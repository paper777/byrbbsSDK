package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * 主题结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class Threads {

    /**
     * 当前主题包含的文章元数据数组
     */
    private List<Article> article;
    /**
     * 当前主题分页信息
     */
    private Pagination pagination;

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
