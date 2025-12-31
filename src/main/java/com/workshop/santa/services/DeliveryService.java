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
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService implements DeliveryInterface {


    private final DeliveryRepo deliveryRepo;
    private final GiftRepo giftRepo;

    public DeliveryService(DeliveryRepo deliveryRepo, GiftRepo giftRepo) {
        this.deliveryRepo = deliveryRepo;
        this.giftRepo = giftRepo;
    }


    @Override
    @Transactional
    public DeliveryDTO createDelivery(@Valid DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setRecipientName(deliveryDTO.getRecipientName());
        delivery.setAddress(deliveryDTO.getAddress());
        List<Long> ids = deliveryDTO.getGiftIds();
        List<Long> addedGifts = new ArrayList<>();
        for (Long id : ids) {
            if (!giftRepo.existsById(id)) {
                throw new EntityNotFoundException("Gift with id" + id + "not found");
            }
            Gift gift = giftRepo.getReferenceById(id);
            GiftStatus currentIdStatus = gift.getStatus();
            if ((!currentIdStatus.equals(GiftStatus.READY)) && (!currentIdStatus.equals(GiftStatus.LOADED))) {
                throw new IllegalStateException("Gift with id " + id + " is with invalid status!");
            } else {

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
    public List<DeliveryDTO> getAllDeliveries(@Valid String recipientName, @Valid DeliveryStatus status) {
        List<Delivery> deliveries = new ArrayList<>();
        if (status != null) {
            deliveries = deliveryRepo.findByStatus(status);
        } else if (recipientName != null) {
            deliveries = deliveryRepo.findByRecipientNameIgnoreCase(recipientName);
        } else {
            deliveries = deliveryRepo.findAll();
        }
        List<DeliveryDTO> response = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            DeliveryDTO currentDelivery = new DeliveryDTO();
            currentDelivery.setAddress(delivery.getAddress());
            currentDelivery.setRecipientName(delivery.getRecipientName());
            currentDelivery.setGiftIds(delivery.getGiftIds());
            response.add(currentDelivery);
        }
        return response;
    }

    @Override
    @Transactional
    public void changeDeliveryStatus(Long id, DeliveryStatus status) {
        Delivery deliveryToChangeStatus = new Delivery();
        if (!deliveryRepo.existsById(id)) {
            throw new EntityNotFoundException("Delivery with id " + id + " does not exist");
        } else {
            deliveryToChangeStatus = deliveryRepo.getReferenceById(id);
        }
        DeliveryStatus currentStatus = deliveryToChangeStatus.getStatus();
        if (currentStatus == DeliveryStatus.DELIVERED && status != DeliveryStatus.DELIVERED) {
            throw new IllegalStateException("Cannot change status DELIVERED to " + status);
        } else {
            List<Long> giftIdsToChange = deliveryToChangeStatus.getGiftIds();
            for (Long giftId : giftIdsToChange) {
                Gift gift = giftRepo.getReferenceById(giftId);
                gift.setStatus(GiftStatus.DELIVERED);
                giftRepo.save(gift);
            }
            deliveryToChangeStatus.setStatus(status);
            deliveryRepo.save(deliveryToChangeStatus);
        }
    }

    @Override
    public DeliveryDTO getDeliveryById(Long id) {
        if (!deliveryRepo.existsById(id)) {
            throw new EntityNotFoundException("Delivery with id " + id + " does not exist");
        } else {
            Delivery deliveryByID = deliveryRepo.getReferenceById(id);
            DeliveryDTO result = new DeliveryDTO();
            result.setRecipientName(deliveryByID.getRecipientName());
            result.setAddress(deliveryByID.getAddress());
            result.setGiftIds(deliveryByID.getGiftIds());
            return result;
        }
    }

    @Override
    public void deleteDeliveryById(Long id) {

    }
}
