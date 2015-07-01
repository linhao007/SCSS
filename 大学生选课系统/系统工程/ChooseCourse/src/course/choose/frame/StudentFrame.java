package course.choose.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import course.choose.dialog.SDialog;
import course.choose.login.LogRecord;
import course.choose.login.PassWord;
import course.choose.panel.About;
import course.choose.panel.ImagePanel;
import course.choose.panel.ModifyPanel;
import course.choose.panel.SelectCourse;
import course.choose.panel.StudentChoosePanel;
import course.choose.panel.StudentInfo;
import course.choose.tree.Stree;
import course.choose.user.Operator;
import course.choose.user.Student;



public class StudentFrame extends JFrame implements ActionListener {
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu_Start = null;
	private JMenu jMenu_StudentSelectCourse= null;
	private JMenu jMenu_Check = null;
	private JMenu jMenu_Help = null;
	private JMenuItem jMenuItem_ReLogin = null;
	private JMenuItem jMenuItem_Change = null;
	private JMenuItem jMenuItem_Exit = null;
	private JMenuItem jMenuItem_SelectCourse = null;
	private JMenuItem jMenuItem_CheckInformation = null;
	private JMenuItem jMenuItem_CheckSelectCourseInformation = null;	
	private JMenuItem jMenuItem_About = null;
	
	JPanel commonPanel;
	public Student s;

	public StudentFrame(Student s) {
		super("ѧ��ѡ��ϵͳ    "+" ��ӭ"+s.getName()+"ͬѧ����ϵͳ��");
		this.s = s;
		commonPanel = new JPanel();
		commonPanel.setLayout(null);	
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(500, 450);
		setLocationRelativeTo(null);
		ImageIcon titleIcon = new ImageIcon(getClass().getResource(
				"/image/1.jpg"));
		setIconImage(titleIcon.getImage());
		setLayout(new BorderLayout());
		ImagePanel jDialogImage = new ImagePanel("/image/3.jpg");
		jDialogImage.setBounds(0, 0, 500, 450);
		commonPanel.add(jDialogImage);
		add(commonPanel, BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);
		init();
		listener();
	}
	
	private void init() {	
		jMenuItem_ReLogin = new JMenuItem();
		jMenuItem_ReLogin.setText("���µ�¼");
		jMenuItem_Change = new JMenuItem();
		jMenuItem_Change.setText("�޸�����");
		jMenuItem_Exit = new JMenuItem();
		jMenuItem_Exit.setText("�˳�");
		jMenuItem_SelectCourse = new JMenuItem();
		jMenuItem_SelectCourse.setText("ѡ��γ�");
		jMenuItem_CheckInformation = new JMenuItem();
		jMenuItem_CheckInformation.setText("�鿴������Ϣ");
		jMenuItem_CheckSelectCourseInformation = new JMenuItem();
		jMenuItem_CheckSelectCourseInformation.setText("�鿴ѡ����Ϣ");
		jMenuItem_About = new JMenuItem();
		jMenuItem_About.setText("����");
		
		jMenu_Start = new JMenu();
		jMenu_Start.setText("��ʼ�˵�");
		jMenu_Start.add(jMenuItem_Change);
		jMenu_Start.add(jMenuItem_ReLogin);
		jMenu_Start.addSeparator(); // �ָ���
		jMenu_Start.add(jMenuItem_Exit);
		jMenu_StudentSelectCourse = new JMenu();
		jMenu_StudentSelectCourse.setText("ѧ��ѡ��");
		jMenu_StudentSelectCourse.add(jMenuItem_SelectCourse);
		jMenu_Check = new JMenu();
		jMenu_Check.setText("��ѯ");
		jMenu_Check.add(jMenuItem_CheckInformation);
		jMenu_Check.add(jMenuItem_CheckSelectCourseInformation);
		jMenu_Help = new JMenu();
		jMenu_Help.setText("����");
		jMenu_Help.add(jMenuItem_About);
		jJMenuBar = new JMenuBar();
		jJMenuBar.setPreferredSize(new Dimension(10, 30));
		
		jJMenuBar.add(jMenu_Start);
		jJMenuBar.add(jMenu_StudentSelectCourse);
		jJMenuBar.add(jMenu_Check);
		jJMenuBar.add(jMenu_Help);
		setJMenuBar(jJMenuBar);

	}
	
	//ע��˵������
		private void listener() {
			jMenuItem_ReLogin.addActionListener(this);
			jMenuItem_Change.addActionListener(this);
			jMenuItem_Exit.addActionListener(this);
			jMenuItem_SelectCourse.addActionListener(this);
			jMenuItem_CheckInformation.addActionListener(this);
			jMenuItem_CheckSelectCourseInformation.addActionListener(this);
			jMenuItem_About.addActionListener(this);
		}

	public void actionPerformed(ActionEvent e) {
		LogRecord lo = new LogRecord();
		if (e.getSource() == jMenuItem_ReLogin) {
			lo.addLog(PassWord.userNames+ "   ִ�����µ�¼������");
			dispose();
			new PassWord();
		}else if(e.getSource() == jMenuItem_Change) {
			lo.addLog(PassWord.userNames + "   ����޸�����ģ�飡");
			commonPanel.removeAll();
			ModifyPanel modify = new ModifyPanel(s);
			commonPanel.add(modify);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if(e.getSource() == jMenuItem_Exit) {
			lo.addLog(PassWord.userNames + "   �˳���ϵͳ��");
			System.exit(0);
		}else if (e.getSource() == jMenuItem_SelectCourse) {
			lo.addLog(PassWord.userNames + "   ѡ��γ̣�");
			commonPanel.removeAll();
			SelectCourse sel = new SelectCourse(s);
			commonPanel.add(sel);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if (e.getSource() == jMenuItem_CheckInformation) {			
			lo.addLog(PassWord.userNames + "   �鿴������Ϣ��");
			commonPanel.removeAll();
			StudentInfo stupanel = new StudentInfo(s);
			commonPanel.add(stupanel);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if(e.getSource() == jMenuItem_CheckSelectCourseInformation) {
			lo.addLog(PassWord.userNames + "   �鿴ѡ����Ϣ��");
			commonPanel.removeAll();
			StudentChoosePanel stuchoosepanel = new StudentChoosePanel(s);
			commonPanel.add(stuchoosepanel);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if(e.getSource() == jMenuItem_About){
			lo.addLog(PassWord.userNames + "   �������ģ�飡");
			About ab = new About();
			ab.setVisible(true);
		}
	}
}
