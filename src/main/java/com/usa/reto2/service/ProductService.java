package com.usa.reto2.service;

import com.usa.reto2.model.Product;
import com.usa.reto2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(String reference){
        return productRepository.getProduct(reference);
    }

    public Product create(Product product){
        if (product.getReference() == null){
            return product;
        }else {
            return productRepository.create(product);
        }
    }

    public Product update(Product product){
        if (product.getReference() != null){
            Optional<Product> productDb = productRepository.getProduct(product.getReference());
            if (!productDb.isEmpty()){

                if (product.getBrand() != null){
                    productDb.get().setBrand(product.getBrand());
                }
                if (product.getCategory() != null){
                    productDb.get().setCategory(product.getCategory());
                }
                if (product.getDescription() != null){
                    productDb.get().setDescription(product.getDescription());
                }
                if (product.getPrice() != 0.0){
                    productDb.get().setPrice(product.getPrice());
                }
                if (product.getQuantity() != 0.0){
                    productDb.get().setQuantity(product.getQuantity());
                }
                if (product.getPhotography() != null){
                    productDb.get().setPhotography(product.getPhotography());
                }
                productDb.get().setAvailability(product.isAvailability());
                productRepository.update(productDb.get());
                return productDb.get();
            }else {
                return product;
            }
        }else{
            return product;
        }
    }

    public boolean delete(String reference){
        Boolean aBoolean = getProduct(reference).map(product ->{
            productRepository.delete(product);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
