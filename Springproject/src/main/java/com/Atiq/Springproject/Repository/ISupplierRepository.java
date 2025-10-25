package com.Atiq.Springproject.Repository;


import com.Atiq.Springproject.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier,Integer> {



}
