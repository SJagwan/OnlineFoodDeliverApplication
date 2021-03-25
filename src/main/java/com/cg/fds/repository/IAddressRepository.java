package com.cg.fds.repository;

import java.util.Optional;

import com.cg.fds.entities.Address;

public interface IAddressRepository {
	
	public Address save(Address address);
	public Optional<Address> findById(String id);
	public Address remove(Address address);
	boolean existsById(String addressId);
}
