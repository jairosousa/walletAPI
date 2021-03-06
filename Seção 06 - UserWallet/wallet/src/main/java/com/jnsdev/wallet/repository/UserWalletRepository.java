package com.jnsdev.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jnsdev.wallet.entity.UserWallet;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {

}
