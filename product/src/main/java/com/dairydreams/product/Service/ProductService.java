package com.dairydreams.product.Service;

import com.dairydreams.product.DTO.ProductRequest;
import com.dairydreams.product.DTO.ProductResponse;
import com.dairydreams.product.Entity.ProductEntity;
import com.dairydreams.product.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductResponse createProducts(ProductRequest productRequest){
        try{
            ProductEntity product = ProductEntity.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();

            // Save product
            ProductEntity savedProduct = productRepository.save(product);

            // Map entity to response
            ProductResponse response = ProductResponse.builder()
                    .name(savedProduct.getName())
                    .description(savedProduct.getDescription())
                    .price(savedProduct.getPrice())
                    .build();

            return response;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public ProductResponse getProductById(Long id){
        try {
            Optional<ProductEntity> product = productRepository.findById(id);

            if(product.isPresent()){
                ProductEntity productEntity = product.get();
                ProductResponse productResponse = ProductResponse.builder()
                        .name(productEntity.getName()).
                        description(productEntity.getDescription())
                        .price(productEntity.getPrice())
                        .build();
                return productResponse;
            } else {
                throw new RuntimeException("Product not found with id: " + id);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public List<ProductResponse> getAllProducts(){
        try{
            List<ProductEntity> products = productRepository.findAll();
            return products.stream().map(product -> mapToProductResponseDto(product)).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ProductResponse mapToProductResponseDto(ProductEntity product){
        return ProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
