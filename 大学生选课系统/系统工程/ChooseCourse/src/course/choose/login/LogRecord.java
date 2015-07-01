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

/*�����ж���Ĳ�����¼��־
 * 
 */
 
public class LogRecord {
	FileOutputStream fos = null;
	//FileInputStream fin = null;
	BufferedReader bufread = null;
	BufferedWriter bufwrite = null;
	public void addLog(String s) {
		try {
			FileWriter wr = new FileWriter(new File("src/course/choose/log.dat"),true);//���ֽ�д���ļ�ĩβ��
			bufwrite = new BufferedWriter(wr);//��Ӵ�������ʹ��write����
			Date date = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			String format = df.format(date).toString();//ʱ������
			bufwrite.write(s);//��¼�û�
			bufwrite.write(format);//��¼ʱ��
			bufwrite.newLine();//����
			bufwrite.flush();//ˢ��
			wr.close();
			bufwrite.close();
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "�����־����");
		}
	}
	/*�����־
	 * 
	 */
	public void deleteLog() {
		try {
			FileOutputStream fos = new FileOutputStream("src/course/choose/log.dat",false);//���Ǽ���
			fos.close();
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "�����־����");
		}
	}
	/*��ȡ��־
	 * 
	 */
	public String readLog() {
		String  str = "���м�¼�Ĳ�����־"+"\n";
		try {
			FileReader fr = new FileReader(new File("src/course/choose/log.dat"));
			bufread = new BufferedReader(fr);//���BufferedReader��ʹ��readLine����
			String line = bufread.readLine();
			while((line != null)) {
				str = str + line +"\n";
				line = bufread.readLine();//��ȡһ��
			}
			fr.close();
			bufread.close();
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "��ȡ��־����");
		}
		return str;
		
	}
}
