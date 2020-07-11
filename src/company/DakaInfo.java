package company;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DakaInfo implements Serializable{
	private int ID;
	private Date start=null;
	private Date end=null;
	private ArrayList<String> listStart=new ArrayList<String>();
	private ArrayList<String> listEnd=new ArrayList<String>();
	private SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public DakaInfo(int ID) {
		this.ID=ID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public ArrayList<String> getStart() {
		if(start==null)return null;;
		return listStart;
	}
	public boolean setStart() {
		Date a=new Date();
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		if(start==null) {
			start=a;
			listStart.add(ss.format(start));
			return true;//true表示可以签到
		}else {
			String a1=s.format(start);
			if(a1.equals(s.format(a)))return false;//表示签到失败
			else {
				start=a;
				listStart.add(ss.format(start));
				return true;
			}
		}
		
	}
	public ArrayList<String> getEnd() {
		if(end==null)return null;
		return listEnd;
	}
	
	public int setEnd() {
		Date a=new Date();
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		if(start==null) {
			return 0;  //0表示未签到，所以不能签退
		}else if(!(s.format(start).equals(s.format(a)))) {
			//如果start中的时间是昨天的时间，那么今天是不能签退的，就是今天未签到的状态,不能签退
			return 0;
			
		}else if(end==null) {
			end=a;
			listEnd.add(ss.format(end));
			return 1;//1表示签退成功
		}else {
			String a1=s.format(end);
			if(a1.equals(s.format(a)))return -1;//-1表示已经签退过了
			else {
				end=a;
				listEnd.add(ss.format(end));
				return 1;//1表示签退成功
			}
		}
	}


}
