package JavaRT2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int CHANGE_COMMU = 0;
	private static int GREET = 1;
	private static int END = -1;
	
	private static PersonType getPersonType(String type) {
		if(type.equals("自分"))
			return PersonType.ME;
		if(type.equals("上司"))
			return PersonType.BOSS;
		if(type.equals("ママ"))
			return PersonType.MOM;
		if(type.equals("パパ"))
			return PersonType.Daddy;
		if(type.equals("部下"))
			return PersonType.SUBORDINATE;
		if(type.equals("同僚"))
			return PersonType.COLLEAGUE;
		if(type.equals("クラブのママ"))
			return PersonType.CLUB_MOM;
		return PersonType.ALIEN;
	}
	
	private static int getUsersAns(String ans) {
		if(ans.equals("change"))
			return CHANGE_COMMU;
		if(ans.equals("greet"))
			return GREET;
		return END;
	}
	
	public static void main(String[] age) {
		Person person;
		InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String name;
		try {
			System.out.println("あなたのお名前を入力してください\n");
			name=br.readLine();
			System.out.println("あなたの居場所を「仕事」、「家族」、「クラブ」のなかから一つ選んで、正確に入力してください\n");
			person = new Person(name, br.readLine());
			while(true) {//メイン(ループする)
				System.out.println("居場所を変えるなら「change」を、挨拶をするなら「greet」を、それ以外なら適当に入力してください\n");
				switch(getUsersAns(br.readLine())) {
				case -1://END
					System.out.println("これで終了します");
					return;
				case 0://CHANGE_COMMU
					System.out.println("「仕事」、「家族」、「クラブ」のなかから一つ選んで、正確に入力してください\n");
					person.setCommunicationType(br.readLine());
					break;
				case 1://GREET
					System.out.println("誰があなたに話しかけるのかを入力してください\n");
					person.greet( getPersonType( br.readLine() ) );
					break;
				}
			}
		}
		catch(IllegalStateException | IOException e) {
			System.out.println("予期しないエラーが発生いたしましたのでここで終了にしたいと思います");
			return;
		} catch (AlienException e) {
			System.out.println("異常なエラー！www\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClubCommuException e) {
			System.out.println("クラブで想定していない出来事！\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (WorkCommuException e) {
			System.out.println("仕事場で想定していない出来事！\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (FamilyCommuException e) {
			System.out.println("家族で想定していない出来事！\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
