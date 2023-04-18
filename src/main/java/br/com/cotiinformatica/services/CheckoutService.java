package br.com.cotiinformatica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cotiinformatica.dtos.AuthResponseDto;
import br.com.cotiinformatica.dtos.CheckoutRequestDto;
import br.com.cotiinformatica.dtos.CheckoutResponseDto;

@Service
public class CheckoutService {

	@Value("${apipagamentos.uri}")
	private String uri;

	@Autowired
	private AuthService authService;

	public CheckoutResponseDto createCheckout(CheckoutRequestDto request) throws Exception {

		AuthResponseDto authResponse = authService.createAuth();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Bearer " + authResponse.getAccessToken());

		HttpEntity<CheckoutRequestDto> httpEntity = new HttpEntity<CheckoutRequestDto>(request, httpHeaders);
		return restTemplate.postForEntity(uri + "/checkout", httpEntity, CheckoutResponseDto.class).getBody();
	}
}
