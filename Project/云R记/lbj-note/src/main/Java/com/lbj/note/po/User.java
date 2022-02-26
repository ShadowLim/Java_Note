package com.lbj.note.po;

/**
 * @author yoonalim
 * @create 2022-01-05-21:36
 */

public class User {
    private Integer userId; // 用户ID
    private String  uname;  // 用户名称
    private String upwd;    // 登录密码
    private String  nick;   // 用户昵称
    private String  head;   // 用户头像
    private String  mood;   // 用户签名

    /**
     *  getter 和 setter 方法 也可以通过 lombok插件来生成
     *  条件： Ⅰ 在 pom.xml 文件中要存在 lombok依赖
     *        Ⅱ file -> settings -> plugins 搜索 lombok 插件进行下载安装 安装成功之后重启 idea 即可
     *        Ⅲ 在类的前面输入以下代码;
     *              import lombok.Getter;
     *              import  lombok.Setter;
     *              @setter
     *              @getter
     */

    // getter()方法
    public Integer getUserId() {
        return userId;
    }

    public String getUname() {
        return uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public String getNick() {
        return nick;
    }

    public String getHead() {
        return head;
    }

    public String getMood() {
        return mood;
    }

    // setter() 方法
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
