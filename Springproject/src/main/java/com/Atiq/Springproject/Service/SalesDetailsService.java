package com.Atiq.Springproject.Service;


import com.Atiq.Springproject.Entity.SalesDetails;
import com.Atiq.Springproject.Repository.ISalesDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesDetailsService {


    @Autowired
    private ISalesDetailsRepository salesDetailRepository;


    public List<SalesDetails> getAllSalesDetails() {

        return salesDetailRepository.findAll();
    }

    public Map<Integer, List<SalesDetails>> getSalesDetailsGroupedBySalesId() {
        List<SalesDetails> allSalesDetails = salesDetailRepository.findAll();

        // Group by sales ID
        return allSalesDetails.stream()
                .collect(Collectors.groupingBy(sd -> sd.getSale().getId()));
    }



}
