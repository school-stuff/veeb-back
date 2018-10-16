//package app;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.instanceOf;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.equalTo;
//
//import java.net.URL;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
///*
//@ContextConfiguration(locations = { "classpath:factorybean-spring-ctx.xml" })
//*/
//public class Application {
//
//    @LocalServerPort
//    private int port;
//
//    private URL base;
//
//    @Autowired
//    private TestRestTemplate template;
//
//    @Before
//    public void setUp() throws Exception {
//        this.base = new URL("http://localhost:" + port + "/");
//    }
//
//    @Test
//    public void getHello() throws Exception {
//        ResponseEntity<String> response = template.getForEntity(base.toString(),
//                String.class);
//        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
//    }
//}

