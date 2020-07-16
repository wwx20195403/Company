package company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Company {
	 private ArrayList<Employee> list=new ArrayList ();
	 private ArrayList<DakaInfo> message=new ArrayList<>();
	public Company() {
		
	}
	public void addEmployee(Employee e) {
		list.add(e);
		message.add(new DakaInfo(e.getID()));
		System.out.println(e.toString()+"添加成功");
	}
	public void removeEmployee(int ID) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getID()==ID) {
				list.remove(i);
				System.out.println("删除成功！");
			}
		}
	}
	public Employee searchEmployee(int ID) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getID()==ID) {
				return list.get(i);
			}
		}
		return null;
	}
	public String showEmployee(int ID) {
		String a=null;
		for(int i=0;i<list.size();i++) {	
			if(list.get(i).getID()==ID){
				ArrayList<String> aa=message.get(i).getStart();
				if(aa!=null) {
					a="签到时间\n";
				for(String temp : aa) {
					a+=temp+"\n";
				 }
				}
				aa=message.get(i).getEnd();
				if(aa!=null) {
					a+="签退时间\n";
				for(String temp : aa) {
					a+=temp+"\n";
				 }
			}
		}
		}
		if(a==null)a="无记录";
		 return a;
	}
	public String  registerStart(int ID) {
		for(DakaInfo a :message) {
			if(a.getID()==ID) {
				if(a.setStart()) {
					return "卡号："+ID+"  打卡成功";
				}else {
					return "今天已经打过卡了";
				}
			}
		}
	return null;	
	}
	public ArrayList<Employee> getEmployees() {
		return list;
	}
	public String  registerEnd(int ID) {
		String aaa=null;
		for(DakaInfo a :message) {
			if(a.getID()==ID) {
				int aa=a.setEnd();
				if(aa==0) {	
					aaa="卡号："+ID+"  今天还没有签到，无法签退";
				}else if(aa==-1) {
					aaa="你今天已经签退过了！";
				}else if(aa==1) {
					aaa="卡号："+ID+"   签退成功";
				}
	
			}
		}
		return aaa;
	}
	public void saveCom() {
		ObjectOutputStream aa = null;
		try {
			File f1=new File("./f1.txt");
			if(f1.exists()) f1.delete();
			f1.createNewFile();
			aa = new ObjectOutputStream(new FileOutputStream(f1));
			aa.writeObject(list);
			aa.flush();
			 File f2=new File("./f2.txt");
			if(f2.exists()) f2.delete();
			f2.createNewFile();
			aa = new ObjectOutputStream(new FileOutputStream(f2));
			aa.writeObject(message);
			aa.flush();	
		} catch (IOException ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
		}finally {
			try {
				if(aa!=null) aa.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
}
	public void readCom() {
		File f1=new File("./f1.txt");
		File f2=new File("./f2.txt");
		if(!(f1.exists()&f2.exists())) {
			//载入员工
			addEmployee(new Employee("张三",001));
			addEmployee(new Employee("李四",002));
			addEmployee(new Employee("王五",003));
			addEmployee(new Employee("赵六",004));
		}else {
			ObjectInputStream i1 = null;
			Object o1;
			try {
				i1 = new ObjectInputStream(new FileInputStream(f1));
				o1 = i1.readObject();
				if(o1 instanceof ArrayList){
					list=(ArrayList<Employee>)o1;
				}
				i1 = new ObjectInputStream(new FileInputStream(f2));
				o1 = i1.readObject();
				if(o1 instanceof ArrayList){
					message=(ArrayList<DakaInfo>)o1;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					if(i1!=null)i1.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
	}
}