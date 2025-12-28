package com.workshop.santa.services;

import com.workshop.santa.DTO.DeliveryDTO;


import java.util.List;

public interface DeliveryInterface {

    DeliveryDTO createDelivery(DeliveryDTO deliveryDTO);

    List<DeliveryDTO> getAllDeliveries ();

    DeliveryDTO getDeliveryById (Long id);

    void deleteDeliveryById (Long id);

}
