package cn.byr.bbs.sdkdemo.openAPI.bean;

import java.util.List;

/**
 * 附件结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class Attachment {

    /**
     * 文件列表
     */
    private List<File> file;
    /**
     * 剩余空间大小
     */
    private String remain_space;
    /**
     * 剩余附件个数
     */
    private int remain_count;

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public String getRemain_space() {
        return remain_space;
    }

    public void setRemain_space(String remain_space) {
        this.remain_space = remain_space;
    }

    public int getRemain_count() {
        return remain_count;
    }

    public void setRemain_count(int remain_count) {
        this.remain_count = remain_count;
    }
}