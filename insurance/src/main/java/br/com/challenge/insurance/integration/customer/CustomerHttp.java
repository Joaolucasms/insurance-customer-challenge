package br.com.challenge.insurance.integration.customer;

import br.com.challenge.insurance.integration.customer.dto.CustomerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class CustomerHttp {
    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("customer-service.ur")
    private String baseUrl;

    public CustomerResponse findCustomer(UUID id){

        String url = baseUrl + "/customer/" + id;
        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    CustomerResponse.class
            ).getBody();
        }catch (Exception e){
            return null;
        }
    }
}
