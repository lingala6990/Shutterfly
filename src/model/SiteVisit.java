package model;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by Arjun on 8/7/17.
 *
 * This class represents SiteVisit from the model
 *
 */
public class SiteVisit {

    private String pageId;
    private Timestamp eventTime;
    private String customerId;
    private Map<String, String> tags;

    /**
     *
     * Constructor for Site Visit
     *
     * @param pageId
     * @param eventTime
     * @param customerId
     * @param tags
     */
    public SiteVisit(String pageId, Timestamp eventTime, String customerId, Map<String, String> tags) {
        this.pageId = pageId;
        this.eventTime = eventTime;
        this.customerId = customerId;
        this.tags = tags;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
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

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
