package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
//import org.apache.ibatis.annotations.Mapper; インポート出来ない


//?serverTimezone=JST

@Repository
@Mapper
public interface DbMapper {
	
	//入荷
	int stock(StockForm form);
	int afterStock(StockForm form);
	
	//出荷（数の取得・負の数なら失敗とする）
	ShipmentForm getCount(ShipmentForm form);
	
	//出荷
	int shipment(ShipmentForm form);
	int afterShipment(ShipmentForm form);
	
	//検索
	List<ResultFormA> getResult(ResultFormA formA);
}
