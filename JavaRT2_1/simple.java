package simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class simple {

	public static void main(String[] age) {
		/*1��*/
		String greeting = "Hello World\n";
		System.out.print(greeting);
		/*���*/
		for(int i=0,sum=0;i<=10;sum+=++i)
			if(i==10) System.out.print(sum + "\n");
		/*3��*/
		LocalDateTime date = LocalDateTime.now();
		date=date.plusYears(3);//�����Z��������
		DayOfWeek week = date.getDayOfWeek();
		//�o��(�h��I�ɂ����ق����g�̂��߂Ǝv�������Ŗh��I�ɂ��܂����I)
		try {
			System.out.println(week.toString() + "\n");
			/*4��*/
			String str;
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			System.out.println("�O���P����͂��Ă�������\n");	
			str=br.readLine();
			if(str=="1")
				System.out.println("������1�Ȃ�^");
			else if(str=="2")
				System.out.println("������0�Ȃ�U");
			else
				System.out.println("0��1����͂��Ăق�������...!(�؎�)");
		} catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		/*5��*/
		//List
		List<String> list = new ArrayList<String>(Arrays.asList("�q�b�v", "�z�b�v", "�X�e�b�v","�W�����v"));
		for(int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
		//Map
		Map<String, String> map = new HashMap<String, String>();
		String key,contents;
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			do {
				System.out.println("�L�[����͂��Ă�������(�d�������Ȃ��łˁ�)\n");
				key=br.readLine();
				System.out.println("���e����͂��Ă�������\n");
				contents=br.readLine();
				map.put(key, contents);
				System.out.println("�܂��������Z�b�g����Ȃ�Y����͂��Ă�������\n");
			}while(br.readLine().equals("Y"));
			
			System.out.println("�Z�b�g�������e������ɂ�Y�������Ă�������\n");
			
			while(br.readLine().equals("Y")) {
				System.out.println("�L�[����͂��Ă�������(�������w�肵����������)\n");
				key=br.readLine();
				System.out.println(map.get(key) + "\n");
				System.out.println("�܂��Z�b�g�������e������ɂ�Y�������Ă�������\n");
			}
		} catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		/*�Ō�̂����A*/
		System.out.println("�o�C�o�`�C��\n");
	}
}
