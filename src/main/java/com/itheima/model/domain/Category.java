package com.itheima.model.domain;

import java.util.Date;

public class Category {

    private int id;//主键(类型表)

    private String categoryCode;//类型编码

    private  String categoryName;//类型名

    private int parentId;//父类型id

    private Long createBy;//谁创建的(外键backend_user{id})

    private Date creationTime;//创建时间

    private Long modifyBy;//修改人(外键backend_user{id})

    private Date modifyDate;//上次修改时间

    private  String parentName;

    private int useNum;

    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getId() {
        return id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", parentId=" + parentId +
                ", createBy=" + createBy +
                ", creationTime=" + creationTime +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
