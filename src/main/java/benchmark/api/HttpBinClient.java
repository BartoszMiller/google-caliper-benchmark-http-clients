package benchmark.api;

import benchmark.model.httpbin.IpAddress;
import benchmark.model.httpbin.UserAgent;

import java.io.IOException;
import java.io.InputStream;

public interface HttpBinClient {

    final String BASE_URL = "http://httpbin.org";

    final String RELATIVE_URL_IP = "/ip";
    final String RELATIVE_URL_USER_AGENT = "/user-agent";
    final String RELATIVE_URL_STREAM = "/stream/%s";

    final String URL_IP = BASE_URL + RELATIVE_URL_IP;
    final String URL_USER_AGENT = BASE_URL + RELATIVE_URL_USER_AGENT;
    final String URL_STREAM = BASE_URL + RELATIVE_URL_STREAM;

    IpAddress findIp() throws IOException;

    UserAgent findUserAgent() throws IOException;

    InputStream streamLines(int lines) throws IOException;
}
