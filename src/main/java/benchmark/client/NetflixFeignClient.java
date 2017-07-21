package benchmark.client;

import benchmark.api.LocalhostClient;
import benchmark.api.LocalhostClientNetflixFeign;
import benchmark.model.greeting.Greeting;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;

import java.io.IOException;

public class NetflixFeignClient implements LocalhostClient {

    LocalhostClientNetflixFeign localhostClientNetflixFeign = Feign.builder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(LocalhostClientNetflixFeign.class, LocalhostClient.BASE_URL);


    @Override
    public Greeting greet() throws IOException {
        return localhostClientNetflixFeign.greet();
    }
}
