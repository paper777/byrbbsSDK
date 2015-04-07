package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * 收藏夹结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class Favorite {

    /**
     * 收藏夹级数，顶层收藏夹level为0
     * 此属性为元数据属性
     */
    private int level;
    /**
     * 收藏夹目录
     * 此属性为元数据属性
     */
    private String description;
    /**
     * 收藏夹目录位置，该值用于删除收藏夹目录
     * 此属性为元数据属性
     */
    private int position;
    /**
     * 该层收藏夹包含的自定义目录的数组，数组元素为收藏夹元数据
     */
    private List<Favorite> sub_favorite;
    /**
     * 该层收藏夹包含的分区的数组，数组元素为分区元数据
     */
    private List<Section> section;
    /**
     * 该层收藏夹包含的版面的数组，数组元素为版面元数据
     */
    private List<Board> board;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Favorite> getSub_favorite() {
        return sub_favorite;
    }

    public void setSub_favorite(List<Favorite> sub_favorite) {
        this.sub_favorite = sub_favorite;
    }

    public List<Section> getSections() {
        return section;
    }

    public void setSections(List<Section> sections) {
        this.section = sections;
    }

    public List<Board> getBoard() {
        return board;
    }

    public void setBoard(List<Board> board) {
        this.board = board;
    }
}
