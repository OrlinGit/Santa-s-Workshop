package com.workshop.santa.services;

import com.workshop.santa.DTO.ElfDTO;

import java.util.List;

public interface ElvesInterface {
    ElfDTO createElf(ElfDTO elfDTO);

    List<ElfDTO> getAllElves();

    ElfDTO getElfById(Long id);

    ElfDTO updateElf(Long id, ElfDTO newElfDTO);

    void deleteElf(Long id);

}
