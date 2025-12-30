package com.workshop.santa.repository;

import com.workshop.santa.model.Delivery;
import com.workshop.santa.model.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface DeliveryRepo extends JpaRepository<Delivery,Long> {

    List<Delivery> findByStatus(DeliveryStatus status);

    List<Delivery> findByRecipientNameIgnoreCase(String recipientName);
}

