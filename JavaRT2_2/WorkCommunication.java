package JavaRT2_2;

public class WorkCommunication implements Communication{
	public String Name="";
	
	public WorkCommunication(String name) {
		Name=name;
	}
	
	public String greet(PersonType type) throws WorkCommuException,AlienException{
		String str="";
		switch(type) {
		case ME://�Ƃ茾
			str = Name + "�u(�J�^�J�^�J�^)�@�h��...�v" + "\n" +
					"(�c�Ƃ͌���...)" + "\n";
			break;
		case MOM://�}�}
		case Daddy://�p�p
			throw new WorkCommuException("�e�ɂ͉�Ђɗ��Ă��炢�����Ȃ�...");
		case BOSS://��i
			str = "��i" + "�u���͂悤�I�v" + "\n" + 
					Name + "�u���͂悤�������܂��I�@�������ǂ��V�C�ł��ˁI�v" + "\n";
			break;
		case SUBORDINATE://����
			str = "����" + "�u���͂悤�������܂��I�@���@���͂������ł��傤���H�v" + "\n" + 
					Name + "�u��`���ʂ��ȁ@������ƔZ���߂ɃA�C�X�R�[�q�[�����Ă��āv" + "\n";
			break;
		case COLLEAGUE://����
			str = "����" + "�u���͂�`�@������c�Ƃ����ςȂ�����`�I�v" + "\n" + 
					Name + "�u���͂�`�@�����������`�v" + "\n";
			break;
		case CLUB_MOM://�N���u�̃}�}
			throw new WorkCommuException("�}�}�`�@���ꂵ�����Ǐ�i�����邩��p������������...");
		case ALIEN://�G�C���A��
			throw new AlienException();
		}
		return str;
	}
}
