package com.workshop.santa.controller;

import com.workshop.santa.DTO.DeliveryDTO;
import com.workshop.santa.model.DeliveryStatus;
import com.workshop.santa.services.DeliveryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping
    public ResponseEntity<List<DeliveryDTO>> getDeliveries(@RequestParam(required = false) String recipientName,
                                                            @RequestParam(required = false) DeliveryStatus status){

        List<DeliveryDTO> deliveries = deliveryService.getAllDeliveries(recipientName, status);
        return ResponseEntity.status(HttpStatus.OK).body(deliveries);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateDeliveryStatus(@PathVariable Long id, @RequestBody Map<String, DeliveryStatus> body){
    try {
        deliveryService.changeDeliveryStatus(id, body.get("deliveryStatus"));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    }


}
