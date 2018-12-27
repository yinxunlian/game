package com.game.entity;

import java.util.Date;


/**
 * 实体类
 *
 * @author yinhang
 */
public class UploadFile {

    /**
     * 文件编码
     */
    private Long    fileId;
    /**
     * 真实文件名
     */
    private String  fileName;
    /**
     * 文件存放相对路径
     */
    private String  accessUrl;
    /**
     * 文件存放相对路径
     */
    private String  url;
    /**
     * 创建人编码
     */
    private String  createUid;
    /**
     * 创建时间
     */
    private Date    createTime;
    /**
     * 修改人编码
     */
    private String  updateUid;
    /**
     * 修改时间
     */
    private Date    updateTime;
    /**
     * 删除标识：0未删除，1删除
     */
    private Integer deleted;

    public UploadFile() {
    }

    /**
     * 新增
     */
    public UploadFile(String fileName, String accessUrl, String createUid) {
        this(null, fileName, accessUrl, createUid, new Date(), createUid, new Date(), 0);
    }

    private UploadFile(Long fileId, String fileName, String accessUrl, String createUid, Date createTime,
                String updateUid, Date updateTime, Integer deleted) {
        super();
        this.fileId = fileId;
        this.fileName = fileName;
        this.accessUrl = accessUrl;
        this.createUid = createUid;
        this.createTime = createTime;
        this.updateUid = updateUid;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    /**
     * 返回文件编码
     *
     * @return fileId 文件编码
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 设置文件编码
     *
     * @param fileId 文件编码
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * 返回真实文件名
     *
     * @return fileName 真实文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置真实文件名
     *
     * @param fileName 真实文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 返回文件存放相对路径
     *
     * @return accessUrl 文件存放相对路径
     */
    public String getAccessUrl() {
        return accessUrl;
    }

    /**
     * 设置文件存放相对路径
     *
     * @param accessUrl 文件存放相对路径
     */
    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl == null ? null : accessUrl.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 返回创建人编码
     *
     * @return createUid 创建人编码
     */
    public String getCreateUid() {
        return createUid;
    }

    /**
     * 设置创建人编码
     *
     * @param createUid 创建人编码
     */
    public void setCreateUid(String createUid) {
        this.createUid = createUid == null ? null : createUid.trim();
    }

    /**
     * 返回创建时间
     *
     * @return createTime 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 返回修改人编码
     *
     * @return updateUid 修改人编码
     */
    public String getUpdateUid() {
        return updateUid;
    }

    /**
     * 设置修改人编码
     *
     * @param updateUid 修改人编码
     */
    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid == null ? null : updateUid.trim();
    }

    /**
     * 返回修改时间
     *
     * @return updateTime 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 返回删除标识：0未删除，1删除
     *
     * @return deleted 删除标识：0未删除，1删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识：0未删除，1删除
     *
     * @param deleted 删除标识：0未删除，1删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
