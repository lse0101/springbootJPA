package com.crazysalaryman.api;

import static com.jayway.restassured.RestAssured.*;
import com.crazysalaryman.App;
import com.crazysalaryman.domain.Customer;
import com.crazysalaryman.repository.CustomerRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jayway.restassured.RestAssured;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lse0101 on 2017-02-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.datasource.url=jdbc:h2:mem:bookmark;DB_CLOSE_ON_EXIT=FALSE"})
public class CustomerRestControllerTest {
    @Autowired
    CustomerRepository repository;
    @Value("${local.server.port}")
    private String port;

    private String apiEndpoint;

    RestTemplate restTemplate;
    Customer customer1;
    Customer customer2;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Page<T> {
        private List<T> content;
        private int numberOfElements;
    }

    @Before
    public void setUp() {
//        System.out.println("###################################################");
//        System.out.println(Arrays.toString(repository.findAll().toArray()));
//        System.out.println("###################################################");
//        repository.deleteAll();
//
//        User user1 = new User("user1", "1", Collections.emptyList());
//        User user2 = new User("user2", "2", Collections.emptyList());
//
//        customer1 = new Customer();
//        customer1.setFirstName("Taro");
//        customer1.setLastName("Yamada");
//        customer1.setUser(user1);
//
//        customer2 = new Customer();
//        customer2.setFirstName("Ichiro");
//        customer2.setLastName("Suzuki");
//        customer2.setUser(user2);
//
//        repository.save(Arrays.asList(customer1, customer2));

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8888));
        requestFactory.setProxy(proxy);

//        this.restTemplate = new RestTemplate(requestFactory);
        this.restTemplate = new RestTemplate();

        apiEndpoint = "http://localhost:" + port + "/api/customers";
    }


    @Test
    public void testGetCustomers() throws Exception {
        System.out.println(apiEndpoint);

        ResponseEntity<Page<Customer>> responseEntity = restTemplate.exchange(apiEndpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Customer>>() {
                });
        System.out.println(responseEntity.getBody().toString());
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getNumberOfElements(), is(4));
    }

    @Test
    public void TestRestAssured() throws Exception {
        RestAssured.port = Integer.parseInt(this.port);

        when().get("/api/customers")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("numberOfElements", is(4));
    }
}