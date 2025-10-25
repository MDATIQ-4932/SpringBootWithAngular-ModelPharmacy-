package com.Atiq.Springproject.Repository;


import com.Atiq.Springproject.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface ICategoryRepository extends JpaRepository<Category,Integer> {


}
