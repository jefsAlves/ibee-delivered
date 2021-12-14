package com.example.food.infra.mapper;

import com.example.food.api.dto.AddressDTO;
import com.example.food.model.entities.Address;

public interface AddressMapper {

    AddressDTO toDTO(Address address);

    Address toEntity(AddressDTO addressDTO);

}
