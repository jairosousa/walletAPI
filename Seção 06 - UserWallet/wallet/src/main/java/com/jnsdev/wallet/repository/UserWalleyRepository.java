package com.jnsdev.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnsdev.wallet.entity.UserWallet;

public interface UserWalleyRepository extends JpaRepository<UserWallet, Long> {

}
