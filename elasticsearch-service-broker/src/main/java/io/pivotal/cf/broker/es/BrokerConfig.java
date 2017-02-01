package io.pivotal.cf.broker.es;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
public class BrokerConfig {

    @Autowired
    Environment env;

//    @Bean
//    ElasticsearchClient client() throws UnknownHostException {
//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
//
//        return client;
//    }

    @Bean
    JestClient client() {
        JestClientFactory factory = new JestClientFactory();
        String connection = "http://" + env.getProperty("ELASTIC_HOST") + ":" + env.getProperty("ELASTIC_PORT");
        log.info("connecting to elastic service at " + connection);
        factory.setHttpClientConfig(new HttpClientConfig.Builder(connection).multiThreaded(true).build());
        return factory.getObject();
    }
}