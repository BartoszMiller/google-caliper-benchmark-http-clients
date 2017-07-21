package benchmark.api;

import benchmark.model.greeting.Greeting;
import retrofit2.Call;
import retrofit2.http.GET;

import java.io.IOException;

public interface LocalhostClientRetrofit {

    @GET("/greeting")
    Call<Greeting> greet() throws IOException;

}
