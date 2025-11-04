package com.asis.entity;

import javax.persistence.*;

@Entity
@Table(name = "app_details")
public class AppDetailsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // For "announcements, banners, and promotions"

    @Lob
    private String contentBody; // The text of the announcement

    private String imageUrl; // For banners

    // Defines where this content appears (e.g., 'homepage-banner', 'sidebar-promo')
    @Column(nullable = false)
    private String displayLocation;

    private boolean isActive; // Toggle to show/hide this content

    // JPA requires an empty constructor
    public AppDetailsData() {
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDisplayLocation() {
        return displayLocation;
    }

    public void setDisplayLocation(String displayLocation) {
        this.displayLocation = displayLocation;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}