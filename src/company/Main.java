package company;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import check.Employee;
//import check.Main;

public class Main extends JFrame implements Serializable{
	private Timer time;
	private static Company com=new Company();	
	private JPanel contentPane;
	private JTextField search;
	private JButton yes;
	private JButton registerStart;
	private JButton registerEnd  ;
	private JTextArea message;
	private JButton exit ;
	private JList<String> allEmployee ;
	private DefaultListModel<String> model;
    private JLabel showTime ;
	private JScrollPane js;

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		//创建窗体
		setTitle("员工打卡系统");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//设置文本框
		search=new JTextField();
		search.setBounds(10, 53, 238, 25);
		contentPane.add(search);
		//文本框确定按钮
		yes = new JButton("查找");
		yes.setBounds(270, 54, 77, 23);
		contentPane.add(yes);
		showTime = new JLabel();
		showTime.setFont(new Font("楷体",Font.BOLD,15));
		showTime.setBounds(10, 20, 238, 25);
		contentPane.add(showTime);	
		
		//签到按钮
		registerStart=new JButton("签到");
		registerStart.setBounds(28, 98, 77, 23);
		contentPane.add(registerStart);

		//签退按钮
		registerEnd  =new JButton("签退");
		registerEnd.setBounds(163, 98, 77, 23);
		contentPane.add(registerEnd);		


		// 设置文本域
		message=new JTextArea();
		message.setBounds(10, 143, 337, 143);
		message.setFont(new Font("楷体",Font.PLAIN,14));
		contentPane.add(message);

		//退出按钮
		exit = new JButton("退出");
		exit.setBounds(400, 245, 93, 23);
		contentPane.add(exit);
		
		//列表框	
		allEmployee =new JList<String>();
		model=new DefaultListModel<String>();
		for(Employee aaa:com.getEmployees()) {
			model.addElement(aaa.toString());
		}
		allEmployee.setModel(model);
		js=new JScrollPane(allEmployee);
		js.setBounds(373, 20, 137, 160);
		contentPane.add(js);

		//这是一个显示时间的标签的方法
		time=new Timer(100,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub			
				showTime.setText(new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss").format(new Date()));
			}
		});
		time.start();
		
		//监听窗口 关闭
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
			com.saveCom();
			System.exit(0);
			}
		});
		
		

		//监听列表双击事件
		
		allEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==1) {
					String a=allEmployee.getSelectedValue();
					String [] aa=a.split(":");
					search.setText(aa[1]);
					//单击触发了文本域的刷新
					try {
						int ID=Integer.parseInt(search.getText());
						Employee temp=com.searchEmployee(ID);
						if(temp==null) {
							message.setText("无此ID员工");
						}else {
							message.setText(com.showEmployee(ID));
						}	
						
					}catch(Exception ee) {
						message.setText("无法解析此ID ！");
					}
				}
			}
		});

		//监听文本框的确定点击
		yes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int ID=Integer.parseInt(search.getText());
					Employee temp=com.searchEmployee(ID);
					if(temp==null) {
						message.setText("无此ID员工");
					}else {
						message.setText(com.showEmployee(ID));
					}	
					
				}catch(Exception ee) {
					message.setText("无法解析此ID ！");
				}
				
			}
		});
		
		//监听签到按钮
		registerStart.addActionListener(new ActionListener() {		

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					int ID=Integer.parseInt(search.getText());
					Employee temp=com.searchEmployee(ID);
					if(temp==null) {
						message.setText("无此ID员工");
					}else {
						String a=com.registerStart(ID);
						message.setText(com.showEmployee(ID));//签到成功后刷新文本域
						JOptionPane.showMessageDialog(null, a);
						
					}	
					
				}catch(Exception ee) {
					message.setText("无法解析此ID ！");
				}
			}
		
		});
		
		//监听签退按钮
		registerEnd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					int ID=Integer.parseInt(search.getText());
					Employee temp=com.searchEmployee(ID);
					if(temp==null) {
						message.setText("无此ID员工");
					}else {
						String a=com.registerEnd(ID);
						message.setText(com.showEmployee(ID));//签退成功后刷新文本域
						JOptionPane.showMessageDialog(null, a);
					}	
					
				}catch(Exception ee) {
					message.setText("无法解析此ID ！");
				}
			}
		});

		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					com.saveCom();
					System.exit(0);		
			}
		});
	
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					com.readCom();
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
		}



}
