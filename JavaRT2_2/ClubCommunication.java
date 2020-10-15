package JavaRT2_2;

public class ClubCommunication implements Communication{
	public String Name="";
	
	public ClubCommunication(String name) {
		Name=name;
	}
	
	public String greet(PersonType type) throws ClubCommuException,AlienException{
		String str="";
		switch(type) {
		case ME://独り言
			str = Name + "「(ゴクゴク)...」" + "\n" +
					"(最高だ～...)" + "\n";
			break;
		case MOM://ママ
		case Daddy://パパ
			throw new ClubCommuException("（うっ！？　気まずい！）");
		case BOSS://上司
			str = "上司" + "「まさか君とここで会うとはね！　奇遇だね！」" + "\n" + 
					Name + "「そうですね！　このことはご内密にお願いいたします！」" + "\n";
			break;
		case SUBORDINATE://部下
			str = "部下" + "「" + Name + "さんもここに来るんですね！」" + "\n" + 
					Name + "「うん！　ご内密にね♪（笑顔の圧）」" + "\n";
			break;
		case COLLEAGUE://同僚
			str = "同僚" + "「やっぱり君も相変わらずだね～！」" + "\n" + 
					Name + "「うん～　ここに来るとなんか和むよね～！」" + "\n";
			break;
		case CLUB_MOM://クラブのママ
			str = "クラブのママ" + "「あなた～　一曲歌ってちょうだい♪！」" + "\n" + 
					Name + "「じゃ～「瞬間の愛（読：ひとときのあい）/中村雅俊」を歌うね～♪！」" + "\n";
			break;
		case ALIEN://エイリアン
			throw new AlienException();
		}
		return str;
	}
}
