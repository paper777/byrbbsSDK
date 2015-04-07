package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * Widget结构体
 *
 * @author dss886
 * @since 2015-1-2
 */
public class Timeline {
    /**
     * 时间线所包含的文章元数据数组
     */
    private List<Article> article;

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }
}
