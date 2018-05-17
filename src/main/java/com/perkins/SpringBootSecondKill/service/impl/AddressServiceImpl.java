package com.perkins.SpringBootSecondKill.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perkins.SpringBootSecondKill.dao.AddressDao;
import com.perkins.SpringBootSecondKill.domain.Address;
import com.perkins.SpringBootSecondKill.exception.GlobalException;
import com.perkins.SpringBootSecondKill.result.CodeMsg;
import com.perkins.SpringBootSecondKill.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressDao addressDao;
	
	@Override
	@Transactional
	public void insert(Address address) {
		try {
			addressDao.insert(address);
		} catch (Exception e) {
			throw new GlobalException(CodeMsg.ADDRESS_INSERT_ERROR);
		}
		
	}

	@Override
	@Transactional
	public void update(Address address) {
		try {
			addressDao.update(address);
		} catch (Exception e) {
			throw new GlobalException(CodeMsg.ADDRESS_UPDATE_ERROR);
		}
		
	}

	@Override
	@Transactional
	public void delete(long id) {
		try {
			addressDao.delete(id);
		} catch (Exception e) {
			throw new GlobalException(CodeMsg.ADDRESS_DELETE_ERROR);
		}
		
	}

	@Override
	public int count(long userId) {
		return addressDao.count(userId);
	}

	@Override
	public Address getAddressById(long id) {
		return addressDao.getAddressById(id);
	}

	@Override
	public List<Address> list(long userId) {
		return addressDao.list(userId);
	}

}
