package cn.byr.bbs.sdkdemo.openAPI.bean;

/**
 * 用户信息结构体
 *
 * @author dss886
 * @since 2014-9-7
 */
public class User {

    /**
     * 用户id
     */
    private String id;
    /**
     * 用户昵称
     */
    private String user_name;
    /**
     * 用户头像地址
     */
    private String face_url;
    /**
     * 用户头像宽度
     */
    private int face_width;
    /**
     * 用户头像高度
     */
    private int face_height;
    /**
     * 用户性别：m表示男性，f表示女性，n表示隐藏性别
     */
    private String gender;
    /**
     * 用户星座 若隐藏星座则为空
     */
    private String astro;
    /**
     * 用户生命值
     */
    private int life;
    /**
     * 用户qq
     */
    private String qq;
    /**
     * 用户msn
     */
    private String msn;
    /**
     * 用户个人主页
     */
    private String home_page;
    /**
     * 用户身份
     */
    private String level;
    /**
     * 用户是否在线
     */
    private boolean is_online;
    /**
     * 用户发文数量
     */
    private int post_count;
    /**
     * 用户上次登录时间，unixtimestamp格式
     */
    private int last_login_time;
    /**
     * 用户上次登录
     */
    private String last_login_ip;
    /**
     * 用户是否隐藏性别和星座
     */
    private boolean is_hide;
    /**
     * 用户是否通过注册审批
     */
    private boolean is_register;
    /**
     * 用户积分，
     * 此属性为隐藏属性，在某些情况下可用
     */
    private String score;
    /**
     * 用户注册时间，unixtimestamp格式，
     * 当前登陆用户为自己或是当前用户具有管理权限时可用
     */
    private int first_login_time;
    /**
     * 用户登陆次数，
     * 当前登陆用户为自己或是当前用户具有管理权限时可用
     */
    private int login_count;
    /**
     * 用户是否为管理员，
     * 当前登陆用户为自己或是当前用户具有管理权限时可用
     */
    private boolean is_admin;
    /**
     * 用户挂站时间，以秒为单位，
     * 当前登陆用户为自己或是当前用户具有管理权限时可用
     */
    private int stay_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFace_url() {
        return face_url;
    }

    public void setFace_url(String face_url) {
        this.face_url = face_url;
    }

    public int getFace_width() {
        return face_width;
    }

    public void setFace_width(int face_width) {
        this.face_width = face_width;
    }

    public int getFace_height() {
        return face_height;
    }

    public void setFace_height(int face_height) {
        this.face_height = face_height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAstro() {
        return astro;
    }

    public void setAstro(String astro) {
        this.astro = astro;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getHome_page() {
        return home_page;
    }

    public void setHome_page(String home_page) {
        this.home_page = home_page;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isIs_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    public int getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(int last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public boolean isIs_hide() {
        return is_hide;
    }

    public void setIs_hide(boolean is_hide) {
        this.is_hide = is_hide;
    }

    public boolean isIs_register() {
        return is_register;
    }

    public void setIs_register(boolean is_register) {
        this.is_register = is_register;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getFirst_login_time() {
        return first_login_time;
    }

    public void setFirst_login_time(int first_login_time) {
        this.first_login_time = first_login_time;
    }

    public int getLogin_count() {
        return login_count;
    }

    public void setLogin_count(int login_count) {
        this.login_count = login_count;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public int getStay_count() {
        return stay_count;
    }

    public void setStay_count(int stay_count) {
        this.stay_count = stay_count;
    }
}
