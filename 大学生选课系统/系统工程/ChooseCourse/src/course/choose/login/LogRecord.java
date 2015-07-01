package course.choose.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.text.DateFormat;

import javax.swing.JOptionPane;

/*对所有对象的操作记录日志
 * 
 */
 
public class LogRecord {
	FileOutputStream fos = null;
	//FileInputStream fin = null;
	BufferedReader bufread = null;
	BufferedWriter bufwrite = null;
	public void addLog(String s) {
		try {
			FileWriter wr = new FileWriter(new File("src/course/choose/log.dat"),true);//将字节写入文件末尾处
			bufwrite = new BufferedWriter(wr);//添加处理流，使用write方法
			Date date = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			String format = df.format(date).toString();//时间日期
			bufwrite.write(s);//记录用户
			bufwrite.write(format);//记录时间
			bufwrite.newLine();//换行
			bufwrite.flush();//刷新
			wr.close();
			bufwrite.close();
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "添加日志出错");
		}
	}
	/*清空日志
	 * 
	 */
	public void deleteLog() {
		try {
			FileOutputStream fos = new FileOutputStream("src/course/choose/log.dat",false);//覆盖即可
			fos.close();
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "清空日志出错");
		}
	}
	/*读取日志
	 * 
	 */
	public String readLog() {
		String  str = "所有记录的操作日志"+"\n";
		try {
			FileReader fr = new FileReader(new File("src/course/choose/log.dat"));
			bufread = new BufferedReader(fr);//添加BufferedReader，使用readLine方法
			String line = bufread.readLine();
			while((line != null)) {
				str = str + line +"\n";
				line = bufread.readLine();//读取一行
			}
			fr.close();
			bufread.close();
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "读取日志出错");
		}
		return str;
		
	}
}
