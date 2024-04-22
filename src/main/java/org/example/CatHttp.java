package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CatHttp {
    HttpClient client;

    public CatHttp() {
        client = HttpClient.newHttpClient();
    }

    public String getCat(String url) {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> res = client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .join();
        String jsonbody = res.body();

        Gson gson = new Gson();

        String first = Arrays.stream(gson.fromJson(jsonbody, JsonObject[].class))
                .findFirst()
                .get()
                .toString();
        JsonParser parser = new JsonParser();

        JsonObject jsonObject = parser.parse(first).getAsJsonObject();

        String imageUrl = jsonObject.get("url").getAsString();

        return imageUrl;
    }

    public void Downloadimage(String catUrl) {
        String destination = "cat.jpg";

        try {
            URL url = URI.create(catUrl).toURL();
            InputStream input = url.openStream();
            FileOutputStream output = new FileOutputStream(destination);

            byte[] buffer = new byte[4096];
            int length;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }

            input.close();
            output.close();

            System.out.println("Image successfully downloaded to " + destination);

        }catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
