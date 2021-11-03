package foo.hk.cn.springwebclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SpringWebclientApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void restTemplateTest() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/hello";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assert HttpStatus.OK.equals(response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        System.out.println(root);
    }

}
