package JavaRT2_2;

public class FamilyCommunication implements Communication{
	public String Name="";
	
	public FamilyCommunication(String name) {
		Name=name;
	}
	
	public String greet(PersonType type) throws FamilyCommuException,AlienException{
		String str="";
		switch(type) {
		case ME://�Ƃ茾
			str = Name + "�u��`���߂̓񕪁@�I���̂P�b�@���ꂪ�f�G�Ȃ�΂������낤�`��v" + "\n" +
					"(������)" + "\n";
			break;
		case MOM://�}�}
			str = "�}�}" + "�u�������ƐQ�Ȃ��`���I�v" + "\n" + 
					Name + "�u�́`���I���Ă܂��A���Ă����΂�������`�I�i���������Ԍߌ�U���j�v" + "\n";
			break;
		case Daddy://�p�p
			str = "�}�}" + "�u�������ƐQ�Ȃ��`���I�v" + "\n" + 
					Name + "�u�́`���I���Ă܂��A���Ă����΂�������`�I�i���������Ԍߌ�U���j�v" + "\n";
			break;
		case BOSS://��i
			throw new FamilyCommuException("�����͖l�̉ƂȂ̂ŗ��Ȃ��łق����ł��i��������j");
		case SUBORDINATE://����
			throw new FamilyCommuException("�N�ł����i���ق̈��j...");
		case COLLEAGUE://����
			throw new FamilyCommuException("���`�I�l�̓�����I�@�l�̉Ƃɂ͗��Ȃ��ł���I");
		case CLUB_MOM://�N���u�̃}�}
			throw new FamilyCommuException("�}�}�`����͂������`�I�@�����}�}�̃N���u�ɍs�����犨�ق��ā`");
		case ALIEN://�G�C���A��
			throw new AlienException();
		}
		return str;
	}
}
