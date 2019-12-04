package com.jnsdev.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jnsdev.wallet.entity.UserWallet;
import com.jnsdev.wallet.repository.UserWalleyRepository;
import com.jnsdev.wallet.service.UserWalletService;

public class UserWalletServiceImpl implements UserWalletService {

	@Autowired
	private UserWalleyRepository repository;
	
	@Override
	public UserWallet save(UserWallet userWallet) {
		return repository.save(userWallet);
	}

}
