package com.Atiq.Springproject.Service;


import com.Atiq.Springproject.Entity.Branch;
import com.Atiq.Springproject.Entity.Category;
import com.Atiq.Springproject.Entity.Product;
import com.Atiq.Springproject.Entity.Supplier;
import com.Atiq.Springproject.Repository.IBranchRepository;
import com.Atiq.Springproject.Repository.ICategoryRepository;
import com.Atiq.Springproject.Repository.IProductRepository;
import com.Atiq.Springproject.Repository.ISupplierRepository;
import com.Atiq.Springproject.Util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service


public class ProductService {

    @Autowired
    private IProductRepository productRepository;


    @Autowired
    private ICategoryRepository categoryRepository;


    @Autowired
    private ISupplierRepository supplierRepository;


    @Autowired
    private IBranchRepository branchRepository;




    @Value("src/main/resources/static/images/medicines")
    private String uploadDir;


    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

//    public void saveProduct(Product product, MultipartFile imageFile) throws IOException {
//
//        Category category = categoryRepository.findById(product.getCategory().getId())
//                .orElseThrow(() -> new RuntimeException("Category with this id not found"));
//
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String imageFileName = saveImage(imageFile, product);
//            product.setPhoto(imageFileName);
//        }
//
//        productRepository.save(product);
//    }


    public ApiResponse saveProduct(Product product, MultipartFile imageFile) throws IOException {
        ApiResponse apiResponse = new ApiResponse();

        try {
            // Validate essential fields
            if (product.getName() == null || product.getCategory() == null || product.getBranch() == null) {
                throw new RuntimeException("Product name, category, and branch are required.");
            }

            // Validate and set Category, Supplier, and Branch
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category with this id not found."));
            product.setCategory(category);

            Supplier supplier = supplierRepository.findById(product.getSupplier().getId())
                    .orElseThrow(() -> new RuntimeException("Supplier with this id not found."));
            product.setSupplier(supplier);

            Branch branch = branchRepository.findById(product.getBranch().getId())
                    .orElseThrow(() -> new RuntimeException("Branch with this id not found."));
            product.setBranch(branch);

            // Check for existing products with the same name and branch
            List<Product> existingProducts = productRepository.findProductByNameAndBranch(product.getName(), product.getBranch().getBranchName());
            if (!existingProducts.isEmpty()) {
                // Update stock and unit price for existing product
                Product existingProduct = existingProducts.get(0);
                existingProduct.setStock(existingProduct.getStock() + product.getStock());
                existingProduct.setUnitprice(product.getUnitprice());

                // Update image if provided
                if (imageFile != null && !imageFile.isEmpty()) {
                    String imageFileName = saveImage(imageFile, existingProduct);
                    existingProduct.setPhoto(imageFileName);
                }

                productRepository.save(existingProduct);
                apiResponse.setSuccessful(true);
                apiResponse.setMessage("Product already exists in the same branch. Stock updated.");
            } else {
                // Save a new product
                if (imageFile != null && !imageFile.isEmpty()) {
                    String imageFileName = saveImage(imageFile, product);
                    product.setPhoto(imageFileName);
                }

                productRepository.save(product);
                apiResponse.setSuccessful(true);
                apiResponse.setMessage("Product saved successfully.");
            }
        } catch (RuntimeException e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccessful(false);
        } catch (Exception e) {
            apiResponse.setMessage("An unexpected error occurred: " + e.getMessage());
            apiResponse.setSuccessful(false);
        }

        return apiResponse;
    }



    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public Product findProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product With This Id Not Found"));
    }



    public List<Product>findProductByCategoryName(String categoryName){
        return productRepository.findProductByCategoryName(categoryName);
    }

    public List<Product>findProductByName(String productName){
        return productRepository.findProductByName(productName);
    }

    public List<Product> findProductByNameAndBranch(String productName, String branchName) {
        return productRepository.findProductByNameAndBranch(productName, branchName);
    }


    public List<Product> findByBranchName(String branchName) {
        return productRepository.findProductByBranchName(branchName);
    }


    public Product updateProduct(Product product, int id, MultipartFile file) throws IOException {
        Product existingProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        // Update product details
        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setUnitprice(product.getUnitprice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setSupplier(product.getSupplier());
        existingProduct.setManufactureDate(product.getManufactureDate());
        existingProduct.setExpiryDate(product.getExpiryDate());

        // Update the stock: Add the incoming stock to the existing stock

        existingProduct.setStock(product.getStock());

        // If a new image file is provided, save it
        if (file != null && !file.isEmpty()) {
            String imageFileName = this.saveImage(file, existingProduct);
            existingProduct.setPhoto(imageFileName);
        }

        // Save the updated product
        return this.productRepository.save(existingProduct);
    }





    public String saveImage(MultipartFile file, Product product) throws IOException {
        Path uploadPath = Paths.get(uploadDir + "/product");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Sanitize file name
        String filename = product.getName().replaceAll("[^a-zA-Z0-9.-]", "_") + "_" + UUID.randomUUID().toString();
        Path filePath = uploadPath.resolve(filename);

        Files.copy(file.getInputStream(), filePath);

        return filename;
    }

    public List<Product> findByCategory_Id(int category) {
        return productRepository.findByCategory_Id(category);
    }







}
