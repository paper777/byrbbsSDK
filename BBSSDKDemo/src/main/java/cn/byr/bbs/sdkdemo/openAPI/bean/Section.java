package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * 分区结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class Section {

    /**
     * 分区名称
     */
    private String name;
    /**
     * 分区表述
     */
    private String description;
    /**
     * 是否是根分区
     */
    private boolean is_root;
    /**
     * 该分区所属根分区名称
     */
    private String parent;
    /**
     * 根分区数量,
     * 只存在于/section中
     */
    private int section_count;
    /**
     * 所有根分区元数据所组成的数组,
     * 只存在于/section中
     */
    private List<Section> section;
    /**
     * 当前分区包含的分区目录名数组,
     * 只存在于/section/:name中
     */
    private String[] sub_section;
    /**
     * 当前分区包含的版面元数据数组,
     * 只存在于/section/:name中
     */
    private List<Board> board;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_root() {
        return is_root;
    }

    public void setIs_root(boolean is_root) {
        this.is_root = is_root;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getSection_count() {
        return section_count;
    }

    public void setSection_count(int section_count) {
        this.section_count = section_count;
    }

    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }

    public String[] getSub_section() {
        return sub_section;
    }

    public void setSub_section(String[] sub_section) {
        this.sub_section = sub_section;
    }

    public List<Board> getBoard() {
        return board;
    }

    public void setBoard(List<Board> board) {
        this.board = board;
    }
}
