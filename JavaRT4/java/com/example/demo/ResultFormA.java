package com.example.demo;

//データベースで検索処理をするときと、result.htmlに出力する時用（AはAnser）
public class ResultFormA {
	private int productID;
	private String productName;
	private int typeID;
	private String typeName;
	private int count;
	private String empty;//空文字であるかを識別するための定数的な奴
	
	public ResultFormA() {//初期化
		productID = -1;
		typeID = -1;
		count = -1;
		empty="";
	}
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String getEmpty() {
		return empty;
	}
}
