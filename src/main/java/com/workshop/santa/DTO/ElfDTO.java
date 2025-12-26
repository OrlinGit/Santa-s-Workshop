package com.workshop.santa.DTO;

import com.workshop.santa.model.ElfSkillLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class ElfDTO {

    @NotBlank
    @Size(min = 2, max = 40)
    private String name;

    @NotNull
    private ElfSkillLevel skillLevel;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElfSkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(ElfSkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setId(Long elfId) {
        
    }

    public void setAssignedGifts(List<Long> assignedGifts) {
    }
}
