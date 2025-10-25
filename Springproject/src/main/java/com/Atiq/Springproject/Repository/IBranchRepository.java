package com.Atiq.Springproject.Repository;


import com.Atiq.Springproject.Entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface IBranchRepository extends JpaRepository<Branch ,Integer> {


}
