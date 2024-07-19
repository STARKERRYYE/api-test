package com.example.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class Runner {
    private static final List<UUID> RUNNERS = Collections.synchronizedList(new ArrayList<>());


    public String submitNewJob(String sequence, String title) {
        log.info(String.format("Http request get an error code: %s, url: %s", sequence, title));
        print();
        return "new job";
    }
    private void print() {
        Runner.RUNNERS.add(UUID.randomUUID());
        System.out.println(Runner.RUNNERS);
    }


    public static final class Job {
        public final String jobid;
        public final String name;
        public final int sequenceLength;

        public Job(String jobid, String name, int sequenceLength) {
            this.jobid = jobid;
            this.name = name;
            this.sequenceLength = sequenceLength;
        }

        public String checkStatus() {
            return "";
        }

    }

    public static String makeHttpRequest(String url, Map<String, Object> formData, HttpMethod method) throws IOException {

        // Create a URL object
        URL obj = new URL(url);

        // Open a connection to the URL
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        CloseableHttpClient c = HttpClients.createDefault();

        // Set the request method
        con.setRequestMethod(method.name());

        // Enable input streams
        con.setDoInput(true);

        // Set the content type of the request
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // set requestBody
        if (formData != null && !formData.isEmpty()) {
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(getFormDataAsBytes(formData));
            os.flush();
            os.close();
        }

        // Get the response code
        int responseCode = con.getResponseCode();
        BufferedReader in = null;
        if (responseCode != 200) {
//            con.disconnect();
//            logger.
//            throw new IllegalStateException("Http Error Code: " + responseCode);
            in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        } else {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }

        // Read the response from the server

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //xiugai  tianjia
        con.disconnect();

        return response.toString();
    }

    private static byte[] getFormDataAsBytes(Map<String, Object> formData) {
        return formData.entrySet().stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue().toString(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&")).getBytes();
    }
}
