package benchmark.client;

import benchmark.api.LocalhostClient;
import benchmark.api.LocalhostClientRetrofit;
import benchmark.model.greeting.Greeting;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class RetrofitClient implements LocalhostClient {

    LocalhostClientRetrofit localhostClientRetrofit = new Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl(LocalhostClient.BASE_URL)
            .client(new OkHttpClient.Builder().build())
            .build().create(LocalhostClientRetrofit.class);

    @Override
    public Greeting greet() throws IOException {

        Call<Greeting> greetingCall = localhostClientRetrofit.greet();
        return greetingCall.execute().body();
    }
}
