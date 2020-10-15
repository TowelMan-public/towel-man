package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Farst {
	private static String m_formula;
	private static int m_idx;//計算をするときに、次に読み込む所についてのインデックス
	
	private static double GetValue() {
		double temp = 0;
		String strnum = "";
		if (m_formula.charAt(m_idx) == '(') {
			++m_idx;
			return GetAns();
		}

		for (int i = 1; m_idx < m_formula.length();m_idx++) {
			if (!Character.isDigit(m_formula.charAt(m_idx)) && m_formula.charAt(m_idx) != '.')break;
			strnum += m_formula.charAt(m_idx);
		}
		temp = Double.parseDouble(strnum);
		return temp;
	}
	private static double GetArgumentValue() {
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

	private static double GetAns() {
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
	
	private static boolean Check() {
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
			}else//想定されれない文字（その時点でアウト！） 
				return false;
		}
		//最終判定
		if(befor==ten||befor==kakkos||befor==enzansi)return false;
		if(KakkoC!=0)return false;//最終的にカコのカウントが0出ない場合、その時点で問題あり
		//全てを通過した場合は問題なし(バグを省く)
		return true;
	}
	
	public static void main(String[] args) {
		double AnsValue;
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        boolean IsLoop=true;
        String temp;
		try {
			while(IsLoop) {
				System.out.println("計算式を入力してください♪\n使用可能なものは、すべて半角で、数字、かっこ、演算子（+,-,*,/）です\n");
				m_formula = br.readLine();
		        if(Check()) {//それ以降を実行
		            m_idx=0;
		            AnsValue = GetAns();
		            System.out.println("結果は"+AnsValue+"です！\n");
		            while(true) {
			            System.out.println("このまま続けるならYを、続けないのならNを入力してください！\n");
		            	temp = br.readLine();
		            	if(temp.equals("Y")) break;
		            	else if(temp.equals("N")) {
		            		IsLoop=false;
		            		break;
		            	}
		            	System.out.println(temp);
		            }
			        //br.close();
		        }else
		        	throw(new IllegalStateException());//例外
			}
		}
		catch(IOException e) {
			System.out.println("よくわからない問題が発生しました！\n");
			System.exit(0);
		}
		catch(ArithmeticException e) {//割る数が0になってしまった
			System.out.println("割り算で、割られる数が0になってしまいました！\n異常です！！\n");
		}
		catch(IllegalStateException e) {//文法が違う
			System.out.println("計算式の文法が不正です！確かめてみてください\n");
		}		
		finally{//ここに来るまでに問題はすべて解決しているという前提でいる（ただ単に使いたいだけ）
			System.out.println("これで終了します\n");
		}
		return;
	}

}