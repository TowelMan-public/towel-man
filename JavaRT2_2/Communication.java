package JavaRT2_2;

enum PersonType{
	ME,//����
	BOSS,//��i
	MOM,//�}�}
	Daddy,//�p�p
	SUBORDINATE,//����
	COLLEAGUE,//����
	CLUB_MOM,//�i�s�����H�́j�N���u�̃}�}
	ALIEN;//�G�C���A��
}

public interface Communication {
	/*public�ȃ����o�֐�*/
	public String greet(PersonType type) throws ClubCommuException, AlienException, WorkCommuException, FamilyCommuException;
}