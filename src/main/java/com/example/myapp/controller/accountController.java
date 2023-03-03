package com.example.myapp.controller;

import com.example.myapp.entity.AccountInfo;
import com.example.myapp.service.AccountServices;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class accountController {
	private static final Logger logger = LoggerFactory.getLogger(accountController.class);
	
	@Autowired
	DataSource dataSource;
	
    @PostMapping("/api/create-user-account")
    public ResponseEntity<?> create_user_account (@RequestBody AccountInfo acc_info) {
    	logger.info("Request_Body: " + acc_info.toString());
    	//return ResponseEntity.ok("halo"); 
    	ResponseEntity<?> responseEntity = null;
    	HttpHeaders header = new HttpHeaders();
    	try {
    		responseEntity = AccountServices.create_account_service(dataSource.getConnection(), acc_info);
		} catch (Exception ex) {
			ex.printStackTrace();
			header.setContentType(MediaType.TEXT_PLAIN);
			responseEntity = new ResponseEntity<>("{\"status\":\""+ false +"\", \"desc\":\""+ex.getLocalizedMessage()+"\"}"
				, header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	logger.info("Response_Status: " + responseEntity.getStatusCodeValue());
    	logger.info("Response_Header: " + responseEntity.getHeaders());
    	logger.info("Response_Body  : " + responseEntity.getBody());
    	return responseEntity;
    }

    @PostMapping("/api/update-user-account")
    public ResponseEntity<?> update_user_account (@RequestBody AccountInfo acc_info) {
    	logger.info("Request_Body: " + acc_info.toString());
    	ResponseEntity<?> responseEntity = null;
    	HttpHeaders header = new HttpHeaders();
    	try {
    		responseEntity = AccountServices.update_account_service(dataSource.getConnection(), acc_info);
		} catch (Exception ex) {
			ex.printStackTrace();
			header.setContentType(MediaType.TEXT_PLAIN);
			responseEntity = new ResponseEntity<>("{\"status\":\""+ false +"\", \"desc\":\""+ex.getLocalizedMessage()+"\"}"
				, header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	logger.info("Response_Status: " + responseEntity.getStatusCodeValue());
    	logger.info("Response_Header: " + responseEntity.getHeaders());
    	logger.info("Response_Body  : " + responseEntity.getBody());
    	return responseEntity;
    }
    
    @GetMapping("/api/get-user-account-phone-no")
    public ResponseEntity<?> get_user_account_phone_no (@RequestBody AccountInfo acc_info) {
    	logger.info("Request_Body: " + acc_info.toString());
    	ResponseEntity<?> responseEntity = null;
    	HttpHeaders header = new HttpHeaders();
    	try {
    		responseEntity = AccountServices.get_account_phoneno_service(dataSource.getConnection(), acc_info);
		} catch (Exception ex) {
			ex.printStackTrace();
			header.setContentType(MediaType.TEXT_PLAIN);
			responseEntity = new ResponseEntity<>(ex.getLocalizedMessage()
					, header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	logger.info("Response_Status: " + responseEntity.getStatusCodeValue());
    	logger.info("Response_Header: " + responseEntity.getHeaders());
    	logger.info("Response_Body  : " + responseEntity.getBody());
    	return responseEntity;
    }
}