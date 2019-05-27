package com.example.postgresql.web;

import com.example.postgresql.dto.ProductDto;
import com.example.postgresql.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/product/list";
    }

    @RequestMapping(path = {"/product/list", "/product"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> listProducts(Model model){
        return productService.listAll();
    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProduct(@PathVariable String id){
        return productService.getById(Long.valueOf(id));
    }

    @RequestMapping(path = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto newProduct(@RequestBody @Validated ProductDto productDto) {
        return productService.save(productDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "product/{id}")
    public void delete(@PathVariable String id){
        productService.delete(Long.valueOf(id));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}