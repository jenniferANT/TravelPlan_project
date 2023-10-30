package com.app.travelplan.service.impl;

import com.app.travelplan.model.dto.AddressDto;
import com.app.travelplan.model.dto.AddressDto;
import com.app.travelplan.model.entity.Address;
import com.app.travelplan.model.entity.Link;
import com.app.travelplan.model.form.AddressForm;
import com.app.travelplan.model.form.AddressForm;
import com.app.travelplan.repository.AddressRepository;
import com.app.travelplan.repository.LinkRepository;
import com.app.travelplan.service.AddressService;
import com.app.travelplan.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public AddressDto save(AddressForm addressForm) {
        return AddressDto.toDto(
                addressRepository.save(toEntity(addressForm)));
    }

    @Override
    public List<AddressDto> getAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public AddressDto update(AddressForm addressForm, long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Link not found with id " + id));
        if(address.getPlaces().getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            Address a = toEntity(addressForm);
            a.setId(id);
            return AddressDto.toDto(addressRepository.save(a));
        }
        throw new IllegalArgumentException("Update link not permit");
    }

    @Override
    public AddressDto getById(long id) {
        return AddressDto.toDto(addressRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Link not found with id " + id)));
    }

    private Address toEntity(AddressForm addressForm) {
        return Address.builder()
                .latitude(addressForm.getLatitude())
                .longitude(addressForm.getLongitude())
                .addressString(addressForm.getAddressString())
                .addressLinkMap(addressForm.getAddressLinkMap())
                .addressPlusCode(addressForm.getAddressPlusCode())
                .build();
    }
}
