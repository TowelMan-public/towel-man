package JavaRT2_2;

public class WorkCommuException extends Throwable{
	//warningを回避するための宣言
	private static final long serialVersionUID = 1L; 

	// コンストラクタ
	public WorkCommuException(String msg){
		super(msg);
	}
}
