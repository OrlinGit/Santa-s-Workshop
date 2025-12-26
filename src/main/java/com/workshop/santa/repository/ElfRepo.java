package com.workshop.santa.repository;

import com.workshop.santa.model.Elf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ElfRepo extends JpaRepository<Elf, Long> {
    }


