package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Farst {
	private static String m_formula;
	private static int m_idx;//�v�Z������Ƃ��ɁA���ɓǂݍ��ޏ��ɂ��ẴC���f�b�N�X
	
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
		//����Z�́A���鐔��0�ɂȂ����肵����java����O���o���Ă���邩�炻���͉����ӎ����Ȃ���
		double seki = 1, syou = 1;
		if (m_formula.charAt(m_idx) == '-')seki = -1;
		if (m_formula.charAt(m_idx) == '+' || m_formula.charAt(m_idx) == '-')++m_idx;
		for (; m_idx < m_formula.length(); /*m_idx++*/) {
			if (m_formula.charAt(m_idx) == '+' || m_formula.charAt(m_idx) == '-' || m_formula.charAt(m_idx) == ')') break;
			if (m_formula.charAt(m_idx) == '*')++m_idx;
			if (m_formula.charAt(m_idx) != '/') seki *= GetValue();
			else {//�|���Z
				++m_idx;
				syou *= GetValue();
			}
		}
		return seki / syou;//�ŏI�I�ȍ��̒l
	}

	private static double GetAns() {
		double ans = 0;
		for (; m_idx < m_formula.length(); /*m_idx++*/) {
			ans += GetArgumentValue();
			if (m_idx < m_formula.length()&&m_formula.charAt(m_idx) == ')') {//�������̕������������̎��̌v�Z�͏I��
				++m_idx;
				break;
			}
		}
		return ans;
	}
	
	private static boolean Check() {
		//���̊֐��ɂ́A"����.����.����"�̂悤�ȏꍇ���Ƃ�Ȃ����Ƃ��ł��Ȃ��Ƃ����o�O�������Ă���!!!!
		
		int farst = -1;
		int num = 0;
		int ten = 4;
		int enzansi = 1;
		int kakkos = 2;
		int kakkof = 3;
		int befor=farst;
		int KakkoC=0;
		
		for(int i=0;i<m_formula.length();i++){
			if(Character.isDigit(m_formula.charAt(i))) {//����
				if(befor==kakkof) return false;
				befor=num;
			}else if(m_formula.charAt(i)=='.') {//�����_
				if(befor!=num) return false;
				befor=ten;
			}else if(m_formula.charAt(i)=='(') {//�J�b�R�n
				if(befor==kakkof||befor==ten||befor==num) return false;
				befor=kakkos;
				++KakkoC;
			}else if(m_formula.charAt(i)==')') {//�J�b�R�I
				if(befor!=num) return false;
				befor=kakkof;
				if(--KakkoC<0) return false;//�J�b�R�̃J�E���g�������ɂȂ����炻�̎��_�Ŗ�肠��
			}else if(m_formula.charAt(i)=='+'||m_formula.charAt(i)=='-'||
						m_formula.charAt(i)=='*'||m_formula.charAt(i)=='/')	{//���Z�q
				if(befor==ten) return false;
				befor=enzansi;
			}else//�z�肳���Ȃ������i���̎��_�ŃA�E�g�I�j 
				return false;
		}
		//�ŏI����
		if(befor==ten||befor==kakkos||befor==enzansi)return false;
		if(KakkoC!=0)return false;//�ŏI�I�ɃJ�R�̃J�E���g��0�o�Ȃ��ꍇ�A���̎��_�Ŗ�肠��
		//�S�Ă�ʉ߂����ꍇ�͖��Ȃ�(�o�O���Ȃ�)
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
				System.out.println("�v�Z������͂��Ă���������\n�g�p�\�Ȃ��̂́A���ׂĔ��p�ŁA�����A�������A���Z�q�i+,-,*,/�j�ł�\n");
				m_formula = br.readLine();
		        if(Check()) {//����ȍ~�����s
		            m_idx=0;
		            AnsValue = GetAns();
		            System.out.println("���ʂ�"+AnsValue+"�ł��I\n");
		            while(true) {
			            System.out.println("���̂܂ܑ�����Ȃ�Y���A�����Ȃ��̂Ȃ�N����͂��Ă��������I\n");
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
		        	throw(new IllegalStateException());//��O
			}
		}
		catch(IOException e) {
			System.out.println("�悭�킩��Ȃ���肪�������܂����I\n");
			System.exit(0);
		}
		catch(ArithmeticException e) {//���鐔��0�ɂȂ��Ă��܂���
			System.out.println("����Z�ŁA�����鐔��0�ɂȂ��Ă��܂��܂����I\n�ُ�ł��I�I\n");
		}
		catch(IllegalStateException e) {//���@���Ⴄ
			System.out.println("�v�Z���̕��@���s���ł��I�m���߂Ă݂Ă�������\n");
		}		
		finally{//�����ɗ���܂łɖ��͂��ׂĉ������Ă���Ƃ����O��ł���i�����P�Ɏg�����������j
			System.out.println("����ŏI�����܂�\n");
		}
		return;
	}

}