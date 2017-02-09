import com.crazysalaryman.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lse0101 on 2017-02-08.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.datasource.driver-class-name:org.aaa.aaa"})
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

    @Value("${local.server.port}")
    private String port;

    TestRestTemplate rest = new TestRestTemplate();


    @Test
    public void testHelloworld(){
        ResponseEntity<String> response = rest.getForEntity("http://localhost:" + port + "/Hello", String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("HelloWorld"));
    }

}
