package com.byzp.pojo;

import java.util.List;

public class ByStudent extends ByLogin{

    private int id;

    private String name;

    private String gender;

    private Integer age;

    private String classes;

    private int identity_number;

    private String identity;

    private String address;

    private String phone;

    private String image;

    private List<ByCount> sclist;

    private ByCount byCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(int identity_number) {
        this.identity_number = identity_number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ByCount> getSclist() {
        return sclist;
    }

    public void setSclist(List<ByCount> sclist) {
        this.sclist = sclist;
    }

    public ByCount getByCount() {
        return byCount;
    }

    public void setByCount(ByCount byCount) {
        this.byCount = byCount;
    }

}