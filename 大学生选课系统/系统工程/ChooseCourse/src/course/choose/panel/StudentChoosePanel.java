package course.choose.panel;

import java.awt.Cursor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import course.choose.dao.Dao;
import course.choose.user.Student;

public class StudentChoosePanel extends JPanel{ 
	ResultSet rs;
	JScrollPane scrollPane;
	public StudentChoosePanel(Student stu) {
		String sql = "select es.sno,es.sname,es.ssex,es.sclassno,es.chooseclassname,es.chooseclassno from (select ts.sno,sname,ssex,ts.sclassno,c.chooseclassname,ts.chooseclassno from (select s.sno,s.sname,s.ssex,s.sclassno,sc.chooseclassno from tb_Student s left join tb_SC sc on s.sno=sc.sno)as ts left join tb_ChooseClass c on ts.chooseclassno=c.chooseclassno) es where es.sno="
				+ stu.getNo();
		Infotable(sql);
		this.setLayout(null);
		this.add(scrollPane);
		this.setSize(480,400);
		
		
	}
	public void Infotable(String sql) {
		DefaultTableModel tableModel = new DefaultTableModel();
		String[] tableHeads = { "学号", "选修课程","选修课班级","专业班级" };
		Vector cell;
		Vector row = new Vector();
		Vector tableHeadName = new Vector();
		for (int i = 0; i < tableHeads.length; i++) {
			tableHeadName.add(tableHeads[i]);
		}

		try {
			rs = Dao.executeQuery(sql);
			while (rs.next()) {
				cell = new Vector();
				if (rs.getString("chooseclassname") == null) {
					cell.add("未选课");
				} else {
					cell.add(rs.getString("sno"));
					cell.add(rs.getString("chooseclassname"));
					cell.add(rs.getString("chooseclassno"));
					cell.add(rs.getString("sclassno"));
				}
				row.add(cell);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "创建表格出错");
		}
		tableModel.setDataVector(row, tableHeadName);
		Mytable table = new Mytable(tableModel);
		table.setRowHeight(20);
		table.setCursor(new Cursor(12));
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 460, 350);
		scrollPane.setCursor(new Cursor(12));
		
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

}
