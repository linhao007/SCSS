package course.choose.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import course.choose.dao.Dao;
import course.choose.tree.Stree;

public class ClassDialog extends JDialog implements ActionListener{
	private JLabel label_classNo;
	private JLabel label_className;
	private JTextField classNo;
	private JTextField className;
	private JButton commit;
	private JButton bn_Canel;
	private Stree se;
	public ClassDialog(Stree se) {
		this.se = se;
		this.setSize(250, 180);
		setLayout(null);
		this.setTitle("添加班级");
		this.setLocationRelativeTo(null);
		label_classNo = new JLabel("班级号:");
		label_classNo.setBounds(20, 10, 80, 40);
		label_className = new JLabel("班级姓名:");
		label_className.setBounds(20, 40, 80, 40);
		classNo = new JTextField();
		classNo.setBounds(80, 20, 140, 22);
		className = new JTextField();
		className.setBounds(80, 50, 140,22);
		commit = new JButton("提交");
		commit.addActionListener(this);
        bn_Canel = new JButton("取消");
        bn_Canel.addActionListener(this);
        commit.setBounds(60, 90, 60, 20);
        bn_Canel.setBounds(150, 90, 60, 20);
      
        add(label_classNo);
        add(classNo);
        add(label_className);
        add(className);
        add(commit);
        add(bn_Canel);
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		// 判断输入的班级号是否重复
		boolean flag1 = false;
		String str = "select sclassno from tb_SpeClass";
		ResultSet rs = Dao.executeQuery(str);
		try {
			while(rs.next()) {
				if(rs.getString("sclassno").equals(classNo.getText())){
					flag1 = true;
				}		
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String no = classNo.getText();
		String classname = className.getText();
		if(e.getSource() == commit) {
			if (no.equals("")||classname.equals("") ) {//判断输入的班级号是否为空值
				JOptionPane.showMessageDialog(null, "请填全信息");
				return;
			} else if(flag1){
				JOptionPane.showMessageDialog(null, "存在"
						+ classNo.getText() + "班级号！");
				classNo.setText("");
			}else {
				int iclassname;
				try {
				iclassname = Integer.parseInt(no);
				}catch(NumberFormatException ef) {
					JOptionPane.showMessageDialog(null, "班级号应为纯数字,如：1201");
					classNo.setText("");
					className.setText("");
					return;
				}
				Object defaultmodel = se.defaultmodel;
				DefaultMutableTreeNode node = se.getSelectedNode();
				((DefaultTreeModel) defaultmodel).insertNodeInto(new DefaultMutableTreeNode(no),
						node, node.getChildCount());
				String sql = "insert into tb_SpeClass values(" + iclassname+",'"+className.getText()
						+ "')";
				Dao.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "添加成功");
				this.setVisible(false);
			}
		}else if(e.getSource() == bn_Canel) {
			this.setVisible(false);
		}
	}
}
