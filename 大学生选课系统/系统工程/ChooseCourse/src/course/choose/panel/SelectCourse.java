package course.choose.panel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import course.choose.dao.Dao;
import course.choose.user.Student;

/** 
 * TableDemo is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */
public class SelectCourse extends JPanel implements TableModelListener,ActionListener{
    private boolean DEBUG = false;
    ResultSet rs;
    ResultSet rst;
    ResultSet rsts;
    ResultSet result;
    Student stu;
    int count = 0;
    int i = 0;
    int s = 0;
    int temp = 0;
    int counts[];
    private Object[][] data;
    int tempscno[] = new int[50];
  
    JPopupMenu popupMenu;
    JScrollPane scrollPane;
    JButton commit;
    JButton bn_Canel;
    public SelectCourse(Student stu) {    	
        this.stu = stu;      
        setLayout(null);
        commit = new JButton("提交");
        bn_Canel = new JButton("取消");
        commit.addActionListener(this);
        bn_Canel.addActionListener(this);
        JTable table = new JTable(new MyTableModel());
        table.getTableHeader().setReorderingAllowed(false);
        table.getModel().addTableModelListener(this);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        this.setOpaque(true);       
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 10, 470, 300);
        add(scrollPane);
        commit.setBounds(110, 330, 60, 20);
        bn_Canel.setBounds(230, 330, 60, 20);
        add(commit);
        add(bn_Canel);
        this.setSize(600, 600);
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"课程号","课程名","选修课班级","老师","选中"};
       
        
      public MyTableModel() {
        	rs = Dao.executeQuery("select a.CNo,CName,ChooseClassNo,TName from tb_Course a,tb_Teacher b,tb_ChooseClass c where a.TNo=b.TNo and a.cname=c.chooseclassname order by CName");
        	rst = Dao.executeQuery("select count(*) 'count' from tb_ChooseClass");
        	try {
        		while(rst.next())
				i = rst.getInt("count");
        		
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	counts = new int[50];
        	for(int sj=0;sj<i;sj++)
        		counts[sj] = -1;
        	data = new Object [i][5];
        	int j = 0;
        	try {
        		while(rs.next()) {
					data[j][0] = (rs.getInt("CNo"));
					data[j][1] = (rs.getString("CName"));
					data[j][2] = (rs.getInt("ChooseClassNo"));
					data[j][3] = (rs.getString("TName"));
					data[j][4] = (new Boolean(false));
					j++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        public boolean isCellEditable(int row, int column)
    	{	if(column == 4)
        	return true;
    		else 
    		return false;
    	}
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
               // System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                //System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                   // System.out.print("  " + data[i][j]);
                }
               // System.out.println();
            }
            //System.out.println("--------------------------");
        }
    }

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
		int row = e.getFirstRow();
		int column = e.getColumn();
		MyTableModel model = (MyTableModel)e.getSource();
		String colunmnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);
		if(((Boolean) data).booleanValue()==true) {
			count++;			
			counts[s++] = row;				
			
		}
		if(((Boolean) data).booleanValue()==false) {
			count--;
			for(int t = 0;t<s;t++) {
				if(counts[t] == row)
				counts[t] = -1;
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==commit) {
			if(count > 3) {
				JOptionPane.showMessageDialog(null, "所选课门数不得超过三门");
			}
				String No = stu.getNo();
				String sql = "select count(cno) 'tempcount' from tb_SC where sno='"+No+"'";
				rsts = Dao.executeQuery(sql);
				String sq12 = "select cno 'tempcno' from tb_SC where sno='"+No+"'";
				result = Dao.executeQuery(sq12);
				
				
				try {
					while(result.next()) {
						tempscno[temp++] = result.getInt("tempcno");
						//System.out.println(tempscno[temp-1]);
					}
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "出错");
				}
				int tempsum = 0;				
				try {	
					while(rsts.next()) {
						tempsum = rsts.getInt("tempcount");
						//System.out.println(tempsum);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "查询学生所选选修课门数出错");
				
				}
			if(count+tempsum > 3) {
					JOptionPane.showMessageDialog(null, "您已经选了"+tempsum+"门，不得超过三门");
				}else {
					for(int st = 0;st<s;st++) {
						if(counts[st] != -1) {
							int row = counts[st];
							int tempcno = (int) data[row][0];							
							int tempclassno = (int) data[row][2];
							for(int scoutn=0;scoutn<temp;scoutn++) {
								if(tempcno == tempscno[scoutn]) {
									JOptionPane.showMessageDialog(null, "您选了重复课!");
									return;
								}
							}
							String sql1 = "insert into tb_SC values('"+No+"',"+tempclassno+","+tempcno+")";
							Dao.executeUpdate(sql1);
							JOptionPane.showMessageDialog(null, "选课成功");
							this.setVisible(false);
						}
					}
			  }
		}else if(e.getSource() == bn_Canel) {
			this.setVisible(false);
		}
	}
}
    
