package course.choose.panel;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import course.choose.dao.Dao;

public class CheckAllstudentPanel extends JPanel implements ActionListener {
	private JPanel jpanel;
	private ResultSet rs;
	
	public CheckAllstudentPanel(){
		
		setLayout(null);
		setBorder(BorderFactory.createTitledBorder("学生总览"));
		setSize(490, 380);
		String sql = "select es.sno,es.sname,es.ssex,es.sclassno,es.chooseclassname from (select ts.sno,sname,ssex,ts.sclassno,c.chooseclassname from (select s.sno,s.sname,s.ssex,s.sclassno,sc.chooseclassno from tb_Student s left join tb_SC sc on s.sno=sc.sno)as ts left join tb_ChooseClass c on ts.chooseclassno=c.chooseclassno) es";
		Infotable(sql);
	}
	
	public void Infotable(String sql) {
		DefaultTableModel tableModel = new DefaultTableModel();
		String[] tableHeads = { "学号", "姓名", "性别", "专业班级", "选修课程" };
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
					JOptionPane.showMessageDialog(null, "没有该学生！");
					return;
				} else {
					cell = new Vector();
					cell.add(rs.getString("sno"));
					cell.add(rs.getString("sname"));
					cell.add(rs.getString("ssex"));
					cell.add(rs.getString("sclassno"));
					if (rs.getString("chooseclassname") == null) {
						cell.add("未选课");
					} else {
						cell.add(rs.getString("chooseclassname"));
					}
					row.add(cell);
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "查找出错！");
			}
			while (rs.next()) {
				cell = new Vector();
				cell.add(rs.getString("sno"));
				cell.add(rs.getString("sname"));
				cell.add(rs.getString("ssex"));
				cell.add(rs.getString("sclassno"));
				if (rs.getString("chooseclassname") == null) {
					cell.add("未选课");
				} else {
					cell.add(rs.getString("chooseclassname"));
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
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 20, 470, 350);
		scrollPane.setCursor(new Cursor(12));
		this.add(scrollPane);
		this.setVisible(true);
			
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
