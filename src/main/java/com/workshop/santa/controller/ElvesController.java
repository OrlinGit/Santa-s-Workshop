package com.workshop.santa.controller;

import com.workshop.santa.DTO.ElfDTO;
import com.workshop.santa.services.ElvesService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elves")
public class ElvesController {

    @Autowired
    private ElvesService elvesService;

    @PostMapping
    public ResponseEntity<ElfDTO> createElf(@RequestBody @Valid ElfDTO elfDTO) {
        ElfDTO createdElf = elvesService.createElf(elfDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElf);
    }

    @GetMapping
    public ResponseEntity<List<ElfDTO>> getAllElves() {
        try {
            List<ElfDTO> allElves = elvesService.getAllElves();
            return ResponseEntity.status(HttpStatus.OK).body(allElves);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ElfDTO> findElf(@PathVariable Long id) {
        try {
            ElfDTO elf = elvesService.getElfById(id);
            return ResponseEntity.status(HttpStatus.OK).body(elf);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElf(@PathVariable Long id) {
        try {
            elvesService.deleteElf(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{elfId}/assign/{giftId}")
    public ResponseEntity<ElfDTO> assignGiftToElf(@RequestBody @Valid ElfDTO elfWithAssignedGift, @PathVariable Long elfId, @PathVariable Long giftId) {
        try {
           elvesService.assignGift(elfId, giftId);
           return ResponseEntity.status(HttpStatus.OK).body(elfWithAssignedGift);
        } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
