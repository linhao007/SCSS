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
		this.setTitle("��Ӱ༶");
		this.setLocationRelativeTo(null);
		label_classNo = new JLabel("�༶��:");
		label_classNo.setBounds(20, 10, 80, 40);
		label_className = new JLabel("�༶����:");
		label_className.setBounds(20, 40, 80, 40);
		classNo = new JTextField();
		classNo.setBounds(80, 20, 140, 22);
		className = new JTextField();
		className.setBounds(80, 50, 140,22);
		commit = new JButton("�ύ");
		commit.addActionListener(this);
        bn_Canel = new JButton("ȡ��");
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
		
		// �ж�����İ༶���Ƿ��ظ�
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
			if (no.equals("")||classname.equals("") ) {//�ж�����İ༶���Ƿ�Ϊ��ֵ
				JOptionPane.showMessageDialog(null, "����ȫ��Ϣ");
				return;
			} else if(flag1){
				JOptionPane.showMessageDialog(null, "����"
						+ classNo.getText() + "�༶�ţ�");
				classNo.setText("");
			}else {
				int iclassname;
				try {
				iclassname = Integer.parseInt(no);
				}catch(NumberFormatException ef) {
					JOptionPane.showMessageDialog(null, "�༶��ӦΪ������,�磺1201");
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
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				this.setVisible(false);
			}
		}else if(e.getSource() == bn_Canel) {
			this.setVisible(false);
		}
	}
}
