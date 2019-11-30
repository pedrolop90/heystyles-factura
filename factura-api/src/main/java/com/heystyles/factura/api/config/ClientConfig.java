package com.heystyles.factura.api.config;

import com.heystyles.common.response.ClientResponseErrorHandler;
import com.heystyles.producto.cliente.MarcaProductoClient;
import com.heystyles.producto.cliente.impl.MarcaProductoClientImpl;
import com.heystyles.usuarios.cliente.ProveedorClient;
import com.heystyles.usuarios.cliente.impl.ProveedorClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    @Autowired
    private ClientProperties clientProperties;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(clientProperties.getConnectionTimeout())
                .setReadTimeout(clientProperties.getReadTimeout())
                .errorHandler(new ClientResponseErrorHandler())
                .build();
    }

    @Bean
    public MarcaProductoClient marcaProductoClient(RestTemplate restTemplate) {
        return new MarcaProductoClientImpl(clientProperties.getProductoUrlBase(), restTemplate);
    }

    @Bean
    public ProveedorClient proveedorClient(RestTemplate restTemplate) {
        return new ProveedorClientImpl(clientProperties.getUsuariosUrlBase(), restTemplate);
    }
}
