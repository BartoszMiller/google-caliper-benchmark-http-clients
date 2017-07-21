package benchmark.client;

import benchmark.api.LocalhostClient;
import benchmark.model.greeting.Greeting;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class ApacheHttpClient implements LocalhostClient {

    HttpClient httpClient = HttpClients.createDefault();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Greeting greet() throws IOException {
        HttpResponse response = httpClient.execute(new HttpGet(LocalhostClient.URL));
        return mapper.readValue(response.getEntity().getContent(), Greeting.class);
    }
}
