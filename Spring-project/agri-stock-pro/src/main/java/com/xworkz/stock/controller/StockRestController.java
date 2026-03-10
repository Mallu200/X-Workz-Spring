package com.xworkz.stock.controller;

import com.xworkz.stock.dto.StockDTO;
import com.xworkz.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockRestController {

    @Autowired
    private StockService service;

    // Returns the complete stock inventory in JSON format for external API consumers.
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StockDTO> getJsonStock() {
        System.out.println("59-REST: API request received for JSON stock data.");
        try {
            List<StockDTO> list = service.getAllStock();
            System.out.println("60-REST: Successfully serialized " + list.size() + " items to JSON.");
            return list;
        } catch (Exception e) {
            // Returns an empty list to prevent the API from crashing during a failure
            System.err.println("61-REST: Error during JSON serialization: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}