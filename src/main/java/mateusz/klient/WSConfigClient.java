package mateusz.klient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WSConfigClient {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("mateusz.klient.wsdl");
		return marshaller;
	}
	
	@Bean
	public BookClient articleClient(Jaxb2Marshaller marshaller) {
		BookClient client = new BookClient();
		client.setDefaultUri("http://localhost:8080/ksiegarnia/ksiazki.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
	
}
