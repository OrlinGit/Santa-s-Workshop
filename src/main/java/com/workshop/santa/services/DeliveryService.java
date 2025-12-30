package com.workshop.santa.services;

import com.workshop.santa.DTO.DeliveryDTO;
import com.workshop.santa.model.Delivery;
import com.workshop.santa.model.DeliveryStatus;
import com.workshop.santa.model.Gift;
import com.workshop.santa.model.GiftStatus;
import com.workshop.santa.repository.DeliveryRepo;
import com.workshop.santa.repository.GiftRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Transactional
    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setRecipientName(deliveryDTO.getRecipientName());
        delivery.setAddress(deliveryDTO.getAddress());
        List<Long> ids = deliveryDTO.getGiftIds();
        List<Long> addedGifts = new ArrayList<>();
        for (Long id : ids){
            if (!giftRepo.existsById(id)){
                throw new EntityNotFoundException ("Gift with id" + id + "not found");
            }
            Gift gift = giftRepo.getReferenceById(id);
            GiftStatus currentIdStatus = gift.getStatus();
            if ((!currentIdStatus.equals(GiftStatus.READY)) && (!currentIdStatus.equals(GiftStatus.LOADED))) {
                throw new IllegalStateException("Gift with id " + id + " is with invalid status!");
            }
            else
            {

                gift.setStatus(GiftStatus.LOADED);
                giftRepo.save(gift);
                addedGifts.add(id);
            }
        }
        delivery.setGiftIds(addedGifts);
        delivery.setStatus(DeliveryStatus.IN_TRANSIT);
        deliveryRepo.save(delivery);

        DeliveryDTO result = new DeliveryDTO();
        result.setRecipientName(deliveryDTO.getRecipientName());
        result.setAddress(delivery.getAddress());
        result.setGiftIds(delivery.getGiftIds());
        return result;
    }


    @Override
    public List<DeliveryDTO> getAllDeliveries(String recipientName, DeliveryStatus status) {
        List<Delivery> deliveries = new ArrayList<>();
        if(status != null){
            deliveries = deliveryRepo.findByStatus(status);
        } else if (recipientName != null) {
            deliveries = deliveryRepo.findByRecipientNameIgnoreCase(recipientName);
        } else {
            deliveries = deliveryRepo.findAll();
        }
        List<DeliveryDTO> response = new ArrayList<>();
        for (Delivery delivery : deliveries){
            DeliveryDTO currentDelivery = new DeliveryDTO();
            currentDelivery.setAddress(delivery.getAddress());
            currentDelivery.setRecipientName(delivery.getRecipientName());
            currentDelivery.setGiftIds(delivery.getGiftIds());
            response.add(currentDelivery);
        }
        return response;
    }

    @Override
    public DeliveryDTO getDeliveryById(Long id) {
        return null;
    }

    @Override
    public void deleteDeliveryById(Long id) {

    }
}
