package course.choose.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import course.choose.dao.Dao;
import course.choose.dialog.ChooseCourseStudentInfo;
import course.choose.dialog.SDialog;
import course.choose.panel.StudentInfo;
import course.choose.user.Operator;
import course.choose.user.Teacher;

public class ChooseCourseStudentTree {
	JTree tree;
	public JScrollPane jp;
	private DefaultTreeModel philosophers;
	private DefaultMutableTreeNode rootNode;
	ResultSet rs1;
	ResultSet rs2;
	ResultSet rs3;
	
	Teacher tea;
	public ChooseCourseStudentTree(Teacher t) {
		tea = t;
		DefaultMutableTreeNode philosophersNode = getPhilosopherTree();
		philosophers = new DefaultTreeModel(philosophersNode);
		tree = new JTree(philosophers);
		jp = new JScrollPane(tree);

		tree.addMouseListener(new MouseAdapter() { //双击查看学生信息
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
							.getLastSelectedPathComponent();
					if(node == null) {
						
					}else {
					Object nodeInfo = node.getUserObject();
					StudentLeafNode snoss;
					if (node.isRoot()) {
	
					} else {
						if (node.isLeaf()) {
							snoss = (StudentLeafNode) nodeInfo;
							checkSname cs = new checkSname(snoss.getNo());
							ChooseCourseStudentInfo ccs = new ChooseCourseStudentInfo();
							ccs.sNo.setText(cs.SNo);
							ccs.sName.setText(cs.SName);
							ccs.sSex.setText(cs.SSex);
							ccs.sSpeClass.setText(cs.SClassNo);
							ccs.sChooseClass.setText(cs.ChooseClassNo);
							ccs.setVisible(true);
						} else {
					
						}
					}

				}
			}
			}
		});
	}

//	private DefaultMutableTreeNode getSelectedNode() {
//		return (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//	}

	private DefaultMutableTreeNode getPhilosopherTree() {
		rs3 = Dao.executeQuery("select CName from tb_Course where TNo="
				+ tea.getNo());
		String choosecoursename = "";
		try {
			while (rs3.next()) {
				choosecoursename = rs3.getString("CName");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		rootNode = new DefaultMutableTreeNode(choosecoursename);
		String sql = "select ChooseClassNo from tb_ChooseClass where ChooseClassName='"
				+ choosecoursename + "'";

		rs1 = Dao.executeQuery(sql);
		String s;
		try {
			while (rs1.next()) {
				s = rs1.getString("ChooseClassNo");
				String sql2 = "select tb_Student.SNo,SName,ssex,sclassno from tb_SC,tb_Student where ChooseClassNo = '"
						+ s + "'and tb_SC.SNo=tb_Student.SNo";
				DefaultMutableTreeNode ban1 = new DefaultMutableTreeNode(s);
				rootNode.add(ban1);
				rs2 = Dao.executeQuery(sql2);
				String names;
				String snos;

				while (rs2.next()) {
					names = rs2.getString("SName");
					snos = rs2.getString("SNo");
					StudentLeafNode sde = new StudentLeafNode(snos, names);
					ban1.add(new DefaultMutableTreeNode(sde));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rootNode;
	}
	
	class checkSname {

		ResultSet rs1;
		String SNo = "";
		String SName = "";
		String SSex = "";
		String SClassNo = "";
		String ChooseClassNo = "";

		checkSname(String nodeInfo) {

			rs1 = Dao
					.executeQuery("select st.SNo,SName,SSex,SClassNo,ChooseClassNo from tb_Student st,tb_SC sc where st.SNo=sc.SNo and st.SNo="
							+ nodeInfo);
			try {
				while (rs1.next()) {
					SNo = rs1.getString("SNo");
					SName = rs1.getString("SName");
					SSex = rs1.getString("SSex");
					SClassNo = rs1.getString("SClassNo");
					ChooseClassNo = rs1.getString("ChooseClassNo");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
