package com.jnsdev.wallet.service;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.jnsdev.wallet.entity.WalletItem;

public interface WalletItemService {

	WalletItem save(WalletItem wi);

	Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page);

}
