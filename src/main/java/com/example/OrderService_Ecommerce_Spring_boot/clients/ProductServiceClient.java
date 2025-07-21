package com.example.OrderService_Ecommerce_Spring_boot.clients;

import com.example.OrderService_Ecommerce_Spring_boot.dto.ProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductServiceClient {

    private final RestTemplate restTemplate;

    public ProductServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ProductDTO getProductById(Long productId){


        String url = "http://ECOMMERSPRING/products/db/" + productId;
        ResponseEntity<ProductDTO> response=restTemplate.getForEntity(url,ProductDTO.class);
        return response.getBody();
    }
}
