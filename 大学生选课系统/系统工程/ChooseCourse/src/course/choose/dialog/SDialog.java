package course.choose.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import course.choose.dao.Dao;
import course.choose.tree.Stree;

/**
 * 添加学生相关信息面板
 */
public class SDialog extends JDialog implements ActionListener {
	public JPanel jpanel;
	JLabel label_sNo;
	JLabel label_sName;
	JLabel label_sSex;
	JLabel label_sSpeClass;
	JLabel label_spassWord;
	public JTextField sNo;
	public JTextField sName;
	public JTextField passWord;
	public JRadioButton jRadioButton_Boy;
	public JRadioButton jRadioButton_Girl;
	public ButtonGroup buttonts;
	public String ts = "男";
	public JTextField speClass;
	public JButton submit;
	public JButton cancel;
	public String name;
	private Stree se;
	
	String str = null;
	
	public SDialog(Stree se,String str) {
		this.se = se;
		this.str = str;
		init();
	}

	public void init() {
		this.setSize(300, 250);
		this.setModal(true);
		this.setTitle("添加学生");
		this.setLocationRelativeTo(null);
		label_sNo = new JLabel("学生学号:");
		label_sNo.setBounds(40, 10, 80, 40);
		label_sName = new JLabel("学生姓名:");
		label_sName.setBounds(40, 40, 80, 40);
		label_sSex = new JLabel("学生性别:");
		label_sSex.setBounds(40, 70, 80, 40);
		label_sSpeClass = new JLabel("专业班级:");
		label_sSpeClass.setBounds(40, 100, 80, 40);
		label_spassWord = new JLabel("学生密码:");
		label_spassWord.setBounds(40, 130, 80, 40);
		sNo = new JTextField();
		sNo.setBounds(100, 20, 140, 22);
		sName = new JTextField();
		sName.setBounds(100, 50, 140,22);
		passWord = new JTextField();
		passWord.setBounds(100, 140, 140,22);
		
		jRadioButton_Boy = new JRadioButton("男", true);// 默认选择男
		jRadioButton_Boy.setBounds(100, 80, 50, 22);
		jRadioButton_Girl = new JRadioButton("女", false);
		jRadioButton_Girl.setBounds(160, 80, 50, 22);
		
		jRadioButton_Boy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ts = "男";
			}
		});
		jRadioButton_Girl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ts = "女";
			}
		});	
		gettsButtonGroup();// 加入按钮组
		
		
		speClass = new JTextField();
		speClass.setBounds(100, 110, 140,22);
		speClass.setEditable(false);
		speClass.setText(str);
		submit = new JButton("提交");
		submit.setBounds(50, 175, 60, 25);
		cancel = new JButton("取消");
		cancel.setBounds(170, 175, 60, 25);
		
	
		jpanel = new JPanel();
		jpanel.setLayout(null);
		jpanel.setBorder(BorderFactory.createTitledBorder("请输入完整信息"));
		jpanel.add(label_sNo);
		jpanel.add(label_sName);
		jpanel.add(label_sSex);
		jpanel.add(label_sSpeClass);
		jpanel.add(label_spassWord);
		jpanel.add(sNo);
		jpanel.add(sName);
		jpanel.add(jRadioButton_Boy);
		jpanel.add(jRadioButton_Girl);
		jpanel.add(speClass);
		jpanel.add(passWord);
		jpanel.add(submit);
		jpanel.add(cancel);
		submit.addActionListener(this);
		cancel.addActionListener(this);
		this.setContentPane(jpanel);
	}
	
	private void gettsButtonGroup() {// 按钮组
		if (buttonts == null) {
			buttonts = new ButtonGroup();
			buttonts.add(jRadioButton_Boy);
			buttonts.add(jRadioButton_Girl);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			dispose();
		}else if (e.getSource() == submit) {
			boolean flag1 = true; // 判断输入的学号是否含有数字
			boolean flag2 = false; // 判断输入的学号中间是否有空格
			boolean flag4 = false; // 判断输入的姓名是否含有数字
			
			for (int i = 0; i < sNo.getText().length(); i++) {
				char[] ch = sNo.getText().toCharArray();
				if (Character.isDigit(ch[i])) {
					flag1 = false;
				}
			}
			for (int i = 0; i < sName.getText().length(); i++) {
				char[] ch = sName.getText().toCharArray();
				if (Character.isDigit(ch[i])) {
					flag4 = true;
				}
			}
			
			for (int i = 0; i < sNo.getText().length(); i++) {
				if (sNo.getText().charAt(i) == ' ')
					flag2 = true;
			}
			
			// 判断输入的学号是否重复
			boolean flag3 = false;
			String str = "select sno from tb_student";
			ResultSet rs = Dao.executeQuery(str);
			try {
				while(rs.next()) {
					if(rs.getString("sno").equals(sNo.getText())){
						flag3 = true;
					}		
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (sNo.getText().trim().length() < 1
					|| sName.getText().trim().length() < 1
					|| passWord.getText().trim().length() < 1) {
				JOptionPane.showMessageDialog(null, "请填全信息");
			}else if(flag1||flag2||sNo.getText().length() != 8){
				JOptionPane.showMessageDialog(null, "请填写正确的学号，如：20121521");
				sNo.setText("");
			}else if (flag3) {
				JOptionPane.showMessageDialog(null, "已存在"
						+ sNo.getText() + "学号！");
				sNo.setText("");
			}else if(flag4){
				JOptionPane.showMessageDialog(null, "请输入正确的姓名，如李四");
				sName.setText("");
			}else{			
			int No = Integer.parseInt(sNo.getText());
			int speclassno = Integer.parseInt(speClass.getText());
			name = sName.getText();
			int pwd = Integer.parseInt(passWord.getText());	
			String sql = "insert into tb_Student values(" + No + "," + "'"
					+ name + "'," + "'" + ts + "',"
					+ pwd + "," + speclassno + ")";
			Dao.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "添加"+name+"成功！");
			se.update(name);
			this.dispose();
			}
		}
	}
}
