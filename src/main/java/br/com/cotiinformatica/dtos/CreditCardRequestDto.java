package br.com.cotiinformatica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequestDto {

	private String cardNumber;
	private String cardholderName;
	private String expirationMonth;
	private String expirationYear;
	private String securityCode;
}
