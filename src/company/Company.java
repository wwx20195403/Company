package company;

import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable{
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
	
}