package com.example.demo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//java_crud_r
@Controller
public class SController {
	private final DbMapper dbMapper = null;
	
	//入荷登録処理
	@PostMapping("stock")
	public String stock(@ModelAttribute @Valid StockForm form, BindingResult bindingResult)
	{ 
		if (bindingResult.hasErrors()) {
		      return "home";
		}
		dbMapper.stock(form);
		form.setMessage("成功");
		return "redirect:/home";
	}
	//出荷登録処理（失敗することがある）
	@PostMapping("shipment")
	public String shipment(@ModelAttribute @Valid ShipmentForm form, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) {
		      return "home";
		}
		if(dbMapper.getCount(form).getCount()<0){
			form.setMessage("失敗");
		}
		else{
			form.setMessage("成功");
		}
		dbMapper.shipment(form);
		return "redirect:/home";
	}
	//検索処理
	@GetMapping("result")
	public String result(@ModelAttribute("ResultForm") ResultForm form , Model model)
	{
		List<ResultForm> outPut = dbMapper.getResult(form);
		model.addAttribute("",outPut);
		return "result";
	}
}
