package benchmark;

import benchmark.api.HttpBinClient;
import benchmark.client.ApacheHttpClient;
import benchmark.client.NetflixFeignClient;
import benchmark.client.OkHttpClient;
import benchmark.client.RetrofitClient;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.api.VmOptions;

import java.io.IOException;
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
    int httpBin_findIp(int reps) throws IOException {
        HttpBinClient httpBinClient = httpClients.get(clientName);
        int dummy = 0;
        for (int i = 0; i < reps; i++) {
            dummy |= httpBinClient.findIp().hashCode();
        }
        return dummy;
    }

    @Benchmark
    int httpBin_streamLines(int reps) throws IOException {
        HttpBinClient httpBinClient = httpClients.get(clientName);
        int dummy = 0;
        for (int i = 0; i < reps; i++) {
            dummy |= httpBinClient.streamLines(100).hashCode();
        }
        return dummy;
    }
}
