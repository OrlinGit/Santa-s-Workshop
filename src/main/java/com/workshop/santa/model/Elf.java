package com.workshop.santa.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "elfs")
public class Elf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long elfId;

    @Column(length = 50,nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ElfSkillLevel skillLevel;

    @ElementCollection
    @CollectionTable(name = "assigned_gifts", joinColumns = @JoinColumn(name = "elf_id"))
    @Column(name = "gifts_id")
    private List<Long> assignedGifts = new ArrayList<>();

    public Elf() {
    }

    public Elf(String name, ElfSkillLevel skillLevel, List<Long> assignedGifts) {
        this.name = name;
        this.skillLevel = skillLevel;
    }

    public Long getElfId() {
        return elfId;
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
