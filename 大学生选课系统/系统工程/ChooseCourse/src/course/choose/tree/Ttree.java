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
import course.choose.dialog.TDialog;
import course.choose.frame.Administrator;

public class Ttree {
	static JTree tree;
	Administrator frame;
	ResultSet rs1;
	ResultSet rs2;
	public DefaultTreeModel philosophers;
	public DefaultMutableTreeNode rootNode;
	public static DefaultTreeModel model;
	private JPopupMenu popupMenu;
	final JMenuItem menuItem1 = new JMenuItem();
	final JMenuItem menuItem2 = new JMenuItem();
	public JScrollPane jp;
	Administrator ator;

	public Ttree(Administrator ator) {
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
			TDialog dia = new TDialog(this);
			dia.setLocationRelativeTo(null);
			dia.setVisible(true);
		}

	}

	private DefaultMutableTreeNode getSelectedNode() {
		if (tree != null)
			return (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		else
			return null;
	}

	private DefaultMutableTreeNode getPhilosopherTree() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("选修课老师");
		String sql = "select tno,TName from tb_Teacher";
		rs1 = Dao.executeQuery(sql);
		String s1;
		String s2;
		try {
			while (rs1.next()) {
				s1 = rs1.getString("TName");
				s2 = rs1.getString("tno");
				StudentLeafNode tnoe = new StudentLeafNode(s2, s1);
				// DefaultMutableTreeNode ban1 = new DefaultMutableTreeNode(s);
				rootNode.add(new DefaultMutableTreeNode(tnoe));
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
		Object nodeInfo = node.getUserObject();// 获得节点关联的数据
		StudentLeafNode tnoe = (StudentLeafNode) nodeInfo;
		String tno = tnoe.getNo();
		model = (DefaultTreeModel) tree.getModel();
		int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "提示框",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			String sql = "delete tb_Teacher where tno = " + tno;
			Dao.executeUpdate(sql);
			model.removeNodeFromParent(node);
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
