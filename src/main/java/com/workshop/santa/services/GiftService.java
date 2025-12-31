package com.workshop.santa.services;

import com.workshop.santa.DTO.GiftDTO;
import com.workshop.santa.model.Gift;
import com.workshop.santa.model.GiftCategory;
import com.workshop.santa.model.GiftStatus;
import com.workshop.santa.repository.GiftRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiftService implements GiftInterface {

    private final GiftRepo giftRepo;

    public GiftService (GiftRepo giftRepo) {
        this.giftRepo = giftRepo;
    }

@Override
        public GiftDTO createGift(@Valid GiftDTO giftDTO) {
        Gift gift = new Gift();
        gift.setName(giftDTO.getName());
        gift.setCategory(giftDTO.getCategory());
        gift.setTargetAge(giftDTO.getTargetAge());
        gift.setStatus(giftDTO.getStatus());
        gift.setWrapped(false);

        Gift savedGift = giftRepo.save(gift);

        GiftDTO response = new GiftDTO();
        response.setName(savedGift.getName());
        response.setCategory(savedGift.getCategory());
        response.setTargetAge(savedGift.getTargetAge());
        response.setStatus(savedGift.getStatus());
        return response;

    }

    @Override
    public List<GiftDTO> getAllGifts(GiftStatus status,
                                     GiftCategory category,
                                     Boolean wrapped) {
        List<GiftDTO> response = new ArrayList<>();
        List<Gift> gifts = new ArrayList<>();
        if (status != null && category != null && wrapped != null) {
            gifts = giftRepo.findByStatusAndCategoryAndWrapped(
                    status, category, wrapped);
        } else if (status != null && category != null) {
            gifts = giftRepo.findByStatusAndCategory(status, category);
        } else if (status != null) {
            gifts = giftRepo.findByStatus(status);
        } else if (category != null) {
            gifts = giftRepo.findByCategory(category);
        } else if (wrapped != null) {
            gifts = giftRepo.findByWrapped(wrapped);
        } else {
            gifts = giftRepo.findAll();
        }
        for (Gift gift : gifts) {
            GiftDTO giftDTO = new GiftDTO();
            giftDTO.setId(gift.getGiftId());
            giftDTO.setName(gift.getName());
            giftDTO.setCategory(gift.getCategory());
            giftDTO.setTargetAge(gift.getTargetAge());
            giftDTO.setIsWrapped(gift.getWrapped());
            giftDTO.setCreatedAt(gift.getCreatedAt());
            giftDTO.setStatus(gift.getStatus());
            response.add(giftDTO);
        }
    return response;
    }

    @Override
    public GiftDTO getGiftById(Long id) {
        if (giftRepo.findById(id).isPresent()) {
            GiftDTO response = new GiftDTO();
            Gift gift = giftRepo.getReferenceById(id);
            response.setId(gift.getGiftId());
            response.setName(gift.getName());
            response.setCategory(gift.getCategory());
            response.setTargetAge(gift.getTargetAge());
            response.setIsWrapped(gift.getWrapped());
            response.setCreatedAt(gift.getCreatedAt());
            response.setStatus(gift.getStatus());
            return response;
        } else {
            throw new EntityNotFoundException("Gift with id " + id + " not found");
        }
    }

    @Override
    public GiftDTO updateGift(Long id, @Valid GiftDTO newGiftDTO) {
        if (giftRepo.findById(id).isPresent()) {
            Gift gift = giftRepo.getReferenceById(id);
            gift.setName(newGiftDTO.getName());
            gift.setCategory(newGiftDTO.getCategory());
            gift.setTargetAge(newGiftDTO.getTargetAge());
            gift.setStatus(newGiftDTO.getStatus());

            Gift updatedGift = giftRepo.save(gift);

            GiftDTO response = new GiftDTO();
            response.setName(updatedGift.getName());
            response.setCategory(updatedGift.getCategory());
            response.setTargetAge(updatedGift.getTargetAge());
            response.setStatus(updatedGift.getStatus());
            return response;
        } else {
            throw new EntityNotFoundException("Gift with id " + id + " not found");
        }
    }

    @Override
    public GiftDTO wrapGift(Long id) {
        if (giftRepo.findById(id).isPresent()) {
            Gift gift = giftRepo.getReferenceById(id);
            if (!gift.getWrapped()) {
                gift.setWrapped(true);
                giftRepo.save(gift);
            }
                GiftDTO response = new GiftDTO();
                response.setId(gift.getGiftId());
                response.setName(gift.getName());
                response.setCategory(gift.getCategory());
                response.setTargetAge(gift.getTargetAge());
                response.setIsWrapped(gift.getWrapped());
                response.setStatus(gift.getStatus());
                return response;

        } else {
            throw new EntityNotFoundException("Gift with id " + id + " not found");
        }
    }

    @Override
    public void deleteGift(Long id) {
        if(!giftRepo.existsById(id)){
            throw new EntityNotFoundException("Gift with id " + id + " not found");
        };
        giftRepo.deleteById(id);
    }

}
