package com.lbj.note.po;


public class NoteType {
    private Integer typeId; // 类型ID
    private String typeName; // 类型名称
    private Integer userId; // 用户ID

    // getter方法
    public Integer getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public Integer getUserId() {
        return userId;
    }

    // setter 方法

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
