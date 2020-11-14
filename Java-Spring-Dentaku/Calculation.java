package com.example.demo;

public class Calculation {
	public String formula;
	private int idx;//計算をするときに、次に読み込む所についてのインデックス
	
	private double getValue() {
		double temp = 0;
		String strnum = "";
		if (formula.charAt(idx) == '(') {
			++idx;
			return getAns();
		}
		if(formula.charAt(idx)=='s'&&formula.charAt(idx+1)=='i'&&formula.charAt(idx+2)=='n'&&formula.charAt(idx+3)=='(') {//sin関数
			idx+=4;
			return Math.sin(getAns());
		}else if(formula.charAt(idx)=='c'&&formula.charAt(idx+1)=='o'&&formula.charAt(idx+2)=='s'&&formula.charAt(idx+3)=='(') {//cos関数
			idx+=4;
			return Math.cos(getAns());
		}else if(formula.charAt(idx)=='t'&&formula.charAt(idx+1)=='a'&&formula.charAt(idx+2)=='n'&&formula.charAt(idx+3)=='(') {//tan関数
			idx+=4;
			return Math.tan(getAns());
		}

		for (int i = 1; idx < formula.length();idx++) {//数字
			if (!Character.isDigit(formula.charAt(idx)) && formula.charAt(idx) != '.')break;
			strnum += formula.charAt(idx);
		}
		temp = Double.parseDouble(strnum);
		if(formula.charAt(idx)=='^'&&formula.charAt(idx+1)=='(') {//累乗
			idx+=2;
			return Math.pow(temp,getAns());
		}
		return temp;
	}
	private double getArgumentValue() {
		//割り算の、割る数が0になったりしたらjavaが例外を出してくれるからそこは何も意識しない♪
		double seki = 1, syou = 1;
		if (formula.charAt(idx) == '-')seki = -1;
		if (formula.charAt(idx) == '+' || formula.charAt(idx) == '-')++idx;
		for (; idx < formula.length(); /*m_idx++*/) {
			if (formula.charAt(idx) == '+' || formula.charAt(idx) == '-' || formula.charAt(idx) == ')') break;
			if (formula.charAt(idx) == '*')++idx;
			if (formula.charAt(idx) != '/') seki *= getValue();
			else {//掛け算
				++idx;
				syou *= getValue();
			}
		}
		return seki / syou;//最終的な項の値
	}

	public double getAns() {
		double ans = 0;
		for (; idx < formula.length(); /*m_idx++*/) {
			ans += getArgumentValue();
			if (idx < formula.length()&&formula.charAt(idx) == ')') {//かっこの閉じるやつが来たら一つの式の計算は終了
				++idx;
				break;
			}
		}
		return ans;
	}
	
	public boolean check() {
		//この関数には、"数字.数字.数字"のような場合をとり省くことができないというバグを持っている!!!!
		
		int farst = -1;
		int num = 0;
		int point = 4;
		int operator = 1;
		int bracketsS = 2;
		int bracketsF = 3;
		int befor=farst;
		int bracketsC=0;
		
		for(int i=0;i<formula.length();i++){
			if(Character.isDigit(formula.charAt(i))) {//数字
				if(befor==bracketsF) return false;
				befor=num;
			}else if(formula.charAt(i)=='.') {//小数点
				if(befor!=num) return false;
				befor=point;
			}else if(formula.charAt(i)=='(') {//カッコ始
				if(befor==bracketsF||befor==point||befor==num) return false;
				befor=bracketsS;
				++bracketsC;
			}else if(formula.charAt(i)==')') {//カッコ終
				if(befor!=num) return false;
				befor=bracketsF;
				if(--bracketsC<0) return false;//カッコのカウントが負数になったらその時点で問題あり
			}else if(formula.charAt(i)=='+'||formula.charAt(i)=='-'||
						formula.charAt(i)=='*'||formula.charAt(i)=='/')	{//演算子
				if(befor==point) return false;
				befor=operator;
			}else if(formula.charAt(i)=='s'&&formula.charAt(i+1)=='i'&&formula.charAt(i+2)=='n'&&formula.charAt(i+3)=='(') {//sin関数
				if(befor==bracketsF||befor==point||befor==num) return false;
				befor=bracketsS;
				++bracketsC;
				i+=3;
			}else if(formula.charAt(i)=='c'&&formula.charAt(i+1)=='o'&&formula.charAt(i+2)=='s'&&formula.charAt(i+3)=='(') {//cos関数
				if(befor==bracketsF||befor==point||befor==num) return false;
				befor=bracketsS;
				++bracketsC;
				i+=3;
			}else if(formula.charAt(i)=='t'&&formula.charAt(i+1)=='a'&&formula.charAt(i+2)=='n'&& formula.charAt(i+3)=='(') {//tan関数
				if(befor==bracketsF||befor==point||befor==num) return false;
				befor=bracketsS;
				++bracketsC;
				i+=3;
			}else if(formula.charAt(i)=='^'&&formula.charAt(i+1)=='(') {
				if(befor==bracketsF||befor==point) return false;
				befor=bracketsS;
				++bracketsC;
				i+=1;
			}
			else//想定されれない文字（その時点でアウト！） 
				return false;
		}
		//最終判定
		if(befor==point||befor==bracketsS||befor==operator)return false;
		if(bracketsC!=0)return false;//最終的にカコのカウントが0出ない場合、その時点で問題あり
		//全てを通過した場合は問題なし(バグを省く)
		return true;
	}
}
