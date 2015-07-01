package course.choose.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import course.choose.user.Student;

public class ChooseCourseStudentInfo extends JDialog {

	public JPanel jpanel;
	JLabel label_sNo;
	JLabel label_sName;
	JLabel label_sSex;
	JLabel label_sSpeClass;
	JLabel label_sChooseClass;

	public JTextField sNo;
	public JTextField sName;
	public JTextField sSex;
	public JTextField sSpeClass;
	public JTextField sChooseClass;

	public ChooseCourseStudentInfo() {
		this.setSize(300, 250);
		this.setModal(true);
		this.setTitle("学生信息");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		label_sNo = new JLabel("学生学号:");
		label_sNo.setBounds(40, 10, 80, 40);
		label_sName = new JLabel("学生姓名:");
		label_sName.setBounds(40, 40, 80, 40);
		label_sSex = new JLabel("学生性别:");
		label_sSex.setBounds(40, 70, 80, 40);
		label_sSpeClass = new JLabel("专业班级:");
		label_sSpeClass.setBounds(40, 100, 80, 40);
		label_sChooseClass = new JLabel("选修班级:");
		label_sChooseClass.setBounds(40, 130, 80, 40);

		sNo = new JTextField();
		sNo.setBounds(100, 20, 140, 22);
		sNo.setEditable(false);

		sName = new JTextField();
		sName.setBounds(100, 50, 140, 22);
		sName.setEditable(false);

		sSex = new JTextField();
		sSex.setBounds(100, 80, 140, 22);
		sSex.setEditable(false);

		sSpeClass = new JTextField();
		sSpeClass.setBounds(100, 110, 140, 22);
		sSpeClass.setEditable(false);
		
		sChooseClass = new JTextField();
		sChooseClass.setBounds(100, 140, 140, 22);
		sChooseClass.setEditable(false);
		add();

	}

	public void add() {
		add(label_sNo);
		add(label_sName);
		add(label_sSex);
		add(label_sSpeClass);
		add(label_sChooseClass);
		add(sNo);
		add(sName);
		add(sSex);
		add(sSpeClass);
		add(sChooseClass);

	}

}
