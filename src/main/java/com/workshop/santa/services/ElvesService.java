package com.workshop.santa.services;

import com.workshop.santa.DTO.ElfDTO;
import com.workshop.santa.model.Elf;
import com.workshop.santa.repository.ElfRepo;
import com.workshop.santa.repository.GiftRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElvesService implements ElvesInterface{

    private final ElfRepo elfRepo;
    private final GiftRepo giftRepo;

    public ElvesService(ElfRepo elfRepo, GiftRepo giftRepo) {
        this.elfRepo = elfRepo;
        this.giftRepo = giftRepo;
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
        List<Elf> allElves = elfRepo.findAll();
        List<ElfDTO> response = new ArrayList<>();
        for (Elf elf : allElves) {
            ElfDTO currentElf = new ElfDTO();
            currentElf.setId(elf.getElfId());
            currentElf.setName(elf.getName());
            currentElf.setSkillLevel(elf.getSkillLevel());
            currentElf.setAssignedGifts(elf.getAssignedGifts());
            response.add(currentElf);
        }
        return response;
    }

    @Override
    public ElfDTO getElfById(Long id) {
        if(elfRepo.findById(id).isPresent()){
            ElfDTO response = new ElfDTO();
            Elf foundElf = elfRepo.getReferenceById(id);
            response.setId(foundElf.getElfId());
            response.setName(foundElf.getName());
            response.setSkillLevel(foundElf.getSkillLevel());
            return response;
        } else {
            throw new EntityNotFoundException("Elf with id " + id + " not found");
        }
    }


    @Override
    public void deleteElf(Long id) {
        if(elfRepo.findById(id).isEmpty()){
            throw new EntityNotFoundException("Elf with id " + id + " not found");
        } else {
            elfRepo.deleteById(id);
        }
    }

    //POST /api/elves/{elfId}/assign/{giftId}
    @Override
    public ElfDTO assignGift(Long elfIdToAssign, Long giftIdForAssignment){
        if(giftRepo.findById(giftIdForAssignment).isEmpty()){
            throw new EntityNotFoundException("Gift with id " + giftIdForAssignment + " not found");
        } else if (elfRepo.findById(elfIdToAssign).isEmpty()) {
            throw new EntityNotFoundException("Elf with id " + elfIdToAssign + " not found");
        } else {
            Elf elfToAssign = elfRepo.getReferenceById(elfIdToAssign);
            List<Long> updatedList = elfToAssign.getAssignedGifts();
            if(!updatedList.contains(giftIdForAssignment)){
                updatedList.add(giftIdForAssignment);
            } else {
                throw new IllegalStateException("Gift with id" + giftIdForAssignment + "is alreday assigned");
            }
            elfRepo.save(elfToAssign);

            ElfDTO result = new ElfDTO();
            result.setId(elfToAssign.getElfId());
            result.setName(elfToAssign.getName());
            result.setSkillLevel(elfToAssign.getSkillLevel());
            result.setAssignedGifts(updatedList);

            return result;
        }
    }



}
