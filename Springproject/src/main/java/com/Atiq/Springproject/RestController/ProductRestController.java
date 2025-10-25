package com.Atiq.Springproject.RestController;


import com.Atiq.Springproject.Entity.Product;
import com.Atiq.Springproject.Entity.Sales;
import com.Atiq.Springproject.Repository.IProductRepository;
import com.Atiq.Springproject.Service.ProductService;
import com.Atiq.Springproject.Service.SalesService;
import com.Atiq.Springproject.Util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SalesService salesService;
//    @Autowired
//    private BonaniBranceSalesService bonaniBranceSalesService;





    @PostMapping("/save")
    public ApiResponse saveProduct(
            @RequestPart(value = "product") Product product,
            @RequestParam(value = "medicines", required = true) MultipartFile file
    ) throws IOException {
        ApiResponse apiResponse = productService.saveProduct(product, file);
        return apiResponse;


    }



    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){

        return  ResponseEntity.ok(productService.getAllProduct());

    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable int categoryId) {
        return productService.findByCategory_Id(categoryId);
    }



    @GetMapping("/h/searchproduct")
    public List<Product> getByCategoryName(@RequestParam String categoryName) {
        return productService.findProductByCategoryName(categoryName);
    }


    @GetMapping("/branch/{branchName}")
    public List<Product> getProductsByBranchName(@PathVariable String branchName) {
        return productService.findByBranchName(branchName);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(product);
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestPart("product") Product product,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {

        Product updatedProduct = productService.updateProduct( product, id, imageFile);

        return ResponseEntity.ok(updatedProduct);
    }








}
