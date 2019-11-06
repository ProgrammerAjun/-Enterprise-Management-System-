package com.hugong.ssm.domain;

/**
 * 旅客信息
 */
public class Traveller {
    private String id;      //无意义，主键uuid
    private String name;    //姓名
    private String sex;     //性别
    private String phoneNum;    //电话号码
    private Integer credentialsType;    //证件类型  0身份证 1护照 2军官证
    private String credentialsTypeStr;
    private String credentialsNum;  //证件号码
    private Integer travellerType;  //旅客类型（人群）  0成人 1儿童
    private String travellerTypeStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsTypeStr() {
        //证件类型  0身份证 1护照 2军官证
        if(credentialsType == 0) {
            credentialsTypeStr = "身份证";
        }
        if(credentialsType == 1) {
            credentialsTypeStr = "护照";
        }
        if(credentialsType == 2) {
            credentialsTypeStr = "军官证";
        }
        return credentialsTypeStr;
    }

    public void setCredentialsTypeStr(String credentialsTypeStr) {
        this.credentialsTypeStr = credentialsTypeStr;
    }

    public String getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public Integer getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(Integer travellerType) {
        this.travellerType = travellerType;
    }

    public String getTravellerTypeStr() {
        //旅客类型（人群）  0成人 1儿童
        if(travellerType == 0) {
            travellerTypeStr = "成人";
        }
        if(travellerType == 1) {
            travellerTypeStr = "儿童";
        }
        return travellerTypeStr;
    }

    public void setTravellerTypeStr(String travellerTypeStr) {
        this.travellerTypeStr = travellerTypeStr;
    }
}
