package course.choose.panel;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;


import course.choose.user.Student;

public class StudentInfo extends JPanel {
	JLabel label_sNo;
	JLabel label_sName;
	JLabel label_sSex;
	JLabel label_sSpeClass;

	JTextField sNo;
	JTextField sName;
	JTextField sSex;
	JTextField sSpeClass;


	
	public StudentInfo(Student stu) {
		this.setSize(480, 380);
		this.setBorder(BorderFactory.createTitledBorder("������Ϣ"));
		this.setLayout(null);
		label_sNo = new JLabel("ѧ��ѧ��:");
		label_sNo.setBounds(40, 10, 80, 40);		
		label_sName = new JLabel("ѧ������:");
		label_sName.setBounds(40, 40, 80, 40);
		label_sSex = new JLabel("ѧ���Ա�:");
		label_sSex.setBounds(40, 70, 80, 40);
		label_sSpeClass = new JLabel("רҵ�༶:");
		label_sSpeClass.setBounds(40, 100, 80, 40);
	
			
		sNo = new JTextField();
		sNo.setBounds(100, 20, 140, 22);
		sNo.setText(stu.getNo());
		sNo.setEditable(false);
		
		sName = new JTextField();
		sName.setBounds(100, 50, 140,22);
		sName.setText(stu.getName());
		sName.setEditable(false);
		
		sSex = new JTextField();
		sSex.setBounds(100, 80, 140, 22);
		sSex.setText(stu.getSex());
		sSex.setEditable(false);
		
		sSpeClass = new JTextField();
		sSpeClass.setBounds(100, 110, 140, 25);
		sSpeClass.setText(stu.getSpeclassNO()+"");
		sSpeClass.setEditable(false);
		add();
	
	}
	public void add() {
		add(label_sNo);
		add(label_sName);
		add(label_sSex);
		add(label_sSpeClass);	
		add(sNo);
		add(sName);
		add(sSex);
		add(sSpeClass);
	
	}
}
