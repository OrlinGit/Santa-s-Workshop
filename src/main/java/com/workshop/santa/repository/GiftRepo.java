package com.workshop.santa.repository;

import com.workshop.santa.model.Gift;
import com.workshop.santa.model.GiftCategory;
import com.workshop.santa.model.GiftStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiftRepo extends JpaRepository<Gift, Long> {

    List<Gift> findByStatus(GiftStatus status);

    List<Gift> findByCategory(GiftCategory category);

    List<Gift> findByWrapped(Boolean wrapped);

    List<Gift> findByStatusAndCategory(GiftStatus status, GiftCategory category);

    List<Gift> findByStatusAndCategoryAndWrapped(
            GiftStatus status,
            GiftCategory category,
            Boolean wrapped
    );

}
