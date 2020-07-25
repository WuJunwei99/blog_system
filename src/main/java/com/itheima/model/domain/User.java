package com.itheima.model.domain;
import java.util.Date;

public class User {

    private int id;             //用户id
    private String username;    //用户名
    private String email;       //用户邮箱
    private Date created;       //用户创建时间
    private int authorityId;      //用户权限等级

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", authorityId=" + authorityId +
                '}';
    }
}
