package JavaRT2_2;

public class FamilyCommuException extends Throwable{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 

	// コンストラクタ
	public FamilyCommuException(String msg){
		super(msg);
	}
}
