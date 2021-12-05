package com.example.food.infra.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.api.dto.AddressDTO;
import com.example.food.model.entities.Address;

@Component
public class AddressMapper {

	@Autowired
	private ModelMapper modelMapper;

	public AddressDTO toDTO(Address address) {
		return modelMapper.map(address, AddressDTO.class);
	}

	public Address toEntity(AddressDTO addressDTO) {
		return modelMapper.map(addressDTO, Address.class);
	}
	
	public static AddressDTO toDTOOptional(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressCep(address.getAddressCep());
		addressDTO.setAddressComplement(address.getAddressComplement());
		addressDTO.setAddressNeighborhood(address.getAddressNeighborhood());
		addressDTO.setAddressNumber(address.getAddressNumber());
		addressDTO.setAddressStreet(address.getAddressStreet());
		return addressDTO;
	}
	
}
