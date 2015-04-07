package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * 用户信箱结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class Mailbox {

    /**
     * 是否有新邮件
     */
    private boolean new_mail;
    /**
     * 信箱是否已满
     */
    private boolean full_mail;
    /**
     * 信箱已用空间
     */
    private String space_used;
    /**
     * 当前用户是否能发信
     */
    private boolean can_send;
    /**
     * 信箱类型描述，包括：收件箱，发件箱，废纸篓，
     * 仅存在于/mail/:box中
     */
    private String description;
    /**
     * 当前信箱所包含的信件元数据数组，
     * 仅存在于/mail/:box中
     */
    private List<Mail> mail;
    /**
     * 当前信箱的分页信息，
     * 仅存在于/mail/:box中
     */
    private Pagination pagination;

    public boolean isNew_mail() {
        return new_mail;
    }

    public void setNew_mail(boolean new_mail) {
        this.new_mail = new_mail;
    }

    public boolean isFull_mail() {
        return full_mail;
    }

    public void setFull_mail(boolean full_mail) {
        this.full_mail = full_mail;
    }

    public String getSpace_used() {
        return space_used;
    }

    public void setSpace_used(String space_used) {
        this.space_used = space_used;
    }

    public boolean isCan_send() {
        return can_send;
    }

    public void setCan_send(boolean can_send) {
        this.can_send = can_send;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Mail> getMail() {
        return mail;
    }

    public void setMail(List<Mail> mail) {
        this.mail = mail;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
