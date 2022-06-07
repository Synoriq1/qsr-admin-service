package com.pharmeasy.admin.common.utils;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.pharmeasy.admin.common.utils.EnrollmentConstants.*;

@Component
public class Utils {

    public static boolean isNullOrEmpty(Object obj) {
        return Objects.isNull(obj) || obj.equals("") || (obj instanceof Map && ((Map) obj).isEmpty());
    }

    public static String getExceptionValueFromAPI(HashMap request) throws JSONException {
        return new JSONObject(request.get("response").toString()).get("message").toString();
    }

    // params -> object/keys to set, request type -> get/post , url -> link
    public HashMap<String, Object> sendGetRequest(HashMap<String, Object> params, String url) throws Exception {
        HashMap<String, Object> response = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        URIBuilder uriBuilder = new URIBuilder(url);
        if (Objects.nonNull(params)) {
            params.forEach((k, v) -> {
                uriBuilder.addParameter(k, String.valueOf(v));
            });
            uriBuilder.setCharset(StandardCharsets.UTF_8).build();
        }
        try {
            ResponseEntity responseEntity = new RestTemplate().exchange(uriBuilder.toString(), HttpMethod.GET, entity, String.class);
            JSONObject responseJson = new JSONObject((String) responseEntity.getBody());
            response.put(STATUS, SUCCESS);
            response.put("response", responseJson);
        } catch (HttpClientErrorException e) {
            response.put(STATUS, FAILURE);
            JSONObject responseJson = new JSONObject((String) e.getResponseBodyAsString());
            response.put("response", responseJson);
        }
        return response;
    }

    public HashMap<String, Object> sendPostRequest(Object params, String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Object> response = new HashMap<>();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            String apiResponseString = new RestTemplate().postForObject(url, params, String.class);
            JSONObject responseJson = new JSONObject(apiResponseString);
            response.put(STATUS, SUCCESS);
            response.put("response", responseJson);
        } catch (HttpClientErrorException e) {
            response.put(STATUS, FAILURE);
            JSONObject responseJson = new JSONObject((String) e.getResponseBodyAsString());
            response.put("response", responseJson);
        }
        return response;

    }

    public JSONObject sendPostRequestWithFile(HashMap<String, Object> params1,
                                              String url, File file, String bucket) throws Exception {
        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("File", new FileSystemResource(file));
        params.add("destinationfolder", bucket);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class);
        return new JSONObject(responseEntity.getBody());
    }

    public HashMap<String, Object> sendPutRequest(Object params, String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HashMap<String, Object> response = new HashMap<>();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            HttpEntity<Object> entity = new HttpEntity<Object>(params, headers);
            ResponseEntity<String> responseJson = new RestTemplate().exchange(url, HttpMethod.PUT, entity, String.class);
            response.put(STATUS, SUCCESS);
            response.put("response", responseJson);
        } catch (HttpClientErrorException e) {
            response.put(STATUS, FAILURE);
            JSONObject responseJson = new JSONObject((String) e.getResponseBodyAsString());
            response.put("response", responseJson);
        }
        return response;
    }

    public void sendDeleteRequest(String url) {
        try {
            HashMap<String, Object> response = new HashMap<>();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(url);
        } catch (Exception e) {

        }
    }

    public static boolean checkIsActive(HashMap responseMap) throws Exception {
        String responseStatus = new JSONObject(responseMap.get("response").toString()).getJSONObject("data").get("status").toString();
        if (responseStatus.equals("ACTIVE")) {
            return true;
        } else {
            return false;
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
