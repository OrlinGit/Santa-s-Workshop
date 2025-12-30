package com.workshop.santa.services;

import com.workshop.santa.DTO.DeliveryDTO;
import com.workshop.santa.model.DeliveryStatus;


import java.util.List;

public interface DeliveryInterface {

    DeliveryDTO createDelivery(DeliveryDTO deliveryDTO);


    List<DeliveryDTO> getAllDeliveries(String recipientName, DeliveryStatus status);

    DeliveryDTO getDeliveryById (Long id);

    void deleteDeliveryById (Long id);

}
