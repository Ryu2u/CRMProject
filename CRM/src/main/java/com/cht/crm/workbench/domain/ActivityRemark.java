package com.cht.crm.workbench.domain;

/**
 * @author Ryuzu
 * @date 2022/1/16 12:01
 */
public class ActivityRemark {
    private String id;           //UUID 主键
    private String noteContent;  //备注信息
    private String createTime;   //创建时间 外键 关联tbl_activity
    private String createBy;     //创建人 外键
    private String editTime;     //编辑时间 外键
    private String editBy;       //编辑人 外键
    private String editFlag;     //是否修改过的标记
    private String activityId;   //外键 关联tbl_activity owner


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }
}
