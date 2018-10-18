package com.testpay;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class DemoApplicationTests {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void contextLoads() throws Exception {
        this.webClient.get().uri("/test/hello").exchange().expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello");
    }

}
