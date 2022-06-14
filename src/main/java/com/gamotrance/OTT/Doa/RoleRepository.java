package com.gamotrance.OTT.Doa;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gamotrance.OTT.Model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

	Role findByRole(String role);
	
}
