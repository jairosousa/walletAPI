package com.jnsdev.wallet.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class WalletDTO {

	private Long id;

	@Length(min = 3, message = "O nome deve conter no mínimo 3 caracteres")
	@NotNull
	private String name;

	@NotNull
	private BigDecimal value;

}
