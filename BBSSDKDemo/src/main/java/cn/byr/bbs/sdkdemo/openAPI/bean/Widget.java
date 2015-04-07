package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * Widget结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class Widget {

    /**
     * widget标识
     */
    private String name;
    /**
     * widget标题
     */
    private String title;
    /**
     * 上次修改时间
     */
    private int time;
    /**
     * 十大热门话题所包含的文章元数据数组
     */
    private List<Article> article;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }
}
