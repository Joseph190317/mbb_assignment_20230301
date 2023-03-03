package com.example.myapp.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class callThirdPartyAPI {
	private static final Logger logger = LoggerFactory.getLogger(callThirdPartyAPI.class);
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "{\"channel\":\"None\"}";
	}
	
	@PostMapping(value = "/api/call-3rd-party")
	private ResponseEntity<?> get_3rd_party_hello(@RequestBody String req_body) {
		logger.info("Request_Body: " + req_body.toString());
    	ResponseEntity<?> responseEntity = null;
    	HttpHeaders header = new HttpHeaders();
    	String desc = "";
		try {
			String uri = "http://localhost:8080/welcome";
			JSONObject jsonObj = new JSONObject(req_body);
			int channel = Integer.valueOf(jsonObj.getString("channel"));
			switch (channel) {
			case 1: {
				uri = "https://catfact.ninja/fact";
				desc = "Get random cat facts via text message every day.";
				break;
			}
			case 2: {
				uri = "https://api.coindesk.com/v1/bpi/currentprice.json"; 
				desc = "View the Bitcoin Price Index (BPI) in real-time.";
				break;
			}
			case 3: {
				uri = "https://www.boredapi.com/api/activity"; 
				desc = "Bored is a free API to find something to do by getting suggestions for random activities.";
				break;
			}
			case 4: {
				uri = "https://UnknowURI";
				break;
			}
			default: {
				desc = "This is default channel.";
				break;
			}
			}
			RestTemplate restTemp = new RestTemplate();
			String response = restTemp.getForObject(uri, String.class);
			JSONObject json_resp = new JSONObject(response);
			json_resp.put("channel_description", desc);
			header.setContentType(MediaType.APPLICATION_JSON);
			responseEntity = new ResponseEntity<>(json_resp.toString(), header, HttpStatus.OK);
			
			logger.info("Response_Status: " + responseEntity.getStatusCodeValue());
	    	logger.info("Response_Header: " + responseEntity.getHeaders());
	    	logger.info("Response_Body  : " + responseEntity.getBody());
		} catch (Exception ex) {
			ex.printStackTrace();
			header.setContentType(MediaType.TEXT_PLAIN);
			responseEntity = new ResponseEntity<>("{\"status\":\""+ false +"\", \"desc\":\""+ex.getLocalizedMessage()+"\"}"
					, header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

}