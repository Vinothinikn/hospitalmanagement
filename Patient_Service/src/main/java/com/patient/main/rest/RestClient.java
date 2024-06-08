package com.patient.main.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestClient {

	@Autowired
	RestTemplate restTemplate;
	
	 public <T, R> R makePostRequest(String baseUrl, T requestBody, Class<R> responseType) {

	        // Set headers with basic authentication
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "application/json");
	        headers.set("Authorization", "Basic " + Base64Utils.encodeToString("username:password".getBytes()));

	        // Create the request entity
	        HttpEntity<T> requestEntity = new HttpEntity<>(requestBody, headers);

	        // Ensure the base URL is properly encoded
	        String uri = UriComponentsBuilder.fromHttpUrl(baseUrl).toUriString();

	        // Make the POST request
	        ResponseEntity<R> responseEntity = restTemplate.exchange(
	                uri,
	                HttpMethod.POST,
	                requestEntity,
	                responseType
	        );

	        // Return the response body
	        return responseEntity.getBody();
	    }
	 
	 public <R> ResponseEntity<List<R>> makeGetRequestForList(String baseUrl) {
		    // Set headers with basic authentication
		    HttpHeaders headers = new HttpHeaders();
		    headers.set("Content-Type", "application/json");
		    headers.set("Authorization", "Basic " + Base64Utils.encodeToString("username:password".getBytes()));

		    // Create the request entity
		    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		    // Ensure the base URL is properly encoded
		    String uri = UriComponentsBuilder.fromHttpUrl(baseUrl).toUriString();

		    // Make the GET request
		    ResponseEntity<List<R>> responseEntity = restTemplate.exchange(
		            uri,
		            HttpMethod.GET,
		            requestEntity,
		            new ParameterizedTypeReference<List<R>>() {}
		    );

		    // Return the response entity
		    return responseEntity;
		}
}
