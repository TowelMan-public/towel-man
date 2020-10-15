package JavaRT2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int CHANGE_COMMU = 0;
	private static int GREET = 1;
	private static int END = -1;
	
	private static PersonType getPersonType(String type) {
		if(type.equals("����"))
			return PersonType.ME;
		if(type.equals("��i"))
			return PersonType.BOSS;
		if(type.equals("�}�}"))
			return PersonType.MOM;
		if(type.equals("�p�p"))
			return PersonType.Daddy;
		if(type.equals("����"))
			return PersonType.SUBORDINATE;
		if(type.equals("����"))
			return PersonType.COLLEAGUE;
		if(type.equals("�N���u�̃}�}"))
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
			System.out.println("���Ȃ��̂����O����͂��Ă�������\n");
			name=br.readLine();
			System.out.println("���Ȃ��̋��ꏊ���u�d���v�A�u�Ƒ��v�A�u�N���u�v�̂Ȃ������I��ŁA���m�ɓ��͂��Ă�������\n");
			person = new Person(name, br.readLine());
			while(true) {//���C��(���[�v����)
				System.out.println("���ꏊ��ς���Ȃ�uchange�v���A���A������Ȃ�ugreet�v���A����ȊO�Ȃ�K���ɓ��͂��Ă�������\n");
				switch(getUsersAns(br.readLine())) {
				case -1://END
					System.out.println("����ŏI�����܂�");
					return;
				case 0://CHANGE_COMMU
					System.out.println("�u�d���v�A�u�Ƒ��v�A�u�N���u�v�̂Ȃ������I��ŁA���m�ɓ��͂��Ă�������\n");
					person.setCommunicationType(br.readLine());
					break;
				case 1://GREET
					System.out.println("�N�����Ȃ��ɘb��������̂�����͂��Ă�������\n");
					person.greet( getPersonType( br.readLine() ) );
					break;
				}
			}
		}
		catch(IllegalStateException | IOException e) {
			System.out.println("�\�����Ȃ��G���[�������������܂����̂ł����ŏI���ɂ������Ǝv���܂�");
			return;
		} catch (AlienException e) {
			System.out.println("�ُ�ȃG���[�Iwww\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClubCommuException e) {
			System.out.println("�N���u�őz�肵�Ă��Ȃ��o�����I\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (WorkCommuException e) {
			System.out.println("�d����őz�肵�Ă��Ȃ��o�����I\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (FamilyCommuException e) {
			System.out.println("�Ƒ��őz�肵�Ă��Ȃ��o�����I\n");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
