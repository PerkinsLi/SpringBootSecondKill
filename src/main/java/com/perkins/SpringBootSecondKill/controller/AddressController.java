package com.perkins.SpringBootSecondKill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.perkins.SpringBootSecondKill.domain.Address;
import com.perkins.SpringBootSecondKill.domain.User;
import com.perkins.SpringBootSecondKill.result.CodeMsg;
import com.perkins.SpringBootSecondKill.result.Result;
import com.perkins.SpringBootSecondKill.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@PostMapping("/insert")
	public JSONObject insert(Address address, User user) {
		
		address.setUserId(user.getId());
		addressService.insert(address);
		return (JSONObject) JSONObject.toJSON(Result.success(CodeMsg.ADDRESS_INSERT_SUCCESS));
	}
	
	@PostMapping("/delete")
	public JSONObject delete(String id) {
		addressService.delete(Long.parseLong(id));
		return (JSONObject) JSONObject.toJSON(Result.success(CodeMsg.ADDRESS_DELETE_SUCCESS));
	}
	
	@PostMapping("/update")
	public JSONObject update(Address address) {
		addressService.update(address);
		return (JSONObject) JSONObject.toJSON(Result.success(CodeMsg.ADDRESS_UPDATE_SUCCESS));
	}
	
	@GetMapping("/get_address")
	public JSONObject getAddressById(String addressId) {
		Address address = addressService.getAddressById(Long.parseLong(addressId));
		return (JSONObject) JSONObject.toJSON(Result.success(address));
	}
	
	@GetMapping("/list")
	public JSONObject list(User user) {
		List<Address> list = addressService.list(user.getId());
		return (JSONObject) JSONObject.toJSON(Result.success(list));
	}
}
