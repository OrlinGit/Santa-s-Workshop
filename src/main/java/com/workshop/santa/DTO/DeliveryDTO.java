package com.workshop.santa.DTO;

import jakarta.validation.constraints.*;

import java.util.List;

public class DeliveryDTO {

    @NotBlank
    @Size(min = 5, max = 120)
    private String address;

    @NotBlank
    private String recipientName;

    @NotEmpty
    private List<@Positive Long> giftIds;

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

}
