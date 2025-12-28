package com.workshop.santa.services;

import com.workshop.santa.DTO.ElfDTO;

import java.util.List;

public interface ElvesInterface {
    ElfDTO createElf(ElfDTO elfDTO);

    List<ElfDTO> getAllElves();

    ElfDTO getElfById(Long id);

    void deleteElf(Long id);

    void assignGift(Long elfIdAssign, Long giftIdForAssignment);

    }
