package com.workshop.santa.controller;

import com.workshop.santa.DTO.DeliveryDTO;
import com.workshop.santa.services.DeliveryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/deliveries")
public class DeliveryController {

    @Autowired
    public DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryDTO> createDelivery(@RequestBody @Valid DeliveryDTO deliveryDTO){
        try{
            DeliveryDTO createdDelivery = deliveryService.createDelivery(deliveryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDelivery);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalStateException e){
          return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
