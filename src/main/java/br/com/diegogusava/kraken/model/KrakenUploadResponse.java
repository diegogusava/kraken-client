package br.com.diegogusava.kraken.model;

import javax.ws.rs.FormParam;

public class KrakenUploadResponse {

    @FormParam("id")
    private String id;

    @FormParam("success")
    private boolean success;

    @FormParam("file_name")
    private String fileName;

    @FormParam("original_size")
    private long originalSize;

    @FormParam("kraked_size")
    private long krakedSize;

    @FormParam("saved_bytes")
    private long savedBytes;

    @FormParam("kraked_url")
    private String krakedUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(long originalSize) {
        this.originalSize = originalSize;
    }

    public long getKrakedSize() {
        return krakedSize;
    }

    public void setKrakedSize(long krakedSize) {
        this.krakedSize = krakedSize;
    }

    public long getSavedBytes() {
        return savedBytes;
    }

    public void setSavedBytes(long savedBytes) {
        this.savedBytes = savedBytes;
    }

    public String getKrakedUrl() {
        return krakedUrl;
    }

    public void setKrakedUrl(String krakedUrl) {
        this.krakedUrl = krakedUrl;
    }

}