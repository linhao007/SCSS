package course.choose.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import course.choose.dao.Dao;
import course.choose.dialog.SDialog;
import course.choose.login.LogRecord;
import course.choose.login.PassWord;
import course.choose.panel.About;
import course.choose.panel.CheckAllstudentPanel;
import course.choose.panel.ImagePanel;
import course.choose.panel.ModifyPanel;
import course.choose.panel.QueryPanel;
import course.choose.panel.SelectCourse;
import course.choose.panel.StudentChoosePanel;
import course.choose.panel.StudentInfo;
import course.choose.tree.ChooseCourseStudentTree;
import course.choose.tree.StudentLeafNode;
import course.choose.tree.Stree;
import course.choose.user.Operator;
import course.choose.user.Teacher;



public class TeacherFrame extends JFrame implements ActionListener {
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu_Start = null;
	private JMenu jMenu_CheckStudent = null;
	private JMenu jMenu_Help = null;
	private JMenuItem jMenuItem_ReLogin = null;
	private JMenuItem jMenuItem_Change = null;
	private JMenuItem jMenuItem_Exit = null;
	private JMenuItem jMenuItem_CheckInformation = null;
	private JMenuItem jMenuItem_CheckAllInformation = null;	
	private JMenuItem jMenuItem_About = null;
	private JPanel commonPanel;
	Teacher t;

	public TeacherFrame(Teacher t) {
		super("ѧ��ѡ��ϵͳ    "+" ��ӭ"+t.getName()+"��ʦ����ϵͳ��");
		this.t = t;
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
	
	
	public void init(){
		jMenuItem_ReLogin = new JMenuItem();
		jMenuItem_ReLogin.setText("���µ�¼");
		jMenuItem_Change = new JMenuItem();
		jMenuItem_Change.setText("�޸�����");
		jMenuItem_Exit = new JMenuItem();
		jMenuItem_Exit.setText("�˳�");
		jMenuItem_CheckInformation = new JMenuItem();
		jMenuItem_CheckInformation.setText("��ѯѧ����Ϣ");
		jMenuItem_CheckAllInformation = new JMenuItem();
		jMenuItem_CheckAllInformation.setText("ѡ��ѧ������");
		jMenuItem_About = new JMenuItem();
		jMenuItem_About.setText("����");
		
		jMenu_Start = new JMenu();
		jMenu_Start.setText("��ʼ�˵�");
		jMenu_Start.add(jMenuItem_Change);
		jMenu_Start.add(jMenuItem_ReLogin);
		jMenu_Start.addSeparator(); // �ָ���
		jMenu_Start.add(jMenuItem_Exit);
		jMenu_CheckStudent = new JMenu();
		jMenu_CheckStudent.setText("ѧ����ѯ");
		jMenu_CheckStudent.add(jMenuItem_CheckInformation);
		jMenu_CheckStudent.add(jMenuItem_CheckAllInformation);
		jMenu_Help = new JMenu();
		jMenu_Help.setText("����");
		jMenu_Help.add(jMenuItem_About);
		jJMenuBar = new JMenuBar();
		jJMenuBar.setPreferredSize(new Dimension(10, 30));
		
		jJMenuBar.add(jMenu_Start);
		jJMenuBar.add(jMenu_CheckStudent);
		jJMenuBar.add(jMenu_Help);
		setJMenuBar(jJMenuBar);
	}
	
	//ע��˵������
			private void listener() {
				jMenuItem_ReLogin.addActionListener(this);
				jMenuItem_Change.addActionListener(this);
				jMenuItem_Exit.addActionListener(this);
				jMenuItem_CheckInformation.addActionListener(this);
				jMenuItem_CheckAllInformation.addActionListener(this);
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
			ModifyPanel modify = new ModifyPanel(t);
			commonPanel.add(modify);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if(e.getSource() == jMenuItem_Exit) {
			lo.addLog(PassWord.userNames + "   �˳���ϵͳ��");
			System.exit(0);
		}else if (e.getSource() == jMenuItem_CheckInformation) {
			lo.addLog(PassWord.userNames + "    �����ѯѧ����Ϣ��");
			commonPanel.removeAll();
			QueryPanel qry = new QueryPanel(t);
			commonPanel.add(qry);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if (e.getSource() == jMenuItem_CheckAllInformation) {			
			lo.addLog(PassWord.userNames + "    ���ѡ��ѧ��������Ϣ��");
			commonPanel.removeAll();
			ChooseCourseStudentTree cct = new ChooseCourseStudentTree(t);
			cct.jp.setBounds(5, 0, 475, 370);
			commonPanel.add(cct.jp);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if(e.getSource() == jMenuItem_About) {
			lo.addLog(PassWord.userNames + "   �������ģ�飡");
			About ab = new About();
			ab.setVisible(true);
		}

	}
}

