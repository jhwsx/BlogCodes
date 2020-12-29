package com.example.annotationstudy;

/**
 * @author wangzhichao
 * @since 2020/12/26
 */
public class VideoItem {
    @Deprecated
    private String objectId;

    private String videoId;

    private String videoUrl;

    @Deprecated
    public VideoItem(String objectId) {
        this.objectId = objectId;
    }

    public VideoItem() {
    }

    @Deprecated
    public String getObjectId() {
        return objectId;
    }
    @Deprecated
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

}
