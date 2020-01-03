package basic.myOKHttp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 16:33 2019/12/24
 */

public class GetExample {
    private OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    public static void main(String[] args) throws IOException {
        GetExample example = new GetExample();
        String response = example.run("https://github.com/Hongscar/blog/blob/master/README.md");
        System.out.println(response);
    }
}
