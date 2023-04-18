package br.com.cotiinformatica.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponseDto {

	private String transactionId;
	private String status;
	private String message;
	private OrderResponseDto order;
	private BigDecimal total;
}
