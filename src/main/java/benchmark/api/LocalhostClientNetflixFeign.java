package benchmark.api;

import benchmark.model.greeting.Greeting;
import benchmark.model.httpbin.IpAddress;
import benchmark.model.httpbin.UserAgent;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.io.IOException;

public interface LocalhostClientNetflixFeign {

    @RequestLine("GET /greeting")
    Greeting greet()  throws IOException;
}
