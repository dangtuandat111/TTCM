package com.example.demoQuanLyXuatHang.controller;

import com.example.demoQuanLyXuatHang.entity.DisplayProduct;
import com.example.demoQuanLyXuatHang.entity.ListProduct;
import com.example.demoQuanLyXuatHang.entity.Product;
import com.example.demoQuanLyXuatHang.repository.ListProductRepository;
import com.example.demoQuanLyXuatHang.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ListProductRepository listProductRepository;
    @GetMapping("/getAllProduct")
    @ResponseBody
    public List<DisplayProduct> getAllProduct (){
        return productRepository.findAllProduct();
    }
    @PostMapping("/addProduct")
    public String addProduct(@RequestParam(defaultValue = "stocking") String statusProduct, @RequestParam int amount , @RequestParam int idList ){
        Product product = new Product(amount,idList,statusProduct);
        productRepository.save(product);
        return "redirect:/getAllProduct";

    }
    @PostMapping("/updateProduct")
    public  String updateProduct(@RequestParam int idProduct,@RequestParam String nameProduct , @RequestParam String detail
            ,@RequestParam String imageProduct, @RequestParam String statusProduct){
      Product product= productRepository.findById(idProduct).orElse(null);
        ListProduct listProduct = listProductRepository.findById(product.getIdList()).orElse(null);
       product.setStatusProduct(statusProduct);
       listProduct.setProductName(nameProduct);
       listProduct.setDetail(detail);
       listProduct.setImageProduct(imageProduct);
       productRepository.save(product);
       listProductRepository.save(listProduct);
        return "redirect:/getAllProduct";
    }
    @RequestMapping(value = "/deleteProduct/{id}")
    public String deleteProduct (@PathVariable(name = "id") int id ){
        productRepository.deleteById(id);
        return "redirect:/getAllProduct";
    }
}
