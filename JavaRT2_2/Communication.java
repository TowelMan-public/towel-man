package JavaRT2_2;

enum PersonType{
	ME,//自分
	BOSS,//上司
	MOM,//ママ
	Daddy,//パパ
	SUBORDINATE,//部下
	COLLEAGUE,//同僚
	CLUB_MOM,//（行きつけ？の）クラブのママ
	ALIEN;//エイリアン
}

public interface Communication {
	/*publicなメンバ関数*/
	public String greet(PersonType type) throws ClubCommuException, AlienException, WorkCommuException, FamilyCommuException;
}