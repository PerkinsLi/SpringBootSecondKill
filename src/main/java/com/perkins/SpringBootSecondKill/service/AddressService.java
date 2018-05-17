package com.perkins.SpringBootSecondKill.service;

import java.util.List;

import com.perkins.SpringBootSecondKill.domain.Address;

public interface AddressService {

	void insert(Address address);
	
	void update(Address address);
	
	void delete(long id);
	
	int count(long userId);
	
	Address getAddressById(long id);
	
	List<Address> list(long userId);
}
