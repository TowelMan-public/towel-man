package JavaRT2_2;

public class ClubCommunication implements Communication{
	public String Name="";
	
	public ClubCommunication(String name) {
		Name=name;
	}
	
	public String greet(PersonType type) throws ClubCommuException,AlienException{
		String str="";
		switch(type) {
		case ME://�Ƃ茾
			str = Name + "�u(�S�N�S�N)...�v" + "\n" +
					"(�ō����`...)" + "\n";
			break;
		case MOM://�}�}
		case Daddy://�p�p
			throw new ClubCommuException("�i�����I�H�@�C�܂����I�j");
		case BOSS://��i
			str = "��i" + "�u�܂����N�Ƃ����ŉ�Ƃ͂ˁI�@������ˁI�v" + "\n" + 
					Name + "�u�����ł��ˁI�@���̂��Ƃ͂������ɂ��肢�������܂��I�v" + "\n";
			break;
		case SUBORDINATE://����
			str = "����" + "�u" + Name + "����������ɗ����ł��ˁI�v" + "\n" + 
					Name + "�u����I�@�������ɂˁ�i�Ί�̈��j�v" + "\n";
			break;
		case COLLEAGUE://����
			str = "����" + "�u����ς�N�����ς�炸���ˁ`�I�v" + "\n" + 
					Name + "�u����`�@�����ɗ���ƂȂ񂩘a�ނ�ˁ`�I�v" + "\n";
			break;
		case CLUB_MOM://�N���u�̃}�}
			str = "�N���u�̃}�}" + "�u���Ȃ��`�@��ȉ̂��Ă��傤������I�v" + "\n" + 
					Name + "�u����`�u�u�Ԃ̈��i�ǁF�ЂƂƂ��̂����j/������r�v���̂��ˁ`��I�v" + "\n";
			break;
		case ALIEN://�G�C���A��
			throw new AlienException();
		}
		return str;
	}
}
