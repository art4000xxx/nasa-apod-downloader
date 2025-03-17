package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class NasaApodDownloader {

    private static final String NASA_API_KEY = "VJlkY8o71ql3ge1MX0cxqN1fkHYLLdN3fnndKbFQ";
    private static final String NASA_APOD_URL = "https://api.nasa.gov/planetary/apod?api_key=" + NASA_API_KEY;

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build()) {

            HttpGet request = new HttpGet(NASA_APOD_URL);
            CloseableHttpResponse response = httpClient.execute(request);

            ObjectMapper mapper = new ObjectMapper();
            ApodResponse apodResponse = mapper.readValue(response.getEntity().getContent(), ApodResponse.class);

            String imageUrl = apodResponse.getUrl();
            String fileName = extractFileName(imageUrl);

            HttpGet imageRequest = new HttpGet(imageUrl);
            CloseableHttpResponse imageResponse = httpClient.execute(imageRequest);

            byte[] imageBytes = imageResponse.getEntity().getContent().readAllBytes();

            File outputFile = new File(fileName);
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                fos.write(imageBytes);
            }

            System.out.println("Image downloaded successfully: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static String extractFileName(String imageUrl) {
        try {
            String path = new URL(imageUrl).getPath();
            return Paths.get(path).getFileName().toString();
        } catch (MalformedURLException e) {
            System.err.println("Error: Invalid URL format: " + imageUrl);
            return "default_image.jpg";
        }
    }

    static class ApodResponse {
        @JsonProperty("copyright")
        private String copyright;
        @JsonProperty("date")
        private String date;
        @JsonProperty("explanation")
        private String explanation;
        @JsonProperty("hdurl")
        private String hdurl;
        @JsonProperty("media_type")
        private String media_type;
        @JsonProperty("service_version")
        private String service_version;
        @JsonProperty("title")
        private String title;
        @JsonProperty("url")
        private String url;

        public String getCopyright() {
            return copyright;
        }

        public void setHdurl(String hdurl) {
            this.hdurl = hdurl;
        }

        public String getMedia_type() {
            return media_type;
        }

        public void setMedia_type(String media_type) {
            this.media_type = media_type;
        }

        public String getService_version() {
            return service_version;
        }

        public void setService_version(String service_version) {
            this.service_version = service_version;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }}