package com.springboot.blog.pojo;

import java.util.Date;

public class BBlogInfo {
    private Integer id;

    private String bAuthor;

    private Date bTime;

    private String bCopyright;

    private String bContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getbAuthor() {
        return bAuthor;
    }

    public void setbAuthor(String bAuthor) {
        this.bAuthor = bAuthor == null ? null : bAuthor.trim();
    }

    public Date getbTime() {
        return bTime;
    }

    public void setbTime(Date bTime) {
        this.bTime = bTime;
    }

    public String getbCopyright() {
        return bCopyright;
    }

    public void setbCopyright(String bCopyright) {
        this.bCopyright = bCopyright == null ? null : bCopyright.trim();
    }

    public String getbContent() {
        return bContent;
    }

    public void setbContent(String bContent) {
        this.bContent = bContent == null ? null : bContent.trim();
    }
}