package com.workshop.santa.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class Elf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ElfSkillLevel skillLevel;
    private List<Long> assignedGifts;

    public Elf() {
    }

    public Elf(String name, ElfSkillLevel skillLevel, List<Long> assignedGifts) {
        this.name = name;
        this.skillLevel = skillLevel;
        this.assignedGifts = assignedGifts;
    }

    public Long getId() {
        return id;
    }

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

    public List<Long> getAssignedGifts() {
        return assignedGifts;
    }

    public void setAssignedGifts(List<Long> assignedGifts) {
        this.assignedGifts = assignedGifts;
    }
}
