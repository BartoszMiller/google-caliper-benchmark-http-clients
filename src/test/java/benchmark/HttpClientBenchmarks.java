package benchmark;

import benchmark.api.LocalhostClient;
import benchmark.client.ApacheHttpClient;
import benchmark.client.NetflixFeignClient;
import benchmark.client.OkHttpClient;
import benchmark.client.RetrofitClient;
import benchmark.model.greeting.Greeting;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.io.IOException;

public class HttpClientBenchmarks {

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    static LocalhostClient apache;
    static LocalhostClient okHttp;
    static LocalhostClient retrofit;
    static LocalhostClient feign;

    static Greeting greeting;

    @BeforeClass
    public static void prepare() {
        apache = new ApacheHttpClient();
        okHttp = new OkHttpClient();
        retrofit = new RetrofitClient();
        feign = new NetflixFeignClient();
    }


    @BenchmarkOptions(benchmarkRounds = 20, warmupRounds = 20, concurrency = -1)
    @Test
    public void apache_greet() throws IOException {
        greeting = apache.greet();
    }

    @BenchmarkOptions(benchmarkRounds = 20, warmupRounds = 20, concurrency = -1)
    @Test
    public void okHttp_greet() throws IOException {
        greeting = okHttp.greet();
    }

    @BenchmarkOptions(benchmarkRounds = 20, warmupRounds = 20, concurrency = -1)
    @Test
    public void retrofit_greet() throws IOException {
        greeting = retrofit.greet();
    }

    @BenchmarkOptions(benchmarkRounds = 20, warmupRounds = 20, concurrency = -1)
    @Test
    public void feign_greet() throws IOException {
        greeting = feign.greet();
    }
}
