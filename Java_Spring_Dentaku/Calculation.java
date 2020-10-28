package com.example.demo;

public class Calculation {
	public String m_formula;
	private int m_idx;//計算をするときに、次に読み込む所についてのインデックス
	
	private double GetValue() {
		double temp = 0;
		String strnum = "";
		if (m_formula.charAt(m_idx) == '(') {
			++m_idx;
			return GetAns();
		}
		if(m_formula.charAt(m_idx)=='s'&&m_formula.charAt(m_idx+1)=='i'&&m_formula.charAt(m_idx+2)=='n'&&m_formula.charAt(m_idx+3)=='(') {//sin関数
			m_idx+=4;
			return Math.sin(GetAns());
		}else if(m_formula.charAt(m_idx)=='c'&&m_formula.charAt(m_idx+1)=='o'&&m_formula.charAt(m_idx+2)=='s'&&m_formula.charAt(m_idx+3)=='(') {//cos関数
			m_idx+=4;
			return Math.cos(GetAns());
		}else if(m_formula.charAt(m_idx)=='t'&&m_formula.charAt(m_idx+1)=='a'&&m_formula.charAt(m_idx+2)=='n'&&m_formula.charAt(m_idx+3)=='(') {//tan関数
			m_idx+=4;
			return Math.tan(GetAns());
		}

		for (int i = 1; m_idx < m_formula.length();m_idx++) {//数字
			if (!Character.isDigit(m_formula.charAt(m_idx)) && m_formula.charAt(m_idx) != '.')break;
			strnum += m_formula.charAt(m_idx);
		}
		temp = Double.parseDouble(strnum);
		if(m_formula.charAt(m_idx)=='^'&&m_formula.charAt(m_idx+1)=='(') {//累乗
			m_idx+=2;
			return Math.pow(temp,GetAns());
		}
		return temp;
	}
	private double GetArgumentValue() {
		//割り算の、割る数が0になったりしたらjavaが例外を出してくれるからそこは何も意識しない♪
		double seki = 1, syou = 1;
		if (m_formula.charAt(m_idx) == '-')seki = -1;
		if (m_formula.charAt(m_idx) == '+' || m_formula.charAt(m_idx) == '-')++m_idx;
		for (; m_idx < m_formula.length(); /*m_idx++*/) {
			if (m_formula.charAt(m_idx) == '+' || m_formula.charAt(m_idx) == '-' || m_formula.charAt(m_idx) == ')') break;
			if (m_formula.charAt(m_idx) == '*')++m_idx;
			if (m_formula.charAt(m_idx) != '/') seki *= GetValue();
			else {//掛け算
				++m_idx;
				syou *= GetValue();
			}
		}
		return seki / syou;//最終的な項の値
	}

	public double GetAns() {
		double ans = 0;
		for (; m_idx < m_formula.length(); /*m_idx++*/) {
			ans += GetArgumentValue();
			if (m_idx < m_formula.length()&&m_formula.charAt(m_idx) == ')') {//かっこの閉じるやつが来たら一つの式の計算は終了
				++m_idx;
				break;
			}
		}
		return ans;
	}
	
	public boolean Check() {
		//この関数には、"数字.数字.数字"のような場合をとり省くことができないというバグを持っている!!!!
		
		int farst = -1;
		int num = 0;
		int ten = 4;
		int enzansi = 1;
		int kakkos = 2;
		int kakkof = 3;
		int befor=farst;
		int KakkoC=0;
		
		for(int i=0;i<m_formula.length();i++){
			if(Character.isDigit(m_formula.charAt(i))) {//数字
				if(befor==kakkof) return false;
				befor=num;
			}else if(m_formula.charAt(i)=='.') {//小数点
				if(befor!=num) return false;
				befor=ten;
			}else if(m_formula.charAt(i)=='(') {//カッコ始
				if(befor==kakkof||befor==ten||befor==num) return false;
				befor=kakkos;
				++KakkoC;
			}else if(m_formula.charAt(i)==')') {//カッコ終
				if(befor!=num) return false;
				befor=kakkof;
				if(--KakkoC<0) return false;//カッコのカウントが負数になったらその時点で問題あり
			}else if(m_formula.charAt(i)=='+'||m_formula.charAt(i)=='-'||
						m_formula.charAt(i)=='*'||m_formula.charAt(i)=='/')	{//演算子
				if(befor==ten) return false;
				befor=enzansi;
			}else if(m_formula.charAt(i)=='s'&&m_formula.charAt(i+1)=='i'&&m_formula.charAt(i+2)=='n'&&m_formula.charAt(i+3)=='(') {//sin関数
				if(befor==kakkof||befor==ten||befor==num) return false;
				befor=kakkos;
				++KakkoC;
				i+=3;
			}else if(m_formula.charAt(i)=='c'&&m_formula.charAt(i+1)=='o'&&m_formula.charAt(i+2)=='s'&&m_formula.charAt(i+3)=='(') {//cos関数
				if(befor==kakkof||befor==ten||befor==num) return false;
				befor=kakkos;
				++KakkoC;
				i+=3;
			}else if(m_formula.charAt(i)=='t'&&m_formula.charAt(i+1)=='a'&&m_formula.charAt(i+2)=='n'&&m_formula.charAt(i+3)=='(') {//tan関数
				if(befor==kakkof||befor==ten||befor==num) return false;
				befor=kakkos;
				++KakkoC;
				i+=3;
			}else if(m_formula.charAt(i)=='^'&&m_formula.charAt(i+1)=='(') {
				if(befor==kakkof||befor==ten) return false;
				befor=kakkos;
				++KakkoC;
				i+=1;
			}
			else//想定されれない文字（その時点でアウト！） 
				return false;
		}
		//最終判定
		if(befor==ten||befor==kakkos||befor==enzansi)return false;
		if(KakkoC!=0)return false;//最終的にカコのカウントが0出ない場合、その時点で問題あり
		//全てを通過した場合は問題なし(バグを省く)
		return true;
	}
}
