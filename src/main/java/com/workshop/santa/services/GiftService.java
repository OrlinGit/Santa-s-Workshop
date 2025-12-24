package com.workshop.santa.services;

import com.workshop.santa.DTO.GiftDTO;
import com.workshop.santa.model.Gift;
import com.workshop.santa.repository.GiftRepo;
import org.springframework.stereotype.Service;

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

}
