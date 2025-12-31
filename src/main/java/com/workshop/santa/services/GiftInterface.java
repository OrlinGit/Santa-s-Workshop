package com.workshop.santa.services;

import com.workshop.santa.DTO.GiftDTO;
import com.workshop.santa.model.GiftCategory;
import com.workshop.santa.model.GiftStatus;

import java.util.List;

public interface GiftInterface {

    GiftDTO createGift(GiftDTO giftDTO);

    List<GiftDTO> getAllGifts(GiftStatus status,
                              GiftCategory category,
                              Boolean wrapped);

    GiftDTO getGiftById(Long giftId);

    GiftDTO updateGift(Long id, GiftDTO newGiftDTO);

    GiftDTO wrapGift(Long id);

    void deleteGift(Long id);
}
