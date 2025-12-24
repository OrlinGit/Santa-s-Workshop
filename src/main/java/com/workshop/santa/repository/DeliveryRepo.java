package com.workshop.santa.repository;

import com.workshop.santa.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepo {
    public interface DeliveryRepository extends JpaRepository<Delivery,Long> {}
}
