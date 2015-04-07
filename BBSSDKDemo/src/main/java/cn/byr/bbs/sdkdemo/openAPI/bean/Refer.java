package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * 提醒结构体
 *
 * @author dss886
 * @since 2014-9-7
 * TODO 测试是否能用gson转换
 */
public class Refer {

    /**
     * 提醒编号，此编号用于提醒的相关操作
     */
    private int index;
    /**
     * 提醒文章的id
     */
    private int id;
    /**
     * 提醒文章的group id
     */
    private int group_id;
    /**
     * 提醒文章的reply id
     */
    private int reply_id;
    /**
     * 提醒文章所在版面
     */
    private String board_name;
    /**
     * 提醒文章的标题
     */
    private String title;
    /**
     * 提醒文章的发信人
     */
    private User user;
    /**
     * 发出提醒的时间
     */
    private int time;
    /**
     * 提醒是否已读
     */
    private boolean is_read;
    /**
     * 提醒类型描述，包括：@我的文章，回复我的文章
     * 仅存在与/refer/:type中
     */
    private String description;
    /**
     * 当前提醒列表所包含的提醒元数据数组
     * 仅存在与/refer/:type中
     */
    private List<Refer> refer;
    /**
     * 当前提醒列表的分页信息
     * 仅存在与/refer/:type中
     */
    private Pagination pagination;
    /**
     * 当前类型的提醒是否启用
     * 仅存在与/refer/:type/info中
     */
    private boolean enable;
    /**
     * 当前类型的新提醒个数
     * 仅存在与/refer/:type/info中
     */
    private int new_count;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Refer> getRefer() {
        return refer;
    }

    public void setRefer(List<Refer> refer) {
        this.refer = refer;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getNew_count() {
        return new_count;
    }

    public void setNew_count(int new_count) {
        this.new_count = new_count;
    }
}
