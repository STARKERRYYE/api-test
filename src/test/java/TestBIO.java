
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class TestBIO {
 private long timestamp;
    // get job id
    public static String getJobId() throws IOException {
        String name = "test1";
        String sequence = "wefsdvmtoiwjg";
        List<String> appl = new ArrayList<>();
        appl.add("Gene3d");
        appl.add("SFLD");

        Map<String, Object> newJobRequestMap = new HashMap<>();
        newJobRequestMap.put("email", "safjha@gmail.com");
        newJobRequestMap.put("title", name);
        newJobRequestMap.put("goterms", true);
        newJobRequestMap.put("pathways", true);
        newJobRequestMap.put("stype", "p");
        newJobRequestMap.put("appl", String.join(",", appl));
        newJobRequestMap.put("sequence", sequence);

        String jobId = makeHttpRequest("https://www.ebi.ac.uk/Tools/services/rest/iprscan5/run", newJobRequestMap, HttpMethod.POST);
        return jobId;
    }

    public static void main(String[] args) throws IOException {
        // get status
//        System.out.println(makeHttpRequest(
//"https://www.ebi.ac.uk/Tools/services/rest/iprscan5/status/iprscan5-R20240501-043539-0265-38570911-p1m", null, HttpMethod.GET));
//
//
//        //new job
//        System.out.println(getJobId());


        //result
        File outfile = File.createTempFile(UUID.randomUUID().toString(), ".xml");
        System.out.println("FilePath: " + outfile.getAbsolutePath());
        String txt = makeHttpRequest("https://www.ebi.ac.uk/Tools/services/rest/iprscan5/result/iprscan5-R20240501-090833-0981-27858348-p1m/json", null, HttpMethod.GET);


        try {
            // 创建FileWriter对象，第二个参数为true表示追加写入，false表示覆盖写入
            FileWriter writer = new FileWriter(outfile.getAbsolutePath(), false);
            writer.write(txt);
            writer.close();
        } catch (IOException ex) {
//            logger.log(Level.SEVERE, null, ex);
//            throw new DocumentOperationException("Write result to file error: " + ex.getMessage());
            System.err.println("写入文件时出现错误：" + ex.getMessage());
        }

//        // 创建 BufferedWriter 对象
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))) {
//            // 写入文本内容到文件
//            writer.write(txt);
//        }

    }


    //map类型和添加method
    private static String makeHttpRequest(String url, Map<String, Object> formData, HttpMethod method) throws IOException {

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
