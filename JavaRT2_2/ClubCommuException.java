package JavaRT2_2;

public class ClubCommuException extends Throwable{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 

	// コンストラクタ
	public ClubCommuException(String msg){
		super(msg);
	}
}
