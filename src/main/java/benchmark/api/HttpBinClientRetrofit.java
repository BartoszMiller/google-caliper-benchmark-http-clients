package benchmark.api;

import benchmark.model.httpbin.IpAddress;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.io.InputStream;

public interface HttpBinClientRetrofit {

    @GET("/ip")
    Call<IpAddress> findIpRetrofit() throws IOException;

    @GET("/stream/{lines}")
    Call<ResponseBody> streamLines(@Path("lines") int lines) throws IOException;
}
