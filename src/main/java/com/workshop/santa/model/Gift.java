package com.workshop.santa.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private GiftCategory category;
    @Positive
    @Max(value = 99)
    private int targetAge;
    private Boolean isWrapped;
    private GiftStatus status;
    private LocalDateTime createdAt;

    public Gift() {
    }

    public Gift(String name, GiftCategory category, int targetAge, Boolean isWrapped, GiftStatus status, LocalDateTime createdAt) {
        this.name = name;
        this.category = category;
        this.targetAge = targetAge;
        this.isWrapped = isWrapped;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GiftCategory getCategory() {
        return category;
    }

    public void setCategory(GiftCategory category) {
        this.category = category;
    }

    public int getTargetAge() {
        return targetAge;
    }

    public void setTargetAge(int targetAge) {
        this.targetAge = targetAge;
    }

    public Boolean getWrapped() {
        return isWrapped;
    }

    public void setWrapped(Boolean wrapped) {
        isWrapped = wrapped;
    }

    public GiftStatus getStatus() {
        return status;
    }

    public void setStatus(GiftStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
