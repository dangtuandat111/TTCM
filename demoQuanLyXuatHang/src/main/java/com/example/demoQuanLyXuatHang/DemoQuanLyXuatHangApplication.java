package com.example.demoQuanLyXuatHang;

import com.example.demoQuanLyXuatHang.entity.DetailOutbill;
import com.example.demoQuanLyXuatHang.entity.OutBill;
import com.example.demoQuanLyXuatHang.entity.OutBill_ProductKey;
import com.example.demoQuanLyXuatHang.entity.Product;
import com.example.demoQuanLyXuatHang.repository.DetailOutBillRepository;
import com.example.demoQuanLyXuatHang.repository.OutBillRepository;
import com.example.demoQuanLyXuatHang.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoQuanLyXuatHangApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoQuanLyXuatHangApplication.class, args);
	}

	@Autowired
	OutBillRepository outBillRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	DetailOutBillRepository detaiOutBillRepository;
	@Override
	public void run(String... args) throws Exception {
//		Product product1 = new Product(100000,1);
//		Product product2 = new Product(75000,2);
//
//		OutBill outBill1 = new OutBill(1,"ok",10);
//		OutBill outBill2 = new OutBill(2,"finished",3);
//
//		DetailOutbill detailOutBill1 = new DetailOutbill();
//		detailOutBill1.setOutBill(outBill1);
//		detailOutBill1.setProduct(product1);
//		detailOutBill1.setAmount(product1.getAmount());
//		detailOutBill1.setId(new OutBill_ProductKey());
//		product1.getDetailOutbills().add(detailOutBill1);
//
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
//		productRepository.save(product1);
//		productRepository.save(product2);
//
//		detaiOutBillRepository.save(detailOutBill1);
//		detaiOutBillRepository.save(detailOutBill2);
//		detaiOutBillRepository.save(detailOutBill3);
	}
}
