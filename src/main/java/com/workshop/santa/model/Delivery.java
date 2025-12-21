package com.workshop.santa.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @Column(length = 130, nullable = false)
    private String address;

    @Column(length = 255, nullable = false)
    private String recipientName;

    @ElementCollection
    @CollectionTable(name = "delivery_gifts", joinColumns =@JoinColumn(name = "delivery_id"))
    @Column(name = "gift_id")
    private List<Long> giftIds;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @Column(nullable = false, updatable = true)
    private LocalDate deliveryDate;

    public Delivery() {
    }

    @PrePersist
    private void createDeliveryDate() {
        if (deliveryDate == null) {
            deliveryDate = LocalDate.now().plusDays(14);
        }
    }

    public Delivery(String address, String recipientName, List<Long> giftIds, DeliveryStatus status) {
        this.address = address;
        this.recipientName = recipientName;
        this.giftIds = giftIds;
        this.status = status;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public List<Long> getGiftIds() {
        return giftIds;
    }

    public void setGiftIds(List<Long> giftIds) {
        this.giftIds = giftIds;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
