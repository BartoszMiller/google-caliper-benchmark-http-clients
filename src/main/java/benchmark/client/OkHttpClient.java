package benchmark.client;

import benchmark.api.HttpBinClient;
import benchmark.model.httpbin.IpAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;

public class OkHttpClient implements HttpBinClient {

    okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public IpAddress findIp() throws IOException {

        Request request = new Request.Builder()
                .url(HttpBinClient.URL_IP)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return mapper.readValue(response.body().bytes(), IpAddress.class);
    }

    @Override
    public InputStream streamLines(int lines) throws IOException {

        Request request = new Request.Builder()
                .url(String.format(HttpBinClient.URL_STREAM, lines))
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return response.body().byteStream();
    }
}
