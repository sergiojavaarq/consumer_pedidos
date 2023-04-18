package br.com.cotiinformatica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequestDto {

	private String total;
	private CustomerRequestDto customer;
	private CreditCardRequestDto creditCard;
}
