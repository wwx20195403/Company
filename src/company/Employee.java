package company;

import java.io.Serializable;

public class Employee implements Serializable{
	private String name;
	private int ID;
	public Employee() {
		
	}
	Employee(String name,int ID){
		this.ID=ID;
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public int getID() {
		return ID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String toString() {
		return name+"  ID:"+ID;
	}
	
}
