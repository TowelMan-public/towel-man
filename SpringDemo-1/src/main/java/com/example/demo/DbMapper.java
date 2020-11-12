package com.example.demo;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
//import org.apache.ibatis.annotations.Mapper; インポート出来ない


//@Mapper 使えない
@Repository
@MapperScan("com.example.demo")
public interface DbMapper {
	
	//入荷
	int stock(StockForm form);
	
	//出荷（数の取得・負の数なら失敗とする）
	ShipmentForm getCount(ShipmentForm form);
	
	//出荷
	int shipment(ShipmentForm form);
	
	//検索
	List<ResultForm> getResult(ResultForm form);
}
