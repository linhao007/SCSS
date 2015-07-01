package course.choose.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import course.choose.user.Operator;
import course.choose.user.Person;
import course.choose.user.Student;
import course.choose.user.Teacher;


public class Dao {
	protected static Connection cnt = null;
	protected static Statement smt;
	protected static ResultSet rs;
	protected static String dbClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

/*�������ݿ�
 * 
 */
	protected Dao() {
		if (cnt == null) {
			try {
				Class.forName(dbClassName);
				cnt = DriverManager
						.getConnection(
								"jdbc:sqlserver://localhost:1433;DataBaseName=db_course",
								"sa", "12");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException s) {
				System.out.print("����");
				s.printStackTrace();
			}
		} else
			return;
	}
/*��ѯ���
 * 
 */
	public static ResultSet executeQuery(String sql) {
		try {
			if (cnt == null)
				new Dao();
			return (cnt.createStatement().executeQuery(sql));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {

		}
	}
/*�������
 * 
 */
	public static int executeUpdate(String sql) {
		try {
			if (cnt == null)
				new Dao();
			return cnt.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {

		}
	}
/*�Ͽ����ݿ����ӣ��ر��ļ�
 * 
 */
	public static void close() {
		try {
			cnt.close();
			smt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cnt = null;
			smt = null;
			rs = null;
		}
	}
/*����¼��Ա���Ƿ������ݿ����������
 * 
 */
	public static Person check(String allname, String allpassword) {
		if (cnt == null) {
			new Dao();
		}
		try {
			smt = cnt.createStatement();
		} catch (SQLException f) {
			// System.out.println("����");
			f.printStackTrace();
		}
/*ͨ���ж��û����ĳ��ȣ����ж��û��Ļ�����ݡ�Ȼ�����Ƿ���������Ա
 * 
 */
		if (allname.length() == 5) {
			Operator opt = new Operator();
			try {
				rs = smt.executeQuery("select * from tb_Manager where ManagerNo='"
						+ allname + "' and PassWord='" + allpassword + "'");
				while (rs.next()) {
					String names = rs.getString("ManagerName");
					opt.setNo(rs.getString("ManagerNo"));
					opt.setName(names);
					opt.setPassword(rs.getString("PassWord"));
				}
			} catch (SQLException h) {
				h.printStackTrace();

			}
			Dao.close();
			return opt;
		} else if (allname.length() == 8) {
			Student stu = new Student();
			try {

				rs = smt.executeQuery("select * from tb_Student where SNo='"
						+ allname + "' and PassWord='" + allpassword+"'");

				while (rs.next()) {
					String No = rs.getString("SNo");
					String names = rs.getString("SName");
					String sex = rs.getString("SSex");
					String pwd = rs.getString("PassWord");
					int classno = rs.getInt("SClassNo");
					stu.setNo(No);
					stu.setName(names);
					stu.setSex(sex);
					stu.setPassword(pwd);
					stu.setSpeclassNO(classno);

				}
			} catch (SQLException h) {
				h.printStackTrace();
			}
			Dao.close();
			return stu;
		} else if (allname.length() == 4) {
			Teacher tea = new Teacher();
			try {

				rs = smt.executeQuery("select * from tb_Teacher where TNo='"
						+ allname + "' and PassWord='" + allpassword+"'");
				while (rs.next()) {
					String No = rs.getString("TNo");
					String names = rs.getString("TName");
					String sex = rs.getString("TSex");
					String pwd = rs.getString("PassWord");
					tea.setNo(No);
					tea.setName(names);
					tea.setSex(sex);
					tea.setPassword(pwd);
				}
			} catch (SQLException h) {
				h.printStackTrace();
			}
			Dao.close();
			return tea;
		} else
			return null;

	}
}