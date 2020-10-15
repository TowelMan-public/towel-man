package JavaRT2_2;

public class Person{
	private String Name;
	private Communication commu;
	
	public Person(String name,String type) throws IllegalStateException{
		Name=name;
		setCommunicationType(type);
	}
	public void setCommunicationType(String type) throws IllegalStateException{
		if(type.equals("�Ƒ�"))
			commu = new FamilyCommunication(Name);
		else if(type.equals("�d��"))
			commu = new WorkCommunication(Name);
		else if(type.equals("�N���u"))
			commu = new ClubCommunication(Name);
		else
			throw new IllegalStateException();
	}
	public void greet(PersonType type) throws ClubCommuException, AlienException, WorkCommuException, FamilyCommuException {
		System.out.println(commu.greet(type));
	}
}