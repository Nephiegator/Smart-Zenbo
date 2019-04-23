package com.example.cv.aninterface;
import java.io.Serializable;

public class dbZenboUser implements Serializable {

    private String uuid;
    private String nickName;
    private String username;
    private int admin;
    private String userId;
    private String relation;


    public dbZenboUser(String uuid, String username, int admin, String userId, String relation, String nickName){
        this.uuid = uuid;
        this.nickName = nickName;
        this.username = username;
        this.admin = admin;
        this.userId = userId;
        this.relation = relation;
    }

    public dbZenboUser(){}

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
