package benchmark.client;

import benchmark.api.HttpBinClient;
import benchmark.api.HttpBinClientRetrofit;
import benchmark.model.httpbin.IpAddress;
import benchmark.model.httpbin.UserAgent;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.io.InputStream;

public class RetrofitClient implements HttpBinClient {

    HttpBinClientRetrofit httpBinClientRetrofit = new Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl(HttpBinClient.BASE_URL)
            .client(new OkHttpClient.Builder().build())
            .build().create(HttpBinClientRetrofit.class);

    @Override
    public IpAddress findIp() throws IOException {
        Call<IpAddress> ipCall = httpBinClientRetrofit.findIpRetrofit();
        return ipCall.execute().body();
    }

    @Override
    public UserAgent findUserAgent() throws IOException {
        Call<UserAgent> ipCall = httpBinClientRetrofit.findUserAgent();
        return ipCall.execute().body();
    }

    @Override
    public InputStream streamLines(int lines) throws IOException {
        Call<ResponseBody> ipCall = httpBinClientRetrofit.streamLines(lines);
        return ipCall.execute().body().byteStream();
    }
}
