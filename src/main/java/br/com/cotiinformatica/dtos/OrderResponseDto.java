package br.com.cotiinformatica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

	private String id;
	private String date;
	private String customerName;
	private String customerEmail;
	private String customerCpf;
}
