package com.example.demoQuanLyXuatHang.controller;

import com.example.demoQuanLyXuatHang.entity.*;
import com.example.demoQuanLyXuatHang.repository.DetailOutBillRepository;
import com.example.demoQuanLyXuatHang.repository.OutBillRepository;
import com.example.demoQuanLyXuatHang.repository.ListProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class OutBillController {

    @Autowired
    OutBillRepository outBillRepository;

    @Autowired
    ListProductRepository listProductRepository;

    @Autowired
    DetailOutBillRepository detaiOutBillRepository;

    @GetMapping("/getAllOutBill")
    @ResponseBody
    public List<DisplayOutBill> getHome(){
//          return new ModelAndView("home","outbills",
//                  outBillRepository.findAllbyMe());
        return outBillRepository.findAllbyMe();
    }
    @GetMapping("/add")
    public String addOutBill(Model model){
     model.addAttribute("outbill",new OutBill());
     return "add";
    }
    @PostMapping("/add")
    public String add(@RequestParam int amount , @RequestParam int idList, @ModelAttribute OutBill outBill, @RequestParam int total , @RequestParam int idBranch , @RequestParam String billStatus,
                      @RequestParam String productName , @RequestParam int idCategory ,@RequestParam String detail,
                      @RequestParam String imageProduct, @RequestParam String unit){
        ListProduct listProduct = new ListProduct( productName,  idCategory,  detail,  imageProduct,  unit);
        OutBill outBill1 = new OutBill(idBranch,billStatus,total);
        DetailOutbill detailOutBill1 = new DetailOutbill(amount);
		detailOutBill1.setOutBill(outBill1);
		detailOutBill1.setProduct(listProduct);
		detailOutBill1.setId(new OutBill_ProductKey());
		outBill.getDetailOutbills().add(detailOutBill1);
		outBillRepository.save(outBill1);
		detaiOutBillRepository.save(detailOutBill1);
		listProductRepository.save(listProduct);
		return "redirect:/";
    }
    @GetMapping("/getId/{id1}")//id outbill
    public DisplayOutBill getOutBillById(@PathVariable(name = "id") int id1 ){
        // return new ModelAndView("getid","outbill",outBillRepository.findById(id).orElse(null));
         List<DisplayOutBill> list = outBillRepository.findAllbyMe();
         for (DisplayOutBill display:list){
             if (display.idOutBill==id1) return display;
         }
         return null;

    }
    @PostMapping("/update/{id1}{id2}") // id1 -outbill // id2-listProduct
    public  String updateOutBill(@PathVariable(name = "id1") int id1 ,@PathVariable(name = "id2") int id2,
   @RequestParam int amount , @RequestParam int idList, @ModelAttribute OutBill outBill, @RequestParam int total , @RequestParam int idBranch , @RequestParam String billStatus,
                                 @RequestParam String productName , @RequestParam int idCategory ,@RequestParam String detail,
                                 @RequestParam String imageProduct, @RequestParam String unit){
        OutBill o = outBillRepository.findById(id1).orElse(null);
        ListProduct listProduct = listProductRepository.findById(id2).orElse(null);
        DetailOutbill detailOutbill = detaiOutBillRepository.findById(new OutBill_ProductKey(id2,id1)).orElse(null);

        o.setIdBranch(idBranch);
        o.setTotal(total);
        o.setBillStatus(billStatus);
        detailOutbill.setAmount(amount);
        listProduct.setDetail(detail);
        listProduct.setImageProduct(imageProduct);
        listProduct.setProductName(productName);
        listProduct.setIdCategory(idCategory);
        listProduct.setUnit(unit);
        outBillRepository.save(o);
        detaiOutBillRepository.save(detailOutbill);
        listProductRepository.save(listProduct);
//        OutBill o = outBillRepository.findById(id).orElse(null);
//        o.setBillStatus(outBill.getBillStatus());
//        o.setCreateTime(new Date());
//        o.setTotal(outBill.getTotal());
//        o.setIdBranch(outBill.getIdBranch());
//        outBillRepository.save(o);
        return "redirect:/";
    }
    @GetMapping("/deleteOutBill/{id}") //id : outbill
    public String deleteOutBill(@PathVariable(name = "id") int id){
        outBillRepository.deleteById(id);
        return "redirect:/";
    }

}
