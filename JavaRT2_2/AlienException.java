package JavaRT2_2;

public class AlienException extends Exception{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 

	// コンストラクタ
	public AlienException(){
		super("WORNING!!::地球外生命体登場！！！！！！！！！");
	}
}