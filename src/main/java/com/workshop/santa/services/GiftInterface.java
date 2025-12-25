package com.workshop.santa.services;

import com.workshop.santa.DTO.GiftDTO;

import java.util.List;

public interface GiftInterface {

    GiftDTO createGift(GiftDTO giftDTO);

    List<GiftDTO> getAllGifts();

    GiftDTO getGiftById(Long giftId);

    GiftDTO updateGift(Long id, GiftDTO newGiftDTO);

    GiftDTO wrapGift(Long id);

    void deleteGift(Long id);
}
