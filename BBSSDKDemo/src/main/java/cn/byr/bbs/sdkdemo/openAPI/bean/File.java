package cn.byr.bbs.sdkdemo.openAPI.bean;

/**
 * 附件文件结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class File {

    /**
     * 文件名
     */
    private String name;
    /**
     * 文件链接，在用户空间的文件，该值为空
     */
    private String url;
    /**
     * 文件大小
     */
    private String size;
    /**
     * 小缩略图链接(宽度120px)，用户空间的文件，该值为空
     */
    private String thumbnail_small;
    /**
     * 中缩略图链接(宽度240px)，用户空间的文件，该值为空
     */
    private String thumbnail_middle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getThumbnail_small() {
        return thumbnail_small;
    }

    public void setThumbnail_small(String thumbnail_small) {
        this.thumbnail_small = thumbnail_small;
    }

    public String getThumbnail_middle() {
        return thumbnail_middle;
    }

    public void setThumbnail_middle(String thumbnail_middle) {
        this.thumbnail_middle = thumbnail_middle;
    }
}
