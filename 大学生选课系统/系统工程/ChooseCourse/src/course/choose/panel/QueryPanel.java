package course.choose.panel;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import course.choose.dao.Dao;
import course.choose.dialog.SDialog;
import course.choose.frame.Administrator;
import course.choose.tree.Stree;
import course.choose.user.Operator;
import course.choose.user.Person;
import course.choose.user.Teacher;

public class QueryPanel extends JPanel implements ActionListener {
	JLabel label_sNo;
	JLabel label_sName;
	JLabel label_sSex;
	JLabel label_sSpeClass;
	JLabel label_spassWord;
	private JLabel jLabel;
	private JRadioButton radiono;
	private JRadioButton radioname;
	private ButtonGroup radiogroup;
	private JTextField text;
	private JButton jButton_check;
	private JPanel jpanel;
	private ResultSet rs;
	private Person person;

	public QueryPanel(Person person) {
		this.person = person;
		jLabel = new JLabel("��ѯ��ʽ��");
		jLabel.setBounds(20, 20, 80, 25);
		radiono = new JRadioButton("ѧ��", true);
		radiono.setBounds(90, 20, 50, 22);
		radioname = new JRadioButton("����", false);
		radioname.setBounds(150, 20, 50, 22);
		radiogroup = new ButtonGroup();
		radiogroup.add(radiono);
		radiogroup.add(radioname);
		radiono.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("");
			}
		});
		radioname.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("");
			}
		});
		text = new JTextField();
		text.setBounds(210, 22, 100, 20);
		jButton_check = new JButton("��ѯ");
		jButton_check.setBounds(325, 20, 70, 22);

		jpanel = new JPanel();
		jpanel.setBounds(5, 60, 480, 315);
		jpanel.setBorder(BorderFactory.createTitledBorder("��ѯ���"));
		jpanel.setVisible(false);

		label_sNo = new JLabel("ѧ��ѧ��:");
		label_sNo.setBounds(40, 10, 80, 40);
		label_sName = new JLabel("ѧ������:");
		label_sName.setBounds(40, 40, 80, 40);
		label_sSex = new JLabel("ѧ���Ա�:");
		label_sSex.setBounds(40, 70, 80, 40);
		label_sSpeClass = new JLabel("רҵ�༶:");
		label_sSpeClass.setBounds(40, 100, 80, 40);
		label_spassWord = new JLabel("ѧ������:");
		label_spassWord.setBounds(40, 130, 80, 40);

		setLayout(null);
		add(jLabel);
		add(radiono);
		add(radioname);
		add(text);
		add(jButton_check);
		add(jpanel);
		jButton_check.addActionListener(this);
		setBorder(BorderFactory.createTitledBorder("ѧ����ѯ"));
		setSize(490, 380);
	}

	public void Infotable(String sql) {
		DefaultTableModel tableModel = new DefaultTableModel();
		String[] tableHeads = { "ѧ��", "����", "�Ա�", "רҵ�༶", "ѡ�޿γ�" };
		Vector cell;
		Vector row = new Vector();
		Vector tableHeadName = new Vector();
		for (int i = 0; i < tableHeads.length; i++) {
			tableHeadName.add(tableHeads[i]);
		}

		try {
			rs = Dao.executeQuery(sql);
			try {
				if (!rs.next()) {
					JOptionPane.showMessageDialog(null, "û�и�ѧ����");
					text.setText("");
					return;
				} else {
					cell = new Vector();
					cell.add(rs.getString("sno"));
					cell.add(rs.getString("sname"));
					cell.add(rs.getString("ssex"));
					cell.add(rs.getString("sclassno"));
					if (rs.getString("chooseclassname") == null) {
						cell.add("δѡ��");
					} else {
						cell.add(rs.getString("chooseclassname"));
					}
					row.add(cell);
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "���ҳ���");
			}
			while (rs.next()) {
				cell = new Vector();
				cell.add(rs.getString("sno"));
				cell.add(rs.getString("sname"));
				cell.add(rs.getString("ssex"));
				cell.add(rs.getString("sclassno"));
				if (rs.getString("chooseclassname") == null) {
					cell.add("δѡ��");
				} else {
					cell.add(rs.getString("chooseclassname"));
				}

				row.add(cell);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "����������");
		}
		tableModel.setDataVector(row, tableHeadName);
		Mytable table = new Mytable(tableModel);
		table.setRowHeight(20);
		table.setCursor(new Cursor(12));
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 50, 350, 100);
		scrollPane.setCursor(new Cursor(12));
		jpanel.add(scrollPane);
		jpanel.setVisible(true);
	}

	public void Infotable1(String sql) {
		DefaultTableModel tableModel = new DefaultTableModel();
		String[] tableHeads = { "ѧ��", "����", "�Ա�", "רҵ�༶", "ѡ�޿γ̰༶" };
		Vector cell;
		Vector row = new Vector();
		Vector tableHeadName = new Vector();
		for (int i = 0; i < tableHeads.length; i++) {
			tableHeadName.add(tableHeads[i]);
		}

		try {
			rs = Dao.executeQuery(sql);
			try {
				if (!rs.next()) {
					JOptionPane.showMessageDialog(null, "û�и�ѧ����");
					text.setText("");
					return;
				} else {
					cell = new Vector();
					cell.add(rs.getString("sno"));
					cell.add(rs.getString("sname"));
					cell.add(rs.getString("ssex"));
					cell.add(rs.getString("sclassno"));
					if (rs.getString("chooseclassno") == null) {
						cell.add("δѡ��");
					} else {
						cell.add(rs.getString("chooseclassno"));
					}
					row.add(cell);
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "���ҳ���");
			}
			while (rs.next()) {
				cell = new Vector();
				cell.add(rs.getString("sno"));
				cell.add(rs.getString("sname"));
				cell.add(rs.getString("ssex"));
				cell.add(rs.getString("sclassno"));
				if (rs.getString("chooseclassno") == null) {
					cell.add("δѡ��");
				} else {
					cell.add(rs.getString("chooseclassno"));
				}

				row.add(cell);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "����������");
		}
		tableModel.setDataVector(row, tableHeadName);
		Mytable table = new Mytable(tableModel);
		table.setRowHeight(20);
		table.setCursor(new Cursor(12));
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 50, 350, 100);
		scrollPane.setCursor(new Cursor(12));
		jpanel.add(scrollPane);
		jpanel.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jButton_check) {
			jpanel.removeAll();
			jpanel.setVisible(false);
			if (radiono.isSelected()) {
				String Sname = null;
				String sex = null;
				int SClassNo = -1;
				String Sno = text.getText();
				boolean flag1 = Sno.trim().equals(""); // �ж������ѧ��ǰ���հ׺�β���հ�
				boolean flag2 = false; // �ж������ѧ���м��Ƿ��пո�
				boolean flag3 = true; // �ж������ѧ���Ƿ�������
				for (int i = 0; i < Sno.length(); i++) {
					if (Sno.charAt(i) == ' ')
						flag2 = true;
				}

				for (int i = 0; i < Sno.length(); i++) {
					char[] ch = Sno.toCharArray();
					if (Character.isDigit(ch[i])) {
						flag3 = false;
					}
				}

				if (Sno.isEmpty() || flag1 || flag2 || flag3) {
					JOptionPane.showMessageDialog(null, "����ȷ����ѧ�ţ� �磺20121521");
					text.setText("");
				}else if(person instanceof Operator){
					int ino = Integer.parseInt(Sno);
					String sql = "select es.sno,es.sname,es.ssex,es.sclassno,es.chooseclassname from (select ts.sno,sname,ssex,ts.sclassno,c.chooseclassname from (select s.sno,s.sname,s.ssex,s.sclassno,sc.chooseclassno from tb_Student s left join tb_SC sc on s.sno=sc.sno)as ts left join tb_ChooseClass c on ts.chooseclassno=c.chooseclassno) es where es.sno="
							+ ino;
					Infotable(sql);
				}else if(person instanceof Teacher){
					Teacher tea = (Teacher) person;
					String s = tea.getNo();
					int ino = Integer.parseInt(Sno);
					String sql1 = "select s.SNo,SName,ssex,sclassno,chooseclassno from tb_SC sc,tb_Student s,tb_Course c where TNo = '"
							+ s + "'and c.cno =sc.cno and sc.SNo=s.SNo and s.sno="+ino;
					Infotable1(sql1);
				}

			} else if (radioname.isSelected()) {
				int sno = -1;
				String sex = null;
				int SClassNo = -1;
				String Sname = text.getText();
				boolean flag1 = Sname.trim().equals(""); // �ж����������ǰ���հ׺�β���հ�
				boolean flag2 = false; // �ж�����������м��Ƿ��пո�
				for (int i = 0; i < Sname.length(); i++) {
					if (Sname.charAt(i) == ' ')
						flag2 = true;
				}
				if (Sname.isEmpty() || flag1 || flag2) {
					JOptionPane.showMessageDialog(null, "����ȷ���������� �磺����");
					text.setText("");
				}else if(person instanceof Operator) {
					String sql = "select es.sno,es.sname,es.ssex,es.sclassno,es.chooseclassname from (select ts.sno,sname,ssex,ts.sclassno,c.chooseclassname from (select s.sno,s.sname,s.ssex,s.sclassno,sc.chooseclassno from tb_Student s left join tb_SC sc on s.sno=sc.sno)as ts left join tb_ChooseClass c on ts.chooseclassno=c.chooseclassno) es where es.sname='"
							+ Sname + "'";
					Infotable(sql);
				}else if(person instanceof Teacher){
					Teacher tea = (Teacher) person;
					String s = tea.getNo();
					String sql1 = "select s.SNo,SName,ssex,sclassno,chooseclassno from tb_SC sc,tb_Student s,tb_Course c where TNo = '"
							+ s + "'and c.cno =sc.cno and sc.SNo=s.SNo and s.sname='"+Sname+"'";
					Infotable1(sql1);
				}

			}

		}
	}
	
}
class Mytable extends JTable {	
	public Mytable(DefaultTableModel tableModel) {
		// TODO Auto-generated constructor stub
		super(tableModel);
	}
	public boolean isCellEditable(int row, int column)
	{
	return false;
	}

}
