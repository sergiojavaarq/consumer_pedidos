package br.com.cotiinformatica.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.commons.pedidos.dto.PedidoDto;
import br.com.cotiinformatica.dtos.CheckoutRequestDto;
import br.com.cotiinformatica.dtos.CheckoutResponseDto;
import br.com.cotiinformatica.dtos.CreditCardRequestDto;
import br.com.cotiinformatica.dtos.CustomerRequestDto;
import br.com.cotiinformatica.services.CheckoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PagamentosConsumer {

	@Autowired
	private CheckoutService checkoutService;

	@KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
	public void consume(ConsumerRecord<String, String> record) throws Exception {

		// Deserializando os dados obtidos da fila
		ObjectMapper objectMapper = new ObjectMapper();
		PedidoDto pedidoDto = objectMapper.readValue(record.value(), PedidoDto.class);

		// Pagar o pedido
		CheckoutRequestDto request = parseCheckout(pedidoDto);
		CheckoutResponseDto response = checkoutService.createCheckout(request);

		log.info(response.toString());
	}

	private CheckoutRequestDto parseCheckout(PedidoDto dto) {

		CheckoutRequestDto checkoutRequestDto = new CheckoutRequestDto();
		checkoutRequestDto.setCreditCard(new CreditCardRequestDto());
		checkoutRequestDto.setCustomer(new CustomerRequestDto());

		checkoutRequestDto.setTotal(dto.getValor().toString());
		checkoutRequestDto.getCustomer().setName(dto.getCliente().getNome());
		checkoutRequestDto.getCustomer().setEmail(dto.getCliente().getEmail());
		checkoutRequestDto.getCustomer().setCpf(dto.getCliente().getCpf());

		checkoutRequestDto.getCreditCard().setCardholderName(dto.getCobranca().getNomeImpressoNoCartao());
		checkoutRequestDto.getCreditCard().setCardNumber(dto.getCobranca().getNumeroCartao());
		checkoutRequestDto.getCreditCard().setExpirationMonth(dto.getCobranca().getMesValidade().toString());
		checkoutRequestDto.getCreditCard().setExpirationYear(dto.getCobranca().getAnoValidade().toString());
		checkoutRequestDto.getCreditCard().setSecurityCode(dto.getCobranca().getCodigoSeguranca().toString());

		return checkoutRequestDto;
	}
}
