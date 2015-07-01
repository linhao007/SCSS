package course.choose.dialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import course.choose.dao.Dao;
import course.choose.tree.Ctree;

public class CDialog extends JDialog implements ActionListener {
	private JLabel label_Cno;
	private JLabel label_Cname;
	private JLabel label_ClassNo;
	private JComboBox jComboBox_Cno;
	private JTextField jTextFiled_Cname;
	private JTextField jTextFiled_ClassNo;
	private JButton submit;
	private JButton cancel;
	private Ctree se;
	private Object item1 = 11;
	int flag = 0;

	public CDialog(Ctree se) {

		this.se = se;
		init();

	}

	public void init() {
		this.setSize(250, 200);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		label_Cno = new JLabel("课程号：");
		label_Cno.setBounds(30, 20, 100, 22);
		label_Cname = new JLabel("课程名：");
		label_Cname.setBounds(30, 60, 100, 22);
		label_ClassNo = new JLabel("班级号:");
		label_ClassNo.setBounds(30, 100, 100, 22);

		jTextFiled_Cname = new JTextField();
		jTextFiled_Cname.setBounds(90, 60, 140, 22);
		jTextFiled_Cname.setEditable(false);

		jComboBox_Cno = new JComboBox();
		jComboBox_Cno.setBounds(90, 20, 140, 22);
		try {
			String sql = "select CNo,Cname from tb_Course";
			ResultSet rs = Dao.executeQuery(sql);			
			while (rs.next()) {
				jComboBox_Cno.addItem(rs.getInt("Cno"));// 获取数据库中的
			}
			jTextFiled_Cname.setText("野生动物猎奇");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jComboBox_Cno.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				item1 = jComboBox_Cno.getSelectedItem(); // 获得当前选中的值
				String sql = "select cname from tb_Course where cno=" + item1;
				ResultSet rs = Dao.executeQuery(sql);
				try {
					while (rs.next())
						jTextFiled_Cname.setText(rs.getString("cname"));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		jTextFiled_ClassNo = new JTextField();
		jTextFiled_ClassNo.setBounds(90, 100, 140, 22);

		submit = new JButton("提交");
		submit.setBounds(40, 130, 60, 22);
		cancel = new JButton("取消");
		cancel.setBounds(130, 130, 60, 22);

		add(label_Cno);
		add(label_Cname);
		add(label_ClassNo);
		add(jComboBox_Cno);
		add(jTextFiled_Cname);
		add(jTextFiled_ClassNo);
		add(submit);
		add(cancel);
		submit.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			boolean flag1 = true; // 判断输入的课程号是否含有数字
			boolean flag2 = false; // 判断输入的课程号中间是否有空格
			for (int i = 0; i < jTextFiled_ClassNo.getText().length(); i++) {
				char[] ch = jTextFiled_ClassNo.getText().toCharArray();
				if (Character.isDigit(ch[i])) {
					flag1 = false;
				}
			}
			for (int i = 0; i < jTextFiled_ClassNo.getText().length(); i++) {
				if (jTextFiled_ClassNo.getText().charAt(i) == ' ')
					flag2 = true;
			}
			// 判断输入的班级号是否重复
			boolean flag3 = false;
			String str = "select chooseclassno from tb_chooseclass";
			ResultSet rs = Dao.executeQuery(str);
			try {
				while(rs.next()) {
					if(rs.getString("chooseclassno").equals(jTextFiled_ClassNo.getText())){
						flag3 = true;
					}		
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (jTextFiled_ClassNo.getText().isEmpty() || flag1 || flag2) {
				JOptionPane.showMessageDialog(null, "请填写正确的班级号！如：1201");
			} else if (flag3) {
				JOptionPane.showMessageDialog(null, "该课程存在"
						+ jTextFiled_ClassNo.getText() + "班级号！");
			} else {
				//System.out.println(item1);
				String sql = "insert into tb_ChooseClass values("
						+ jTextFiled_ClassNo.getText() + ",'"
						+ jTextFiled_Cname.getText() + "'," + item1 + ")";
				Dao.executeUpdate(sql);
				se.update(jTextFiled_ClassNo.getText() + "");
				this.dispose();
			}

		} else if (e.getSource() == cancel) {
			this.dispose();
		}

	}
}
