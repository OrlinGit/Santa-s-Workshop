package com.workshop.santa.controller;

import com.workshop.santa.DTO.GiftDTO;
import com.workshop.santa.services.GiftService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gifts")
public class GiftController {

    @Autowired
    private GiftService giftService;

    @PostMapping()
    public ResponseEntity<GiftDTO> createGift(@RequestBody @Valid GiftDTO giftDTO) {
        GiftDTO createdGift = giftService.createGift(giftDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGift);
    }

    @GetMapping()
    public ResponseEntity<List<GiftDTO>> getAllGifts() {
        List<GiftDTO> getAllGifts = giftService.getAllGifts();
        return ResponseEntity.status(HttpStatus.OK).body(getAllGifts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftDTO> getGiftById(@PathVariable Long id) {
    try {
        GiftDTO giftDTO = giftService.getGiftById(id);
        return ResponseEntity.status(HttpStatus.OK).body(giftDTO);
    } catch (EntityNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GiftDTO> updateGift(@PathVariable Long id, @RequestBody @Valid GiftDTO giftDTO) {
        try{
            GiftDTO updatedGift = giftService.updateGift(id, giftDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedGift);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}/wrap")
    public ResponseEntity<GiftDTO> wrapGift(@PathVariable Long id) {
        try{
            GiftDTO wrappedGift = giftService.wrapGift(id);
            return ResponseEntity.status(HttpStatus.OK).body(wrappedGift);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGift(@PathVariable Long id) {
        try {
            giftService.deleteGift(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
