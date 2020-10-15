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
		/*1つ目*/
		String greeting = "Hello World\n";
		System.out.print(greeting);
		/*二つ目*/
		for(int i=0,sum=0;i<=10;sum+=++i)
			if(i==10) System.out.print(sum + "\n");
		/*3つ目*/
		LocalDateTime date = LocalDateTime.now();
		date=date.plusYears(3);//足し算をしたい
		DayOfWeek week = date.getDayOfWeek();
		//出力(防御的にしたほうが身のためと思い直感で防御的にしました！)
		try {
			System.out.println(week.toString() + "\n");
			/*4つ目*/
			String str;
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			System.out.println("０か１を入力してください\n");	
			str=br.readLine();
			if(str=="1")
				System.out.println("引数が1なら真");
			else if(str=="2")
				System.out.println("引数が0なら偽");
			else
				System.out.println("0か1を入力してほしかった...!(切実)");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		/*5つ目*/
		//List
		List<String> list = new ArrayList<String>(Arrays.asList("ヒップ", "ホップ", "ステップ","ジャンプ"));
		for(int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
		//Map
		Map<String, String> map = new HashMap<String, String>();
		String key,contents;
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			do {
				System.out.println("キーを入力してください(重複させないでね♪)\n");
				key=br.readLine();
				System.out.println("内容を入力してください\n");
				contents=br.readLine();
				map.put(key, contents);
				System.out.println("まだ何かをセットするならYを入力してください\n");
			}while(br.readLine().equals("Y"));
			
			System.out.println("セットした内容を見るにはYを押してください\n");
			
			while(br.readLine().equals("Y")) {
				System.out.println("キーを入力してください(さっき指定したやつだけよ♪)\n");
				key=br.readLine();
				System.out.println(map.get(key) + "\n");
				System.out.println("まだセットした内容を見るにはYを押してください\n");
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		/*最後のご挨拶*/
		System.out.println("バイバ～イ♪\n");
	}
}
