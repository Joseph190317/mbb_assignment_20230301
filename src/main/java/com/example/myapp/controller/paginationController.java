package com.example.myapp.controller;

import com.example.myapp.entity.Product;
import com.example.myapp.service.ProductServices;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/get-product-pagination")
public class paginationController {
	
	private static final Logger logger = LoggerFactory.getLogger(paginationController.class);
	
    @Autowired
    private ProductServices productServices;
    
    @GetMapping
    public Page<Product> getProducts(@PageableDefault(page=0, size=10) Pageable pageRequest) {
    	logger.info("Request_Body page: " + pageRequest.getPageNumber());
    	logger.info("Request_Body size: " + pageRequest.getPageSize());
        Page<Product> products = productServices.getProductInfo(pageRequest);
		List<Product> resultList = products.getContent();
		Page<Product> productsDtoPage = new PageImpl<>(resultList, pageRequest, products.getTotalElements());
		logger.info("Response_Body: " + productsDtoPage.getContent().toString());
        return productsDtoPage;
    }
}