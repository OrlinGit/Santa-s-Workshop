package com.workshop.santa.services;

import com.workshop.santa.DTO.GiftDTO;
import com.workshop.santa.model.Gift;
import com.workshop.santa.repository.GiftRepo;
import jakarta.persistence.EntityNotFoundException;
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
        public GiftDTO createGift(GiftDTO giftDTO) {
        Gift gift = new Gift();
        gift.setName(giftDTO.getName());
        gift.setCategory(giftDTO.getCategory());
        gift.setTargetAge(giftDTO.getTargetAge());
        gift.setWrapped(false);

        Gift savedGift = giftRepo.save(gift);

        GiftDTO response = new GiftDTO();
        response.setName(savedGift.getName());
        response.setCategory(savedGift.getCategory());
        response.setTargetAge(savedGift.getTargetAge());
        return response;

    }

    @Override
    public List<GiftDTO> getAllGifts() {
        List<GiftDTO> response = new ArrayList<>();
        List<Gift> gifts = giftRepo.findAll();
        for (Gift gift : gifts) {
            GiftDTO giftDTO = new GiftDTO();
            giftDTO.setId(gift.getGiftId());
            giftDTO.setName(gift.getName());
            giftDTO.setCategory(gift.getCategory());
            giftDTO.setTargetAge(gift.getTargetAge());
            giftDTO.setIsWrapped(gift.getWrapped());
            giftDTO.setCreatedAt(gift.getCreatedAt());
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
            return response;
        } else {
            throw new EntityNotFoundException("Gift with id " + id + " not found");
        }
    }

    @Override
    public GiftDTO updateGift(Long id, GiftDTO newGiftDTO) {
        if (giftRepo.findById(id).isPresent()) {
            Gift gift = giftRepo.getReferenceById(id);
            gift.setName(newGiftDTO.getName());
            gift.setCategory(newGiftDTO.getCategory());
            gift.setTargetAge(newGiftDTO.getTargetAge());

            Gift updatedGift = giftRepo.save(gift);

            GiftDTO response = new GiftDTO();
            response.setName(updatedGift.getName());
            response.setCategory(updatedGift.getCategory());
            response.setTargetAge(updatedGift.getTargetAge());
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
