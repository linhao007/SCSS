package course.choose.tree;

import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import course.choose.dao.Dao;
import course.choose.dialog.CDialog;
import course.choose.frame.Administrator;
import course.choose.login.PassWord;

public class Ctree {
	static JTree tree;
	Administrator frame;
	ResultSet rs1;
	ResultSet rs2;
	ResultSet rs3;
	ResultSet rs4;
	public DefaultTreeModel philosophers;
	public DefaultMutableTreeNode rootNode;
	public static DefaultTreeModel model;
	private JPopupMenu popupMenu;
	final JMenuItem menuItem1 = new JMenuItem();
	final JMenuItem menuItem2 = new JMenuItem();
	public JScrollPane jp;
	Administrator ator;

	// sd.updatetree(tree);

	public Ctree(Administrator ator) {
		this.ator = ator;
		DefaultMutableTreeNode philosophersNode = getPhilosopherTree();
		philosophers = new DefaultTreeModel(philosophersNode);
		tree = new JTree(philosophers);
		jp = new JScrollPane(tree);
		shows();
	}

	public void addPhilosopher() {
		DefaultMutableTreeNode node = getSelectedNode();
		if (node == null) {
			JOptionPane.showMessageDialog(null, "请先选择一个区域", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (node.isRoot()) {
			CDialog cg = new CDialog(this);
			cg.setVisible(true);
		}

	}

	private DefaultMutableTreeNode getSelectedNode() {
		if (tree != null)
			return (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		else
			return null;
	}

	private DefaultMutableTreeNode getPhilosopherTree() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("选修课班级");
		String sql = "select distinct ChooseClassName from tb_ChooseClass";
		rs1 = Dao.executeQuery(sql);
		String s;
		try {
			// while (rs1.next()) {
			// s = rs1.getString("ChooseClassName");
			String sql2 = "select ChooseClassNo from tb_ChooseClass";
			// DefaultMutableTreeNode ban1 = new DefaultMutableTreeNode(s);
			// rootNode.add(ban1);
			rs2 = Dao.executeQuery(sql2);
			while (rs2.next()) {
				String chooseclassNo = rs2.getString("ChooseClassNo");
				String sql3 = "select SName from tb_ChooseClass c,tb_SC sc,tb_Student s where sc.ChooseClassNo = '"
						+ chooseclassNo
						+ "' and c.ChooseClassNo = '"
						+ chooseclassNo + "' and sc.SNo = s.SNo ";
				DefaultMutableTreeNode ban2 = new DefaultMutableTreeNode(
						chooseclassNo);
				rootNode.add(ban2);
				rs3 = Dao.executeQuery(sql3);
				while (rs3.next()) {
					String sname = rs3.getString("SName");
					DefaultMutableTreeNode ban3 = new DefaultMutableTreeNode(
							sname);
					ban2.add(ban3);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rootNode;

	}

	public void update(String names) {
		DefaultMutableTreeNode insertparent = getSelectedNode();
		philosophers.insertNodeInto(new DefaultMutableTreeNode(names),
				insertparent, insertparent.getChildCount());
		ator.setVisible(true);
		jp.setVisible(true);

	}

	public void shows() {
		if (tree != null) {
			tree.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getModifiers() == Event.META_MASK) {
						addPopup(jp, popupMenu);
						popupMenu.show(jp, e.getX(), e.getY());
					}
				}
			});

			popupMenu = new JPopupMenu();
			final JMenuItem menuItem1 = new JMenuItem();
			final JMenuItem menuItem2 = new JMenuItem();
			menuItem1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addPhilosopher();
				}
			});
			menuItem2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
							.getLastSelectedPathComponent();
					model = (DefaultTreeModel) tree.getModel();
					if (node == null) {
						JOptionPane.showMessageDialog(null, "请先选择一个区域",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (node.isRoot()) {
						JOptionPane.showMessageDialog(null, "该名称不能删除！");
						return;
					} else {
						remove(node);
					}
				}
			});
			menuItem1.setText("添加");
			menuItem2.setText("删除");
			popupMenu.add(menuItem1);
			popupMenu.add(menuItem2);
		}
	}

	private static void remove(DefaultMutableTreeNode node) {
		String no = node.toString();
		int ino = 0;
		ino = Integer.parseInt(no);
		model = (DefaultTreeModel) tree.getModel();
		try {
			String sql = "select sno from tb_SC where chooseclassno=" + ino;
			ResultSet rs = Dao.executeQuery(sql);
			try {
				if (!rs.next()) {
					int n = JOptionPane.showConfirmDialog(null, "确认删除吗?",
							"提示框", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						String sq = "delete from tb_ChooseClass where chooseclassno="
								+ ino;
						Dao.executeUpdate(sq);
						model.removeNodeFromParent(node);
					}
				} else {
					int n = JOptionPane.showConfirmDialog(null,
							"该班级有学生，确认删除吗?", "提示框", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						String sq = "delete from tb_ChooseClass where chooseclassno="
								+ ino;
						Dao.executeUpdate(sq);
						model.removeNodeFromParent(node);
					} 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (NumberFormatException e) {

		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger())
					showMenu(e);
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger())
					showMenu(e);
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

}
