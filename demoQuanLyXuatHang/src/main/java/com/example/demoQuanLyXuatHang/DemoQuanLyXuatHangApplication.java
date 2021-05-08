package com.example.demoQuanLyXuatHang;

import com.example.demoQuanLyXuatHang.entity.DetailOutbill;
import com.example.demoQuanLyXuatHang.entity.ListProduct;
import com.example.demoQuanLyXuatHang.entity.OutBill;
import com.example.demoQuanLyXuatHang.entity.OutBill_ProductKey;
import com.example.demoQuanLyXuatHang.repository.DetailOutBillRepository;
import com.example.demoQuanLyXuatHang.repository.OutBillRepository;
import com.example.demoQuanLyXuatHang.repository.ListProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoQuanLyXuatHangApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoQuanLyXuatHangApplication.class, args);
	}

//	@Autowired
//	OutBillRepository outBillRepository;
//
//	@Autowired
//    ListProductRepository listProductRepository;
//
//	@Autowired
//	DetailOutBillRepository detaiOutBillRepository;
	@Override
	public void run(String... args) throws Exception {
//		ListProduct listProduct1 = new ListProduct( "ca ngu dai duong",  1,  "hai san",  "D/image",  "ttt");
//		ListProduct listProduct2 = new ListProduct( "Tom Hum",  1,  "hai san",  "D/image/haisan",  "aaa");
//
//		OutBill outBill1 = new OutBill(1,"ok",10);
//		OutBill outBill2 = new OutBill(2,"finished",3);
//
//		DetailOutbill detailOutBill1 = new DetailOutbill(100);
//	detailOutBill1.setListProduct(listProduct1);
//		detailOutBill1.setId(new OutBill_ProductKey());
//	outBill1.getDetailOutbills().add(detailOutBill1);
//
//		DetailOutbill detailOutBill2 = new DetailOutbill(100);
//	detailOutBill2.setOutBill(outBill2);
//		detailOutBill2.setListProduct(listProduct1);
//		detailOutBill2.setId(new OutBill_ProductKey());
//	outBill2.getDetailOutbills().add(detailOutBill2);
//
//		DetailOutbill detailOutBill3 = new DetailOutbill(100);
//		detailOutBill3.setOutBill(outBill2);
//	detailOutBill3.setListProduct(listProduct2);
//		detailOutBill3.setId(new OutBill_ProductKey());
//		outBill2.getDetailOutbills().add(detailOutBill3);

//		DetailOutbill detailOutBill2 = new DetailOutbill();
//		detailOutBill2.setOutBill(outBill2);
//		detailOutBill2.setProduct(product1);
//		detailOutBill2.setId(new OutBill_ProductKey());
//		detailOutBill2.setAmount(product1.getAmount());
//		product1.getDetailOutbills().add(detailOutBill2);
//
//		DetailOutbill detailOutBill3 = new DetailOutbill();
//		detailOutBill3.setOutBill(outBill2);
//		detailOutBill3.setProduct(product2);
//		detailOutBill3.setId(new OutBill_ProductKey());
//		detailOutBill3.setAmount(product2.getAmount());
//		product1.getDetailOutbills().add(detailOutBill3);
//
//		outBillRepository.save(outBill1);
//		outBillRepository.save(outBill2);
//
//		listProductRepository.save(listProduct1);
//		listProductRepository.save(listProduct2);
//
//		detaiOutBillRepository.save(detailOutBill1);
//		detaiOutBillRepository.save(detailOutBill2);
//		detaiOutBillRepository.save(detailOutBill3);
	}
}
