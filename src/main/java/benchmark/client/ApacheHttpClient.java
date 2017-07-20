package benchmark.client;

import benchmark.api.HttpBinClient;
import benchmark.model.httpbin.IpAddress;
import benchmark.model.httpbin.UserAgent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class ApacheHttpClient implements HttpBinClient {

    HttpClient httpClient = HttpClients.createDefault();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public IpAddress findIp() throws IOException {

        HttpResponse response = httpClient.execute(new HttpGet(HttpBinClient.URL_IP));
        return mapper.readValue(response.getEntity().getContent(), IpAddress.class);
    }

    @Override
    public UserAgent findUserAgent() throws IOException {

        HttpResponse response = httpClient.execute(new HttpGet(HttpBinClient.URL_USER_AGENT));
        return mapper.readValue(response.getEntity().getContent(), UserAgent.class);
    }

    @Override
    public InputStream streamLines(int lines) throws IOException {

        HttpResponse response = httpClient.execute(new HttpGet(String.format(HttpBinClient.URL_STREAM, lines)));
        return response.getEntity().getContent();
    }
}
