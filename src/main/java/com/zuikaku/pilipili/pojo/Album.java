package com.zuikaku.pilipili.pojo;

import java.util.Date;

public class Album {
    private Long albumId;

    private String name;

    private String coverPath;

    private String folderPath;

    private Date createDate;

    public Album(Long albumId, String name, String coverPath, String folderPath, Date createDate) {
        this.albumId = albumId;
        this.name = name;
        this.coverPath = coverPath;
        this.folderPath = folderPath;
        this.createDate = createDate;
    }

    public Album() {
        super();
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath == null ? null : coverPath.trim();
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath == null ? null : folderPath.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}