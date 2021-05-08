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

@RestController
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
        List<DisplayOutBill> list = outBillRepository.findAllbyMe();
        for(DisplayOutBill display : list){
            System.out.println(display.toString());
        }
        return outBillRepository.findAllbyMe();
    }
    @GetMapping("/add")
    public String addOutBill(Model model){
     model.addAttribute("outbill",new OutBill());
     return "add";
    }
    @PostMapping("/add")
    public String add(@RequestParam(defaultValue = "100") int amount ,  @RequestParam(defaultValue = "10") int total , @RequestParam int idBranch , @RequestParam(defaultValue = "process") String billStatus,
                      @RequestParam(defaultValue = " ") String productName , @RequestParam(defaultValue = "1") int idCategory ,@RequestParam(defaultValue = " ") String detail,
                      @RequestParam(defaultValue = " ") String imageProduct, @RequestParam(defaultValue = " ") String unit ,@RequestParam int[] idProducts){
        ListProduct listProduct = new ListProduct( productName,  idCategory,  detail,  imageProduct,  unit);
        for (int i : idProducts){
            listProduct.getProducts().add(i);
        }
        OutBill outBill1 = new OutBill(idBranch,billStatus,total);
        DetailOutbill detailOutBill1 = new DetailOutbill(amount);
		detailOutBill1.setOutBill(outBill1);
		detailOutBill1.setListProduct(listProduct);
		detailOutBill1.setId(new OutBill_ProductKey());
		outBill1.getDetailOutbills().add(detailOutBill1);
		outBillRepository.save(outBill1);
		detaiOutBillRepository.save(detailOutBill1);
		listProductRepository.save(listProduct);
		return "redirect:/getAllOutBill";
    }
    @GetMapping("/getId")//id1 outbill  // id2 listproduct (của DisplayOutBill)
    public DisplayOutBill getOutBillById(@RequestParam int id1,@RequestParam int id2 ){
        // return new ModelAndView("getid","outbill",outBillRepository.findById(id).orElse(null));
         List<DisplayOutBill> list = outBillRepository.findAllbyMe();
         for (DisplayOutBill display:list){
             if (display.idOutBill==id1&&display.idListProduct==id2) return display;
         }
         return null;

    }
    @PostMapping("/update") // id1 -outbill // id2-listProduct
    public  String updateOutBill(@RequestParam int id1 , @RequestParam String billStatus){
        OutBill o = outBillRepository.findById(id1).orElse(null);
        o.setBillStatus(billStatus);
        outBillRepository.save(o);

//        OutBill o = outBillRepository.findById(id1).orElse(null);
//        ListProduct listProduct = listProductRepository.findById(id2).orElse(null);
//        DetailOutbill detailOutbill = detaiOutBillRepository.findById(new OutBill_ProductKey(id2,id1)).orElse(null);

//        List<OutBill> o = outBillRepository.findOutBillByIdAndIdListProduct(id1,id2);
//        ListProduct listProduct = listProductRepository.findListProductByIdAndIdOutBill(id2,id1);
//        DetailOutbill detailOutbill = detaiOutBillRepository.findById(new OutBill_ProductKey(id2,id1)).orElse(null);
//        for (OutBill outBill : o){
//            System.out.println(outBill.toString());
//        }
//        System.out.println(listProduct.toString());
//        System.out.println(detailOutbill.toString());
//        o.setIdBranch(idBranch);
//        o.setTotal(total);
//        o.setBillStatus(billStatus);
//        detailOutbill.setAmount(amount);
//        listProduct.setDetail(detail);
//        listProduct.setImageProduct(imageProduct);
//        listProduct.setProductName(productName);
//        listProduct.setIdCategory(idCategory);
//        listProduct.setUnit(unit);
//        outBillRepository.save(o);
//        detaiOutBillRepository.save(detailOutbill);
//        listProductRepository.save(listProduct);
//        OutBill o = outBillRepository.findById(id).orElse(null);
//        o.setBillStatus(outBill.getBillStatus());
//        o.setCreateTime(new Date());
//        o.setTotal(outBill.getTotal());
//        o.setIdBranch(outBill.getIdBranch());
//        outBillRepository.save(o);
        return "redirect:/getAllOutBill";
    }
    @GetMapping("/deleteOutBill/{id}") //id : outbill
    public String deleteOutBill(@PathVariable(name = "id") int id){
        outBillRepository.deleteById(id);
        return "redirect:/";
    }

}
