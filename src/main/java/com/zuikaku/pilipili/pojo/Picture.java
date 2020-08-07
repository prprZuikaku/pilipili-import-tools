package com.zuikaku.pilipili.pojo;

import java.util.Date;

public class Picture {
    private Long pictureId;

    private String name;

    private String picturePath;

    private Long albumId;

    private Integer sortNo;

    private Date createDate;

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", name='" + name + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", albumId=" + albumId +
                ", sortNo=" + sortNo +
                ", createDate=" + createDate +
                '}';
    }

    public Picture(Long pictureId, String name, String picturePath, Long albumId, Integer sortNo, Date createDate) {
        this.pictureId = pictureId;
        this.name = name;
        this.picturePath = picturePath;
        this.albumId = albumId;
        this.sortNo = sortNo;
        this.createDate = createDate;
    }

    public Picture() {
        super();
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}