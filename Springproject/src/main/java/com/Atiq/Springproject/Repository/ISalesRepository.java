package com.Atiq.Springproject.Repository;


import com.Atiq.Springproject.Entity.Product;
import com.Atiq.Springproject.Entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository


public interface ISalesRepository extends JpaRepository<Sales,Integer> {

    public interface IProductRepository extends JpaRepository<Product, Long> {
        List<Product> findByCategoryName(@Param("categoryName") String categoryName);
    }
}
