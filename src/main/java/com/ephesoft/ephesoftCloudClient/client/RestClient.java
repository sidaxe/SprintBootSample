package com.ephesoft.ephesoftCloudClient.client;

import com.ephesoft.ephesoftCloudClient.Model.Document;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RestClient {

    public static final String JSON_CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;

    private static final String DEFAULT_BASE_URL = "http://localhost:8080/";

    private int DEF_CONNECTION_TIME_OUT = 3000 ;

    private int DEF_READ_TIME_OUT = 3000 ;

    private String contentType = JSON_CONTENT_TYPE;

    private RestTemplate restTemplate ;

    public RestClient(int connectionTimeOut, int socketTimeOut) {
        this.DEF_CONNECTION_TIME_OUT = connectionTimeOut;
        this.DEF_READ_TIME_OUT = socketTimeOut;
        restTemplate = new RestTemplate(getClientHttpRequestFactory());

    }

    public RestClient() {
        restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }

    public <T> T invokeGet(String url , Class<T> classObject ) throws Exception {

        ResponseEntity<T> response = restTemplate.getForEntity(DEFAULT_BASE_URL + url, classObject);
        if( response.getStatusCode().equals(HttpStatus.OK)){
            return response.getBody();
        }
        else{
            throw new Exception("Error while invoking get on url" + url);
        }
    }

    public <R,T> R invokePost(String url , T request , Map<String , String > customHeaders , Class<R> classObject){
        HttpHeaders headers  = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        if(customHeaders != null){
            headers.setAll(customHeaders);
        }
        HttpEntity<MultiValueMap<String, String>> requestObject = new HttpEntity(request, headers);
        ResponseEntity<R> response = restTemplate.postForEntity( DEFAULT_BASE_URL + url, requestObject , classObject);
        return response.getBody();
    }

    public <T> T invokePut(String url , T request , Map<String , String > requestHeaders){
        return null;
    }
    public <T> T invokeDelete(String url , T request , Map<String , String > requestHeaders){
        return null;
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 3000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(DEF_CONNECTION_TIME_OUT)
                .setSocketTimeout(DEF_READ_TIME_OUT)
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);

    }

    private MultiValueMap<String , String> getMultiValueMap(Map<String , String>  requestHeaders){
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        if(requestHeaders != null) {
            for (String key : requestHeaders.keySet()) {
                map.add(key, requestHeaders.get(key));
            }
        }
        return  map;
    }


    public static void main(String ar[]) throws Exception {

        String resourceUrl = "document/getAll";
        RestClient client = new RestClient();
        List<Document> list = client.invokeGet(resourceUrl, List.class);


        System.out.println(list);
        System.out.println("\n\n");

//        Document newdocument = new Document();
//        DocumentDetail detail = new DocumentDetail();
//        detail.setDocumentType("code");
//        detail.setNoOfPages(1);
//        Page page1 = new Page();
//        page1.setData("This is code page1 ");
//        page1.setPageNo(1);
//        newdocument.setDocumentDetail(detail);
//        newdocument.setPage(Arrays.asList(page1));
//        newdocument.setDocumentName("Code Document");
//        Document persisted = client.invokePost("document/",newdocument,null , Document.class);
//        System.out.println(persisted);
    }
}

