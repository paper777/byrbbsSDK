package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * 搜索结果结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class Search {

    /**
     * 当前搜索结果所包含的分区目录名数组
     * 仅存在于/search/:board中
     */
    private List<Section> section;
    /**
     * 当前搜索结果所包含的版面元数据数组
     * 仅存在于/search/:board中
     */
    private List<Board> board;
    /**
     * 当前搜索结果所包含的文章元数组
     */
    private List<Article> article;
    /**
     * 当前搜索结果分页信息
     */
    private Pagination pagination;

    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }

    public List<Board> getBoard() {
        return board;
    }

    public void setBoard(List<Board> board) {
        this.board = board;
    }

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
