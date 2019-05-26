package com.example.postgre.service;

import com.example.postgre.domain.Product;
import com.example.postgre.dto.ProductDto;
import com.example.postgre.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> listAll() {
        List<ProductDto> products = new ArrayList<>();
        productRepository.findAll()
                .forEach(p->products
                        .add(ProductDto.builder()
                                .id(p.getId())
                                .description(p.getDescription())
                                .price(p.getPrice())
                                .build()));
        return products;
    }

    @Override
    public ProductDto getById(Long id) {
        return productRepository.findById(id)
                .map(p-> ProductDto.builder()
                        .id(p.getId())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .build())
                .orElse(null);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product productSaved = productRepository.save(new Product(null, productDto.getDescription(), productDto.getPrice()));
        return ProductDto.builder()
                .id(productSaved.getId())
                .description(productSaved.getDescription())
                .price(productSaved.getPrice())
                .build();
    }

    @Override
    public void delete(Long id) {
        productRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Product does not exist " + id));
        productRepository.deleteById(id);

    }
}