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

/*连接数据库
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
				System.out.print("出错");
				s.printStackTrace();
			}
		} else
			return;
	}
/*查询语句
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
/*更新语句
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
/*断开数据库连接，关闭文件
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
/*检查登录人员，是否是数据库中允许的人
 * 
 */
	public static Person check(String allname, String allpassword) {
		if (cnt == null) {
			new Dao();
		}
		try {
			smt = cnt.createStatement();
		} catch (SQLException f) {
			// System.out.println("出错");
			f.printStackTrace();
		}
/*通过判断用户名的长度，来判断用户的基本身份。然后检查是否是允许人员
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