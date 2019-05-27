package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.repo.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repo;

    @Test
    public void listAllTest() {
        service.save(new ProductDto(null, "desc1", new BigDecimal(100.00)));
        service.save(new ProductDto(null, "desc2", new BigDecimal(100.00)));
        List<ProductDto> products = service.listAll();
        Assertions.assertThat(products).extracting(ProductDto::getDescription).contains("desc1","desc2");
    }

    @Test
    public void getById() {
        ProductDto current = service.save(new ProductDto(null, "desc", new BigDecimal(100.00)));
        ProductDto expected = service.getById(current.getId());
        Assertions.assertThat(current.getId()).isEqualTo(expected.getId());
    }

    @Test
    public void saveProductWhenNameAndEmailIsValidTest() {
        ProductDto current = service.save(new ProductDto(null, "desc", new BigDecimal(100.00)));
        ProductDto expected = new ProductDto(current.getId(), "desc",  new BigDecimal(100.00));
        Assertions.assertThat(current).isEqualTo(expected);
    }

    @Test
    public void deleteProductWhenIdExistsTest() {
        ProductDto current = service.save(new ProductDto(null, "name", new BigDecimal(100.00)));
        service.delete(current.getId());
        Assertions.assertThat(repo.findById(current.getId())).isNotPresent();
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteProductWhenIdNotExistsTest() {
        service.delete(0L);
    }
}
