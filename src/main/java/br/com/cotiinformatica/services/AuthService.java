package br.com.cotiinformatica.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cotiinformatica.dtos.AuthRequestDto;
import br.com.cotiinformatica.dtos.AuthResponseDto;

@Service
public class AuthService {

	@Value("${apipagamentos.uri}")
	private String uri;

	@Value("${apipagamentos.client}")
	private String client;

	@Value("${apipagamentos.password}")
	private String password;

	public AuthResponseDto createAuth() throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		AuthRequestDto request = new AuthRequestDto(client, password);
		return restTemplate.postForEntity(uri + "/auth", request, AuthResponseDto.class).getBody();
	}
}
