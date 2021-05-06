package com.example.demoQuanLyXuatHang.controller;

import com.example.demoQuanLyXuatHang.entity.DetailOutbill;
import com.example.demoQuanLyXuatHang.entity.OutBill;
import com.example.demoQuanLyXuatHang.entity.OutBill_ProductKey;
import com.example.demoQuanLyXuatHang.entity.Product;
import com.example.demoQuanLyXuatHang.repository.DetailOutBillRepository;
import com.example.demoQuanLyXuatHang.repository.OutBillRepository;
import com.example.demoQuanLyXuatHang.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class OutBillController {

    @Autowired
    OutBillRepository outBillRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DetailOutBillRepository detaiOutBillRepository;

    @GetMapping
    public ModelAndView getHome(){
          return new ModelAndView("home","outbills",outBillRepository.findAllbyMe());
    }
    @GetMapping("/add")
    public String addOutBill(Model model){
     model.addAttribute("outbill",new OutBill());
     return "add";
    }
    @PostMapping("/add")
    public String add(@RequestParam int amount , @RequestParam int idList, @ModelAttribute OutBill outBill){
        Product product = new Product(amount,idList);
        DetailOutbill detailOutBill1 = new DetailOutbill();
		detailOutBill1.setOutBill(outBill);
		detailOutBill1.setProduct(product);
		detailOutBill1.setAmount(product.getAmount());
		detailOutBill1.setId(new OutBill_ProductKey());
		outBill.getDetailOutbills().add(detailOutBill1);
		outBillRepository.save(outBill);
		detaiOutBillRepository.save(detailOutBill1);
		productRepository.save(product);
		return "redirect:/";
    }
    @GetMapping("/getId/{id}")
    public ModelAndView getOutBillById(@PathVariable(name = "id") int id ){
          return new ModelAndView("getid","outbill",outBillRepository.findById(id).orElse(null));
    }
    @PostMapping("/update/{id}")
    public  String updateOutBill(@PathVariable(name = "id") int id ,@ModelAttribute OutBill outBill){
        OutBill o = outBillRepository.findById(id).orElse(null);
        o.setBillStatus(outBill.getBillStatus());
        o.setCreateTime(new Date());
        o.setTotal(outBill.getTotal());
        o.setIdBranch(outBill.getIdBranch());
        outBillRepository.save(o);
        return "redirect:/";
    }
    @GetMapping("/deleteOutBill/{id}")
    public String deleteOutBill(@PathVariable(name = "id") int id){
        outBillRepository.deleteById(id);
        return "redirect:/";
    }

}
