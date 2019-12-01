package com.jnsdev.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnsdev.wallet.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
