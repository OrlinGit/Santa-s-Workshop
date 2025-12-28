package com.workshop.santa.services;

import com.workshop.santa.DTO.DeliveryDTO;
import com.workshop.santa.repository.DeliveryRepo;
import com.workshop.santa.repository.GiftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService implements DeliveryInterface{


    private final DeliveryRepo deliveryRepo;
    private final GiftRepo giftRepo;

    public DeliveryService(DeliveryRepo deliveryRepo, GiftRepo giftRepo) {
        this.deliveryRepo = deliveryRepo;
        this.giftRepo = giftRepo;
    }


    @Override
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {

        return null;
    }

    @Override
    public List<DeliveryDTO> getAllDeliveries() {
        return List.of();
    }

    @Override
    public DeliveryDTO getDeliveryById(Long id) {
        return null;
    }

    @Override
    public void deleteDeliveryById(Long id) {

    }
}
