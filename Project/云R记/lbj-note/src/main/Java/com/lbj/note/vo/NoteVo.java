package com.lbj.note.vo;


public class NoteVo {
    private String groupName; // 分组名称
    private long noteCount; // 云记数量
    private Integer typeId; // 类型ID

    // getter方法
    public String getGroupName() {
        return groupName;
    }

    public long getNoteCount() {
        return noteCount;
    }

    public Integer getTypeId() {
        return typeId;
    }

    // setter方法
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNoteCount(long noteCount) {
        this.noteCount = noteCount;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
