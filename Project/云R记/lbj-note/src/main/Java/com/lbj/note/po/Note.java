package com.lbj.note.po;

import java.util.Date;

public class Note {
    private Integer noteId; // 云记ID
    private String title; // 云记标题
    private String content; // 云记内容
    private Integer typeId; // 云记类型
    private Date pubTime; //云记发布时间
    private Float lon; // 经度
    private Float lat; // 纬度

    private String typeName;

    // gettter()方法
    public Integer getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public Float getLon() {
        return lon;
    }

    public Float getLat() {
        return lat;
    }

    // setter()方法
    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }
}
