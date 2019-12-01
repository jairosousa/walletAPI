package com.jnsdev.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jnsdev.wallet.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	

}
