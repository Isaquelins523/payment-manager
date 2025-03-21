package com.Nordertino.payment_manager;

import com.mercadopago.MercadoPagoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentManagerApplication.class, args);
		MercadoPagoConfig.setAccessToken("YOUR_ACCESS_TOKEN");
	}
}
