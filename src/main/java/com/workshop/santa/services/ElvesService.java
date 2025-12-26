package com.workshop.santa.services;

import com.workshop.santa.DTO.ElfDTO;
import com.workshop.santa.model.Elf;
import com.workshop.santa.model.Gift;
import com.workshop.santa.repository.ElfRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElvesService implements ElvesInterface{

    private final ElfRepo elfRepo;
    public ElvesService(ElfRepo elfRepo) {
        this.elfRepo = elfRepo;
    }

    @Override
    public ElfDTO createElf(ElfDTO elfDTO) {
        Elf newElf = new Elf();
        newElf.setName(elfDTO.getName());
        newElf.setSkillLevel(elfDTO.getSkillLevel());

        Elf savedElf = elfRepo.save(newElf);

        ElfDTO response = new ElfDTO();
        response.setName(savedElf.getName());
        response.setSkillLevel(savedElf.getSkillLevel());
        return response;
    }

    @Override
    public List<ElfDTO> getAllElves() {
        List<Elf> allElfs = elfRepo.findAll();
        List<ElfDTO> response = new ArrayList<>();
        for (Elf elf : allElfs) {
            ElfDTO ElfDTO = new ElfDTO();
            ElfDTO.setId(elf.getElfId());
            ElfDTO.setName(elf.getName());
            ElfDTO.setSkillLevel(elf.getSkillLevel());
            ElfDTO.setAssignedGifts(elf.getAssignedGifts());
            response.add(ElfDTO);
        }
        return response;
    }

    @Override
    public ElfDTO getElfById(Long id) {

        return null;
    }

    @Override
    public ElfDTO updateElf(Long id, ElfDTO newElfDTO) {
        return null;
    }

    @Override
    public void deleteElf(Long id) {

    }


}
