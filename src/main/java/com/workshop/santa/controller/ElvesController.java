package com.workshop.santa.controller;

import com.workshop.santa.DTO.ElfDTO;
import com.workshop.santa.services.ElvesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/elves")
public class ElvesController {

    @Autowired
    private ElvesService elvesService;

    @GetMapping()
    public ResponseEntity<ElfDTO> createElf(@RequestBody ElfDTO elfDTO) {
        ElfDTO createdElf = elvesService.createElf(elfDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElf);
    }

}
