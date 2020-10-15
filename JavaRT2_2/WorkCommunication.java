package JavaRT2_2;

public class WorkCommunication implements Communication{
	public String Name="";
	
	public WorkCommunication(String name) {
		Name=name;
	}
	
	public String greet(PersonType type) throws WorkCommuException,AlienException{
		String str="";
		switch(type) {
		case ME://独り言
			str = Name + "「(カタカタカタ)　辛い...」" + "\n" +
					"(残業は嫌だ...)" + "\n";
			break;
		case MOM://ママ
		case Daddy://パパ
			throw new WorkCommuException("親には会社に来てもらいたくない...");
		case BOSS://上司
			str = "上司" + "「おはよう！」" + "\n" + 
					Name + "「おはようございます！　今日も良い天気ですね！」" + "\n";
			break;
		case SUBORDINATE://部下
			str = "部下" + "「おはようございます！　ご機嫌はいかがでしょうか？」" + "\n" + 
					Name + "「ん～普通かな　ちょっと濃いめにアイスコーヒーを入れてきて」" + "\n";
			break;
		case COLLEAGUE://同僚
			str = "同僚" + "「おはよ～　昨日も残業しっぱなしだよ～！」" + "\n" + 
					Name + "「おはよ～　おいらもだよ～」" + "\n";
			break;
		case CLUB_MOM://クラブのママ
			throw new WorkCommuException("ママ～　うれしいけど上司もいるから恥ずかしいかな...");
		case ALIEN://エイリアン
			throw new AlienException();
		}
		return str;
	}
}
