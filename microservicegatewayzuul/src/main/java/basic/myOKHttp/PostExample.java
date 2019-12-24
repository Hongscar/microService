package basic.myOKHttp;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

/**
 * @Author: Seth
 * @Description:
 * @Date: Created in 16:49 2019/12/24
 */

public class PostExample {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':2367702411696,"
                + "'dateStarted':2367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[101,8,6,7,8],'color':-13388315,'total':2339},"
                + "{'name':'" + player2 + "','history':[6,10,51,10,10],'color':-48060,'total':12341}"
                + "]}";
    }

    public static void main(String[] args) throws IOException {
        PostExample example = new PostExample();
        String json = example.bowlingJson("Jesse", "Jake");
        String response = example.post("http://www.roundsapp.com/post", json);
        System.out.println(response);
    }

}
