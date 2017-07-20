package benchmark.model.httpbin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAgent {

    private String userAgent;

    @JsonCreator
    public UserAgent(@JsonProperty("user-agent") String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
