package ebus.model;
import java.io.IOException;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SendFileEmail {
    public static String send(String[] emailArr, String subject, String message) throws IOException {
        OkHttpClient client = new OkHttpClient();
        okhttp3.MultipartBody.Builder requestBuilder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
        requestBuilder.addFormDataPart("from", "KMUTT-e-bus-plus <postmaster@e-bus.pureewat.me>");
        requestBuilder.addFormDataPart("subject", subject);
        requestBuilder.addFormDataPart("text", message);
        for(String sendEach : emailArr) {
            requestBuilder.addFormDataPart("to", sendEach);
        }
        RequestBody requestBody = requestBuilder.build();
        Request request = new Request.Builder()
                        .addHeader("content-type", "multipart/form-data")
                        .addHeader("authorization", "Basic YXBpOmtleS0wODYyZWViNjBhOTdkYWFjOThlODc5ZTliYTVmOTA5ZQ==")
                        .url("https://api.mailgun.net/v3/e-bus.pureewat.me" + "/messages")
                        .method("POST", requestBody)
                        .post(requestBody)
                        .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public static void main(String[] args) throws IOException {
        String[] mail = {"nattapat27@gmail.com"};
        send(mail, "test", "test");
    }
}