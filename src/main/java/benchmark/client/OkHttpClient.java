package benchmark.client;

import benchmark.api.LocalhostClient;
import benchmark.model.greeting.Greeting;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpClient implements LocalhostClient {

    okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Greeting greet() throws IOException {

        Request request = new Request.Builder()
                .url(LocalhostClient.URL)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return mapper.readValue(response.body().bytes(), Greeting.class);
    }
}
