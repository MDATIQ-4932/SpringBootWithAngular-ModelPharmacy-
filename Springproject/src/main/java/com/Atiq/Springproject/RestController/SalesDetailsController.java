package com.Atiq.Springproject.RestController;


import com.Atiq.Springproject.Entity.SalesDetails;
import com.Atiq.Springproject.Service.SalesDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/salesdetails")




public class SalesDetailsController {


    @Autowired
    private SalesDetailsService salesDetailsService;

    @GetMapping("/")
    public List<SalesDetails> getAllSales(){

        return salesDetailsService.getAllSalesDetails();
    }

    @GetMapping("/grouped")
    public ResponseEntity<Map<Integer, List<SalesDetails>>> getGroupedSalesDetails() {
        Map<Integer, List<SalesDetails>> groupedSalesDetails = salesDetailsService.getSalesDetailsGroupedBySalesId();
        return ResponseEntity.ok(groupedSalesDetails);
    }

}
