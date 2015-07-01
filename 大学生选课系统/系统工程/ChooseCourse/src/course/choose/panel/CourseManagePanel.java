package course.choose.panel;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import course.choose.dao.Dao;
import course.choose.login.LogRecord;
import course.choose.login.PassWord;

public class CourseManagePanel extends JPanel implements ActionListener {
	private JToolBar jJToolBarBar;
	private JButton jButton_CheckAll;
	private JButton jButton_Add;
	private JButton jButton_Query;
	private JButton jButton_Delete;
	private JScrollPane jScrollPane;
	private JTable jTable;
	private ResultSet rs;
	private ResultSet rs1;
	private ResultSet rsline;
	String sql = "select cno,cname,t.tname,chooseclassno from(select c.cno,c.cname,tno,chooseclassno from tb_Course c left join tb_ChooseClass cc on c.cno=cc.cno)as ts,tb_Teacher t where ts.tno=t.tno";
	String sql1 = "select t.tname from tb_Teacher t where t.tno not in(select c.tno from tb_Course c)";


	DefaultTableModel model;
	private JPanel jpanel;

	public CourseManagePanel() {
		init();
	}

	private void init() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("课程管理"));
		this.setSize(490, 380);

		jButton_CheckAll = new JButton();
		jButton_CheckAll.setText("总览");
		jButton_Add = new JButton();
		jButton_Add.setText("添加");
		jButton_Query = new JButton();
		jButton_Query.setText("查询");
		jButton_Delete = new JButton();
		jButton_Delete.setText("删除");

		jTable = new JTable();

		jJToolBarBar = new JToolBar();
		jJToolBarBar.add(jButton_CheckAll);
		jJToolBarBar.add(jButton_Add);
		jJToolBarBar.add(jButton_Query);
		jJToolBarBar.add(jButton_Delete);

		jButton_CheckAll.addActionListener(this);
		jButton_Add.addActionListener(this);
		jButton_Query.addActionListener(this);
		jButton_Delete.addActionListener(this);
		model = new DefaultTableModel();
		showAll(sql);
		jpanel = new JPanel();
		jpanel.setLayout(null);
		jpanel.add(jScrollPane);
		this.add(jpanel, BorderLayout.CENTER);
		this.add(jJToolBarBar, BorderLayout.NORTH);
		this.setVisible(true);
	}

	// 显示所有课程信息
	public void showAll(String sql) {
		int i = this.getTotalRow(sql);// 获取要查询的记录集数目
		if (i == 0) {
			JOptionPane.showMessageDialog(null, "没有该课程！");
		} else {
			model = new DefaultTableModel();
			model.setRowCount(i);// 设置行数
			model.addColumn("课程编号");
			model.addColumn("课程名称");
			model.addColumn("任课教师");
			model.addColumn("课程班级");
			int counter;
			ResultSet rs = Dao.executeQuery(sql);
			try {
				for (counter = 0; rs.next(); counter++) {
					model.setValueAt(rs.getString("cno"), counter, 0);
					model.setValueAt(rs.getString("cname"), counter, 1);
					model.setValueAt(rs.getString("tname"), counter, 2);
					if (rs.getString("chooseclassno") == null) {
						model.setValueAt("未开设班级", counter, 3);
					} else {
						model.setValueAt(rs.getString("chooseclassno"),
								counter, 3);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		jTable.setModel(model);
		jTable.setAutoCreateRowSorter(true);
		jTable.setRowHeight(20);
		jTable.setCursor(new Cursor(12));
		jTable.getTableHeader().setReorderingAllowed(false);

		jScrollPane = new JScrollPane(jTable);
		jScrollPane.setViewportView(jTable);
		jScrollPane.setBounds(5, 10, 460, 320);
		jScrollPane.setCursor(new Cursor(12));
	}

	/**
	 * 获取记录集总数模块
	 */
	public int getTotalRow(String sql) {// 获取记录集总数
		rsline = Dao.executeQuery(sql);
		int i = 0;
		try {
			while (rsline.next()) {
				i++;
			}
		} catch (Exception er) {
		}
		return i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LogRecord lo = new LogRecord();
		if (e.getSource() == jButton_CheckAll) {
			lo.addLog(PassWord.userNames + "   执行总览课程操作");
			jpanel.removeAll();
			showAll(sql);
			jpanel.add(jScrollPane);
			jpanel.update(jpanel.getGraphics());
			this.add(jpanel, BorderLayout.CENTER);
			this.setVisible(true);
		} else if (e.getSource() == jButton_Add) {
			try {
				ResultSet rs2 = Dao.executeQuery(sql1);
				if (!rs2.next()) {
					JOptionPane.showMessageDialog(null, "目前没有空闲的老师，无法添加课程！");
				} else {
					AddCourse ac = new AddCourse();
					ac.setVisible(true);
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jpanel.removeAll();
			showAll(sql);
			jpanel.add(jScrollPane);
			jpanel.update(jpanel.getGraphics());
			this.add(jpanel, BorderLayout.CENTER);
			this.setVisible(true);
		} else if (e.getSource() == jButton_Delete) {
			if (jTable.getSelectedRow() != -1) {
				String str = jTable.getValueAt(jTable.getSelectedRow(), 3)
						.toString();
				String str1 = jTable.getValueAt(jTable.getSelectedRow(), 0)
						.toString();
				int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					if (str.equals("未开设班级")) {
						String sql1 = "delete from tb_Course where cno=" + str1
								+ "";
						Dao.executeUpdate(sql1);
						lo.addLog(PassWord.userNames + "   执行了删除选课编号为" + str1
								+ "的操作");
					} else {
						String sql2 = "delete from tb_Chooseclass where chooseclassno="
								+ str + "";
						Dao.executeUpdate(sql2);
						lo.addLog(PassWord.userNames + "   执行了删除选课班级为" + str
								+ "的操作");
					}
					jpanel.removeAll();
					showAll(sql);
					jpanel.add(jScrollPane);
					jpanel.update(jpanel.getGraphics());
					this.add(jpanel, BorderLayout.CENTER);
					this.setVisible(true);
				} else if (n == JOptionPane.NO_OPTION) {

				}

			} else {
				JOptionPane.showMessageDialog(null, "请选择要删除的行！");
			}
		} else if (e.getSource() == jButton_Query) {
			String input = JOptionPane.showInputDialog("按选课编号查询，请输入要查询的选课编号：");
			boolean flag1 = false; // 判断输入的课程号前导空白和尾部空白
			boolean flag2 = false; // 判断输入的课程号中间是否有空格
			boolean flag3 = true; // 判断输入的课程号是否含有数字
			try {
				flag1 = input.trim().equals("");
				for (int i = 0; i < input.length(); i++) {
					if (input.charAt(i) == ' ')
						flag2 = true;
				}
				for (int i = 0; i < input.length(); i++) {
					char[] ch = input.toCharArray();
					if (Character.isDigit(ch[i])) {
						flag3 = false;
					}
				}

				if (input.isEmpty() || flag1 || flag2 || flag3) {
					JOptionPane.showMessageDialog(null, "请正确输入课程号！ 如：11");
				} else {
					String str = "select cno,cname,t.tname,chooseclassno from(select c.cno,c.cname,tno,chooseclassno from tb_Course c left join tb_ChooseClass cc on c.cno=cc.cno)as ts,tb_Teacher t where ts.tno=t.tno and ts.cno="
							+ input + "";
					lo.addLog(PassWord.userNames + "   执行选课查询操作");
					jpanel.removeAll();
					showAll(str);
					jpanel.add(jScrollPane);
					jpanel.update(jpanel.getGraphics());
					this.add(jpanel, BorderLayout.CENTER);
					this.setVisible(true);
				}
			} catch (Exception ex) {

			}
		}

	}

	// 添加课程
	class AddCourse extends JDialog implements ActionListener {

		private JPanel jpanel;
		private JLabel jLabel_Cno;
		private JLabel jLabel_Cname;
		private JLabel jLabel_Tname;
		private JLabel jLabel_Classno;
		private JTextField jTextField_Cno;
		private JTextField jTextField_Cname;
		private JComboBox jComboBox_Tname;
		// private JComboBox jComboBox_Classno;
		private JButton submit;
		private JButton cancel;
		private Object item1 = null;
		

		public AddCourse() {
			init();
		}

		public void init() {
			this.setSize(300, 250);
			this.setModal(true);
			this.setTitle("添加课程");
			this.setLocationRelativeTo(null);
			jLabel_Cno = new JLabel("课程编号:");
			jLabel_Cno.setBounds(20, 40, 80, 40);
			jLabel_Cname = new JLabel("课程名称:");
			jLabel_Cname.setBounds(20, 80, 80, 40);
			jLabel_Tname = new JLabel("任课老师:");
			jLabel_Tname.setBounds(20, 120, 80, 40);

			jTextField_Cno = new JTextField();
			jTextField_Cno.setBounds(80, 50, 140, 22);
			jTextField_Cname = new JTextField();
			jTextField_Cname.setBounds(80, 90, 140, 22);

			/**
			 * 下拉框模块
			 */
			jComboBox_Tname = new JComboBox();
			jComboBox_Tname.setBounds(80, 130, 140, 22);

			try {
				ResultSet rs3 = Dao.executeQuery(sql1);
				while (rs3.next()) {
					jComboBox_Tname.addItem(rs3.getString("tname"));// 获取数据库中的
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jComboBox_Tname.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					item1 = jComboBox_Tname.getSelectedItem(); // 获得当前选中的值
				}
			});

			submit = new JButton("确定");
			submit.setBounds(50, 175, 60, 25);
			cancel = new JButton("取消");
			cancel.setBounds(160, 175, 60, 25);

			jpanel = new JPanel();
			jpanel.setLayout(null);
			jpanel.setBorder(BorderFactory.createTitledBorder("请输入完整信息"));
			jpanel.add(jLabel_Cno);
			jpanel.add(jLabel_Cname);
			jpanel.add(jLabel_Tname);
			jpanel.add(jTextField_Cno);
			jpanel.add(jTextField_Cname);
			jpanel.add(jComboBox_Tname);
			jpanel.add(submit);
			jpanel.add(cancel);
			this.setContentPane(jpanel);
			submit.addActionListener(this);
			cancel.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LogRecord lo = new LogRecord();
			if (e.getSource() == submit) {
				int index = jComboBox_Tname.getSelectedIndex();
				String Type = (String) jComboBox_Tname.getItemAt(index);
				String str = "select tno from tb_Teacher where tname='" + Type
						+ "'";
				ResultSet rs = Dao.executeQuery(str);
				String sql2 = null;
				String strCno = "select cno,cname from tb_Course";
				ResultSet rs1 = Dao.executeQuery(strCno);
				boolean flag1 = false; // 判断输入的课程号前导空白和尾部空白
				boolean flag2 = false; // 判断输入的课程号中间是否有空格
				boolean flag3 = true; // 判断输入的课程号是否含有数字
				boolean flag4 = false; // 判断输入的课程号是否存在
				boolean flag5 = false; // 判断输入的课程名是否存在
				try {
					flag1 = jTextField_Cno.getText().trim().equals("");
					for (int i = 0; i < jTextField_Cno.getText().length(); i++) {
						if (jTextField_Cno.getText().charAt(i) == ' ')
							flag2 = true;
					}
					for (int i = 0; i < jTextField_Cno.getText().length(); i++) {
						char[] ch = jTextField_Cno.getText().toCharArray();
						if (Character.isDigit(ch[i])) {
							flag3 = false;
						}
					}
					while (rs1.next()) {
						if (jTextField_Cno.getText().equals(
								rs1.getString("cno"))) {
							flag4 = true;
						}
						if (jTextField_Cname.getText().equals(
								rs1.getString("cname"))) {
							flag5 = true;
						}
					}
					if (jTextField_Cno.getText().isEmpty() || flag1 || flag2
							|| flag3) {
						JOptionPane.showMessageDialog(null, "请正确输入课程号！ 如：11");
						jTextField_Cno.setText("");
					} else if (flag4) {
						JOptionPane.showMessageDialog(null, "已存在该课程号！");
						jTextField_Cno.setText("");
					} else if (jTextField_Cname.getText().isEmpty() || flag1
							|| flag2 || flag3) {
						JOptionPane.showMessageDialog(null, "请正确输入课程名！ 如：风水植物");
						jTextField_Cname.setText("");
					} else if (flag5) {
						JOptionPane.showMessageDialog(null, "已存在该课程名！");
						jTextField_Cname.setText("");
					} else {
						while (rs.next())
							sql2 = "Insert Into tb_Course Values('"
									+ jTextField_Cno.getText() + "','"
									+ jTextField_Cname.getText() + "','"
									+ rs.getString("tno") + "')";
						Dao.executeUpdate(sql2);
						lo.addLog(PassWord.userNames + "   添加了课程编号为"
								+ jTextField_Cno.getText().trim() + "的课程！");
						JOptionPane.showMessageDialog(null, "添加成功！");
						dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (e.getSource() == cancel) {
				dispose();
				lo.addLog(PassWord.userNames + " 添加课程失败");
			}

		}
	}
}
