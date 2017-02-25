package ru.sareth.webapp.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload";
    private String locationImages = "upload" + File.separator + "images";
    private String locationAttachments = "upload" + File.separator + "attachments";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationImages() {
        return locationImages;
    }

    public void setLocationImages(String locationImages) {
        this.locationImages = locationImages;
    }

    public String getLocationAttachments() {
        return locationAttachments;
    }

    public void setLocationAttachments(String locationAttachments) {
        this.locationAttachments = locationAttachments;
    }
}
