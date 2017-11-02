package com.lanou.bean;

import java.util.Date;

public class Message {
    private Integer mesId;

    private String mesContent;

    private Date mesDate;

    private Integer mesUp;

    private Integer mesDown;

    public Integer getMesId() {
        return mesId;
    }

    public void setMesId(Integer mesId) {
        this.mesId = mesId;
    }

    public String getMesContent() {
        return mesContent;
    }

    public void setMesContent(String mesContent) {
        this.mesContent = mesContent == null ? null : mesContent.trim();
    }

    public Date getMesDate() {
        return mesDate;
    }

    public void setMesDate(Date mesDate) {
        this.mesDate = mesDate;
    }

    public Integer getMesUp() {
        return mesUp;
    }

    public void setMesUp(Integer mesUp) {
        this.mesUp = mesUp;
    }

    public Integer getMesDown() {
        return mesDown;
    }

    public void setMesDown(Integer mesDown) {
        this.mesDown = mesDown;
    }
}