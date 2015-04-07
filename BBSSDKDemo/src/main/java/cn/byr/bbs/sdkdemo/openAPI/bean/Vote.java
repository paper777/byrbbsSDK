package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * 投票结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class Vote {

    /**
     * 投票标识id
     */
    private int vid;
    /**
     * 投票标题
     */
    private String title;
    /**
     * 投票发起时间戳
     */
    private int start;
    /**
     * 投票截止时间戳
     */
    private int end;
    /**
     * 投票参与的人数
     */
    private int user_count;
    /**
     * 投票总票数(投票类型为单选时与user_count相等)，如果设置投票后可见且还没投票这个值为-1
     * 只存在于/vote/:id中
     */
    private int vote_count;
    /**
     * 投票类型，0为单选，1为多选
     */
    private int type;
    /**
     * 每个用户能投票数的最大值，只有当type为1时，此属性有效
     */
    private int limit;
    /**
     * 投票所关联的投票版面的文章id
     */
    private int aid;
    /**
     * 投票是否截止
     */
    private boolean is_end;
    /**
     * 投票是否被删除
     */
    private boolean is_deleted;
    /**
     * 投票结果是否投票后可见
     */
    private boolean is_result_voted;
    /**
     * 投票发起人的用户元数据
     */
    private User user;
    /** 当前用户是否投票 */
    /** 当前用户的投票时间，未投票为-1 */
    /**
     * 当前用户的投票结果，未投票为空数组
     */
    private List<VoteOption> voted;
    /**
     * 投票选项，由投票选项元数据组成的数组
     */
    private List<VoteOption> options;
    /**
     * 所查询的投票列表的投票元数据构成的数组
     * 只存在于/vote/category/:cate中
     */
    private List<Vote> votes;
    /**
     * 当前投票列表的分页信息
     * 只存在于/vote/category/:cate中
     */
    private Pagination pagination;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getUser_count() {
        return user_count;
    }

    public void setUser_count(int user_count) {
        this.user_count = user_count;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public boolean isIs_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public boolean isIs_result_voted() {
        return is_result_voted;
    }

    public void setIs_result_voted(boolean is_result_voted) {
        this.is_result_voted = is_result_voted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<VoteOption> getVoted() {
        return voted;
    }

    public void setVoted(List<VoteOption> voted) {
        this.voted = voted;
    }

    public List<VoteOption> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOption> options) {
        this.options = options;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
