package com.jnsdev.wallet.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jnsdev.wallet.entity.WalletItem;
import com.jnsdev.wallet.repository.WalletItemRepository;
import com.jnsdev.wallet.service.WalletItemService;

@Service
public class WalletItemServiceImpl implements WalletItemService {
	
	@Autowired
	private WalletItemRepository repository;
	
	@Value("${pagination.items_per_page}")
	private int itemsParPage;
	
	@Override
	public WalletItem save(WalletItem wi) {
		return repository.save(wi);
	}

	@Override
	public Page<WalletItem> findBetweenDates(Long wallet, Date start, Date end, int page) {
		
		@SuppressWarnings("deprecation")
		PageRequest pg = new PageRequest(page, itemsParPage);
		
		return repository.findAllByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(wallet, start, end, pg);
	}

}
