package com.example.demo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SController {
	@Autowired
	DbMapper dbMapper;
	
	private boolean IsInt(String str) {//指定されたString文字列が整数であるかを判定する
		for(int i=0;i<str.length();i++) {
			if(!Character.isDigit(str.charAt(i))) 
				return false;
		}
		return true;
	}
	
	//初期状態の表示
	@GetMapping(value ="/home")
	public String displaySearch(Model model) {
	    return "/home";
	}
	//入荷登録処理
	@PostMapping("/stock")
	public String stock(@ModelAttribute @Valid StockForm form, BindingResult bindingResult)
	{ 
		if (bindingResult.hasErrors()) {
		      return "/home";
		}
		//入荷処理
		dbMapper.stock(form);
		dbMapper.afterStock(form);
		return "redirect:/home";
	}
	//出荷登録処理（失敗することがある）
	@PostMapping("/shipment")
	public String shipment(@ModelAttribute @Valid ShipmentForm form, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
		      return "/home";
		}
		if(dbMapper.getCount(form).getCount()<0){//在庫不足で出荷できない
			return "redirect:/home";
		}
		//出荷処理
		dbMapper.shipment(form);
		dbMapper.afterShipment(form);
		return "redirect:/home";
	}
	//検索処理
	@GetMapping("/result")
	public String result(@ModelAttribute("ResultForm") ResultForm form , Model model)
	{
		// ResultFormAに、取得したResultFormから適宜変更等をして適切にセットする
		ResultFormA formA = new ResultFormA();
		formA.setProductName(form.getProductName());
		formA.setTypeName(form.getTypeName());
		if(!(form.getProductID()==null||form.getProductID().equals(""))&&IsInt(form.getProductID())) {//整数に変換する必要がある場合
			formA.setProductID(Integer.parseInt(form.getProductID()));//値（整数）を取得
		}
		if(!(form.getTypeID()==null||form.getTypeID().equals(""))&&IsInt(form.getTypeID())) {//整数に変換する必要がある場合
			formA.setTypeID(Integer.parseInt(form.getTypeID()));//値（整数）を取得
		}
		if(!(form.getCount()==null||form.getCount().equals(""))&&IsInt(form.getCount())) {//整数に変換する必要がある場合
			formA.setCount(Integer.parseInt(form.getCount()));//値（整数）を取得
		}
		if(!(form.getProductName()==null||form.getProductName().equals(""))) {//空文字である
			formA.setProductName("");//空文字を明示的にセット
		}
		if(!(form.getTypeName()==null||form.getProductName().equals(""))){//空文字である
			formA.setTypeName("");//空文字を明示的にセット
		}
		//検索処理(Listで結果を受け取る)
		List<ResultFormA> outPut = dbMapper.getResult(formA);
		//htmlに検索結果をセット
		model.addAttribute("ResultFormA",outPut);
		return "/result";
	}
}
