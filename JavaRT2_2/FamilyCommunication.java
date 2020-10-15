package JavaRT2_2;

public class FamilyCommunication implements Communication{
	public String Name="";
	
	public FamilyCommunication(String name) {
		Name=name;
	}
	
	public String greet(PersonType type) throws FamilyCommuException,AlienException{
		String str="";
		switch(type) {
		case ME://独り言
			str = Name + "「♪～初めの二分　終わりの１秒　それが素敵ならばいいだろう～♪」" + "\n" +
					"(虚しい)" + "\n";
			break;
		case MOM://ママ
			str = "ママ" + "「さっさと寝なさ～い！」" + "\n" + 
					Name + "「は～い！ってまだ帰ってきたばっかだよ～！（しかも時間午後６時）」" + "\n";
			break;
		case Daddy://パパ
			str = "ママ" + "「さっさと寝なさ～い！」" + "\n" + 
					Name + "「は～い！ってまだ帰ってきたばっかだよ～！（しかも時間午後６時）」" + "\n";
			break;
		case BOSS://上司
			throw new FamilyCommuException("ここは僕の家なので来ないでほしいです（ぐったり）");
		case SUBORDINATE://部下
			throw new FamilyCommuException("誰ですか（沈黙の圧）...");
		case COLLEAGUE://同僚
			throw new FamilyCommuException("あ～！僕の同僚よ！　僕の家には来ないでくれ！");
		case CLUB_MOM://クラブのママ
			throw new FamilyCommuException("ママ～それはあかんよ～！　明日ママのクラブに行くから勘弁して～");
		case ALIEN://エイリアン
			throw new AlienException();
		}
		return str;
	}
}
