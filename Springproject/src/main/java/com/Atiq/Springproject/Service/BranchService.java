package com.Atiq.Springproject.Service;


import com.Atiq.Springproject.Entity.Branch;
import com.Atiq.Springproject.Repository.IBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private IBranchRepository branchRepository;



    public void saveBranch(Branch b) {

        branchRepository.save(b);
    }

    public List<Branch> getAllBranch() {

        return branchRepository.findAll();
    }

    public void deleteBranchById(int id) {

        branchRepository.deleteById(id);
    }

    public Branch findByid(int id) {
        return branchRepository.findById(id).get();
    }



    public Branch updateBranch(Branch b, int id) {

        return branchRepository.save(b);
    }


}
