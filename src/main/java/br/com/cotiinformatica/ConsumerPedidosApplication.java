package br.com.cotiinformatica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ConsumerPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerPedidosApplication.class, args);
	}

}
