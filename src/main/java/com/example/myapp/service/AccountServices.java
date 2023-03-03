package com.example.myapp.service;

import java.sql.Connection;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.myapp.entity.AccountInfo;
import com.example.myapp.utils.DBAccUtils;

@Service
public class AccountServices {

	public static ResponseEntity<?> create_account_service(Connection connection, AccountInfo account) throws Exception {
		// validate account data
		// perform business logic
		// save account to database
		boolean status = DBAccUtils.insert_account_info(connection, account);
		ResponseEntity<?> responseEntity = new ResponseEntity<>("{\"status\":\""+ status +"\"}", new HttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}

	public static ResponseEntity<?> update_account_service(Connection connection, AccountInfo account) throws Exception {
		boolean status = DBAccUtils.update_account_info(connection, account);
		ResponseEntity<?> responseEntity = new ResponseEntity<>("{\"status\":\""+ status +"\"}", new HttpHeaders(), HttpStatus.OK);
		return responseEntity;
	}
	
	public static ResponseEntity<?> get_account_phoneno_service(Connection connection, AccountInfo account) throws Exception {
		return new ResponseEntity<>(DBAccUtils.get_account_phoneno(connection, account), new HttpHeaders(), HttpStatus.OK);
	}
	
	
}