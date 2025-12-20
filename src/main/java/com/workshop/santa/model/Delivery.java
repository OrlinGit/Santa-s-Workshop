package com.workshop.santa.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 5, max = 120)
    private String address;
    @NotBlank
    private String recipientName;
    @Size(min = 1)
    private List<Long> giftsId;
    private DeliveryStatus status;
    private LocalDateTime deliveryDate;
}
