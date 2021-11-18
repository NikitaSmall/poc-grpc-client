package com.example.poc;

import com.example.poc.grpc.GreeterClientImpl;
import com.example.poc.grpc.GreeterGrpc;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.poc"})
public class PocClientApplication {
	@Bean
	public AutoConfiguredOpenTelemetrySdk autoConfiguredOpenTelemetrySdk() {
		return AutoConfiguredOpenTelemetrySdk.initialize();
	}

	public static void main(String[] args) {
		SpringApplication.run(PocClientApplication.class, args);
	}

}
