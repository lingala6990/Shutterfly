package model;

import java.sql.Timestamp;

/**
 * Created by Arjun on 8/7/17.
 *
 * This class represents Image entity from the model.
 */
public class ImageUpload {

    private String imageId;
    private Timestamp eventTime;
    private String customerId;
    private String cameraMake;
    private String cameraModel;

    /**
     *
     * Constructor for Image
     *
     * @param imageId
     * @param eventTime
     * @param customerId
     * @param cameraMake
     * @param cameraModel
     */
    public ImageUpload(String imageId, Timestamp eventTime, String customerId, String cameraMake, String cameraModel) {
        this.imageId = imageId;
        this.eventTime = eventTime;
        this.customerId = customerId;
        this.cameraMake = cameraMake;
        this.cameraModel = cameraModel;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCameraMake() {
        return cameraMake;
    }

    public void setCameraMake(String cameraMake) {
        this.cameraMake = cameraMake;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }
}
