package benchmark.api;

import benchmark.model.httpbin.IpAddress;
import feign.Param;
import feign.RequestLine;
import feign.Response;

import java.io.IOException;

public interface HttpBinClientNetflixFeign {

    @RequestLine("GET /ip")
    IpAddress findIp() throws IOException;

    @RequestLine("GET /stream/{lines}")
    Response streamLines(@Param("lines") int lines) throws IOException;
}
