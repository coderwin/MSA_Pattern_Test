package com.example.productservice.controller;

import com.example.productservice.dto.ProductResponse;
import com.example.productservice.entity.Category;
import com.example.productservice.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductViewController {
    private final ProductService service;
    private final ConvertTestService convertTestService;
    @GetMapping("/getproductlist")
    public String getStores(Model model){
        List<ProductResponse> productlist =   service.findAll();
        System.out.println(productlist);
        model.addAttribute("productlist",productlist);
        return "product/list";
    }
    @GetMapping("/test")
    @ResponseBody
    public String Test() throws JsonProcessingException {
        int year = 2020, month = 5, dayOfMonth = 16, hour = 6, minute = 24, second = 44;
        LocalDateTime of = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        String data = convertTestService.serializeStoreDto(
                new ProductResponse(1L, "사과", "과일", 10000, "dd",
                        of, new Category()));
        return  data;
    }
}
