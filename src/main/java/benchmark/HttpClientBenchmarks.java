package benchmark;

import benchmark.api.HttpBinClient;
import benchmark.client.ApacheHttpClient;
import benchmark.client.NetflixFeignClient;
import benchmark.client.OkHttpClient;
import benchmark.client.RetrofitClient;
import benchmark.model.httpbin.IpAddress;
import benchmark.model.httpbin.UserAgent;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.api.VmOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@VmOptions({"-Xms512M", "-Xmx512M"})
public class HttpClientBenchmarks {

    final static String APACHE = "apache";
    final static String OK_HTTP = "okHttp";
    final static String RETROFIT = "retrofit";
    final static String FEIGN = "feign";

    static Map<String, HttpBinClient> httpClients;

    static {
        httpClients = new HashMap<>();
        httpClients.put(APACHE, new ApacheHttpClient());
        httpClients.put(OK_HTTP, new OkHttpClient());
        httpClients.put(RETROFIT, new RetrofitClient());
        httpClients.put(FEIGN, new NetflixFeignClient());
    }

    /**
     * Specify clients used in benchmarking
     */
    @Param({APACHE, OK_HTTP, RETROFIT, FEIGN})
    String clientName;

    @Benchmark
    void httpBin_findIp(int reps) throws IOException {
        HttpBinClient httpBinClient = httpClients.get(clientName);
        for (int i = 0; i < reps; i++) {
            IpAddress ip = httpBinClient.findIp();
        }
    }

    @Benchmark
    void httpBin_userAgent(int reps) throws IOException {
        HttpBinClient httpBinClient = httpClients.get(clientName);
        for (int i = 0; i < reps; i++) {
            UserAgent userAgent = httpBinClient.findUserAgent();
        }
    }

    //    @Benchmark
    void httpBin_streamLines(int reps) throws IOException {
        HttpBinClient httpBinClient = httpClients.get(clientName);
        for (int i = 0; i < reps; i++) {
            InputStream inputStream = httpBinClient.streamLines(1);
        }
    }
}
