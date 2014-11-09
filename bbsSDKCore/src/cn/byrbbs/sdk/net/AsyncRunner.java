package cn.byrbbs.sdk.net;

import android.os.AsyncTask;
import cn.byrbbs.sdk.exception.BBSException;
import cn.byrbbs.sdk.exception.BBSHttpException;
import cn.byrbbs.sdk.utils.LogUtil;

public class AsyncRunner {

	public static String request(String url, BBSParameters params, String httpMethod)
			throws BBSException, BBSHttpException {
		return HttpManager.openUrl(url, httpMethod, params);
	}

	public static void requestAsync(String url, BBSParameters params, 
			String httpMethod, RequestListener listener) {
		LogUtil.i("ASYNC", url);
		new RequestRunner(url, params, httpMethod, listener).execute(new Void[1]);
	}

	private static class AsyncTaskResult<T> {
		private T result;
		private BBSException error;

		public T getResult() {
			return this.result;
		}

		public BBSException getError() {
			return this.error;
		}

		public AsyncTaskResult(T result) {
			this.result = result;
		}

		public AsyncTaskResult(BBSException error) {
			this.error = error;
		}
	}

	/*********************************************************************************************************/
	private static class RequestRunner extends AsyncTask<Void, Void, AsyncRunner.AsyncTaskResult<String>> {
		private final String mUrl;
		private final BBSParameters mParams;
		private final String mHttpMethod;
		private final RequestListener mListener;

		public RequestRunner(String url, BBSParameters params, String httpMethod, RequestListener listener) {
			this.mUrl = url;
			this.mParams = params;
			this.mHttpMethod = httpMethod;
			this.mListener = listener;
		}


	    @SuppressWarnings({ "rawtypes", "unchecked" })
		protected AsyncRunner.AsyncTaskResult<String> doInBackground(Void[] params)
	    {
	      try {
	        String result = HttpManager.openUrl(this.mUrl, this.mHttpMethod, this.mParams);
	        return new AsyncRunner.AsyncTaskResult(result);
	      }
	      catch (BBSException e) {
	        return new AsyncRunner.AsyncTaskResult(e);
	      } catch (BBSHttpException e) {
	    	  e.printStackTrace();
	      }
		return null;
	    }

		protected void onPreExecute() { }

		protected void onPostExecute(AsyncRunner.AsyncTaskResult<String> result)
		{
			BBSException exception = result.getError();
			if (exception != null)
				this.mListener.onException(exception);
			else
				this.mListener.onComplete((String)result.getResult());
		}
	}// private static calss
}// class end
