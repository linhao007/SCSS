package course.choose.dialog;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import course.choose.dao.Dao;
import course.choose.tree.Ttree;


public class TDialog extends JDialog implements ActionListener {
	public JPanel jpanel;
	JLabel label_tNo;
	JLabel label_tName;
	JLabel label_tSex;
	JLabel label_tpassWord;
	public JTextField tNo;
	public JTextField tName;
	public JTextField tpassWord;
	public JRadioButton jRadioButton_Boy;
	public JRadioButton jRadioButton_Girl;
	public ButtonGroup buttonts;
	public String ts = "男";
	public JButton submit;
	public JButton cancel;
	
	String name;
	int flag = 0;
	JDialog jg;

	private Ttree se;
	

	public TDialog(Ttree se) {
		this.se = se;
		init();
	}
	public void init(){
		this.setSize(300, 250);
		this.setModal(true);
		this.setTitle("添加教师");
		this.setLocationRelativeTo(null);
		label_tNo = new JLabel("教师工号:");
		label_tNo.setBounds(40, 20, 80, 40);
		label_tName = new JLabel("教师姓名:");
		label_tName.setBounds(40, 50, 80, 40);
		label_tSex = new JLabel("教师性别:");
		label_tSex.setBounds(40, 80, 80, 40);
		label_tpassWord = new JLabel("教师密码:");
		label_tpassWord.setBounds(40, 110, 80, 40);
		tNo = new JTextField();
		tNo.setBounds(100, 30, 140, 22);
		tName = new JTextField();
		tName.setBounds(100, 60, 140,22);
		tpassWord = new JTextField();
		tpassWord.setBounds(100, 120, 140,22);
		
		jRadioButton_Boy = new JRadioButton("男", true);// 默认选择男
		jRadioButton_Boy.setBounds(100, 90, 50, 22);
		jRadioButton_Girl = new JRadioButton("女", false);
		jRadioButton_Girl.setBounds(160, 90, 50, 22);
		
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
		
		submit = new JButton("提交");
		submit.setBounds(50, 165, 60, 25);
		cancel = new JButton("取消");
		cancel.setBounds(170, 165, 60, 25);
		
	
		jpanel = new JPanel();
		jpanel.setLayout(null);
		jpanel.setBorder(BorderFactory.createTitledBorder("请输入完整信息"));
		jpanel.add(label_tNo);
		jpanel.add(label_tName);
		jpanel.add(label_tSex);
		jpanel.add(label_tpassWord);
		jpanel.add(tNo);
		jpanel.add(tName);
		jpanel.add(jRadioButton_Boy);
		jpanel.add(jRadioButton_Girl);
		jpanel.add(tpassWord);
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
			boolean flag1 = true; // 判断输入的工号是否含有数字
			boolean flag2 = false; // 判断输入的工号中间是否有空格
			boolean flag4 = false; // 判断输入的姓名是否含有数字
			
			for (int i = 0; i < tNo.getText().length(); i++) {
				char[] ch = tNo.getText().toCharArray();
				if (Character.isDigit(ch[i])) {
					flag1 = false;
				}
			}
			for (int i = 0; i < tName.getText().length(); i++) {
				char[] ch = tName.getText().toCharArray();
				if (Character.isDigit(ch[i])) {
					flag4 = true;
				}
			}
			
			for (int i = 0; i < tNo.getText().length(); i++) {
				if (tNo.getText().charAt(i) == ' ')
					flag2 = true;
			}
			
			// 判断输入的工号是否重复
			boolean flag3 = false;
			String str = "select tno from tb_Teacher";
			ResultSet rs = Dao.executeQuery(str);
			try {
				while(rs.next()) {
					if(rs.getString("tno").equals(tNo.getText())){
						flag3 = true;
					}		
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (tNo.getText().trim().length() < 1
					|| tName.getText().trim().length() < 1
					|| tpassWord.getText().trim().length() < 1) {
				JOptionPane.showMessageDialog(null, "请填全信息");
			}else if(flag1||flag2||tNo.getText().length() != 4){
				JOptionPane.showMessageDialog(null, "请填写正确的工号，如：1111");
				tNo.setText("");
			}else if (flag3) {
				JOptionPane.showMessageDialog(null, "已存在"
						+ tNo.getText() + "工号！");
				tNo.setText("");
			}else if(flag4){
				JOptionPane.showMessageDialog(null, "请输入正确的姓名，如李四");
				tName.setText("");
			}else{			
			int No = Integer.parseInt(tNo.getText());
			name = tName.getText();
			int pwd = Integer.parseInt(tpassWord.getText());	
			String sql = "insert into tb_Teacher values(" + No + "," + "'"
					+ name + "'," + "'" + ts + "',"
					+ pwd + ")";
			Dao.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "添加"+name+"成功！");
			se.update(name);
			this.dispose();
			}
		}
	}
}
