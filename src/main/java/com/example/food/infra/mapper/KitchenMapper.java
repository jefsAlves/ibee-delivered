package com.example.food.infra.mapper;

import com.example.food.api.dto.KitchenDTO;
import com.example.food.model.entities.Kitchen;

public interface KitchenMapper {

    KitchenDTO toDTO(Kitchen kitchen);

    Kitchen toEntity(KitchenDTO kitchenDTO);
    
}
