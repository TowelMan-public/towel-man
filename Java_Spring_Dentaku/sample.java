package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//ここが実行されるところ
@RestController
@RequestMapping("Dentaku")
public class sample {
	private String m_html1;
	private String m_html2;
	
	public sample() {//初期化（test1.txtとtst2.txtを連結させるために、それぞれを別々にStringに取り込む）
		try {
			String str = new String();
			File file = new File("C:\\Users\\Odagi\\eclipse-workspace\\SpringDemo\\src\\main\\java\\com\\example\\demo\\test1.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader br = new BufferedReader(filereader);
			str=br.readLine();
			while(str != null){
			    m_html1 += str;
			    str = br.readLine();
			}
			br.close();
			filereader.close();
			file = new File("C:\\Users\\Odagi\\eclipse-workspace\\SpringDemo\\src\\main\\java\\com\\example\\demo\\test2.txt");
			filereader = new FileReader(file);
			br = new BufferedReader(filereader);
			str=br.readLine();
			while(str != null){
			    m_html2 += str;
			    str = br.readLine();
			}
			br.close();
			filereader.close();
		}catch(FileNotFoundException e) {
			m_html1="ERROR";
		}catch( IOException e) {
			m_html2="ERROR";
		}
	}
	
	@RequestMapping("See")//最初のサイト生成（読み込んだ二つのhtmlデータを単純に合わせてそれを返すだけ）
	public String See() {
		return m_html1 + m_html2;
	}
	
	@RequestMapping(value = "do", method = RequestMethod.GET)
	public String Do(@ModelAttribute("CustomerForm") CustomerForm form , Model model) {//計算をして、結果を反映させる(二つのhtmlデータの間に計算結果を挟んで合わせてそれを返す)
		try {
			double ans=0;
			Calculation cal = new Calculation();
			cal.m_formula=form.getFormula();
			if(!cal.Check())throw(new IllegalStateException());
			ans = cal.GetAns();
			return m_html1 + "  " + String.valueOf(ans) + m_html2;
		}
		catch(ArithmeticException e) {//割る数が0になってしまった
			return m_html1 + "割り算で、割られる数が0になってしまいました！\n異常です！！" + m_html2;
		}
		catch(IllegalStateException e) {//文法が違う
			return  m_html1 + "計算式の文法が不正です！確かめてみてください" + m_html2;
		}	
	}
}
