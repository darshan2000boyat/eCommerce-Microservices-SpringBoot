package com.dairydreams.product.Controller;

import com.dairydreams.product.DTO.ProductRequest;
import com.dairydreams.product.DTO.ProductResponse;
import com.dairydreams.product.Entity.ProductEntity;
import com.dairydreams.product.Service.ProductService;
import com.dairydreams.product.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        List<ProductResponse> products = productService.getAllProducts();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "All products fetched...");
        response.put("status", true);
        response.put("data", products);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        try{
            if(id == null){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse("You're missing id param", false, null));
            }
            ProductResponse productResponse = productService.getProductById(id);
            log.info("product {} found successfully", productResponse);
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(new ApiResponse("product found successfully....", true, productResponse));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest product){
       try {
           if(product.getName() == null && product.getName().isBlank() && product.getDescription() == null && product.getDescription().isBlank() && product.getPrice() == null){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Missing required data", false, null));
           }
           ProductResponse data = productService.createProducts(product);
           return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Product has been created successfully...", true, data));
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), false, null));
       }
    }


}
