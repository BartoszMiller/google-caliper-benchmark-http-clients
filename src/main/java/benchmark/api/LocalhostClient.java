package benchmark.api;

import benchmark.model.greeting.Greeting;

import java.io.IOException;

public interface LocalhostClient {

    final String BASE_URL = "http://localhost:8080";
    final String RELATIVE_URL = "/greeting";
    final String URL = BASE_URL + RELATIVE_URL;

    Greeting greet()  throws IOException;

}
