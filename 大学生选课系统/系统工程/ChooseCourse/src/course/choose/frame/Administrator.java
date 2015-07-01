package course.choose.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.WindowConstants;

import course.choose.login.LogRecord;
import course.choose.login.PassWord;
import course.choose.panel.About;
import course.choose.panel.CheckAllstudentPanel;
import course.choose.panel.CourseManagePanel;
import course.choose.panel.ImagePanel;
import course.choose.panel.LogPanel;
import course.choose.panel.ModifyPanel;
import course.choose.panel.QueryPanel;
import course.choose.tree.Ctree;
import course.choose.tree.Stree;
import course.choose.tree.Ttree;
import course.choose.user.Operator;


public class Administrator extends JFrame implements ActionListener {
	Stree sd;
	Ctree cd;
	Ttree td;
	JPanel menuPanel;
	JPanel commonPanel;
	private JMenu jMenu_Start = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu_Manage = null;
	private JMenu jMenu_Check = null;
	private JMenu jMenu_Help = null;
	private JMenuItem jMenuItem_ReLogin = null;
	private JMenuItem jMenuItem_Change = null;
	private JMenuItem jMenuItem_Exit = null;
	private JMenuItem jMenuItem_StudentClass = null;
	private JMenuItem jMenuItem_ChooseCourseClass = null;
	private JMenuItem jMenuItem_Teacher = null;	
	private JMenuItem jMenuItem_Course = null;
	private JMenuItem jMenuItem_StudentInformation = null;
	private JMenuItem jMenuItem_CheckAll = null;
	private JMenuItem jMenuItem_About = null;
	public JMenuItem jMenuItem_CheckLog = null;
	Operator op;
	public Administrator(Operator op) {
		super("学生选课系统    "+" 欢迎"+op.getName()+"管理员进入系统！");
		this.op = op;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
		setSize(500, 450);
		setLocationRelativeTo(null);
		ImageIcon titleIcon = new ImageIcon(getClass().getResource(
				"/image/1.jpg"));
		setIconImage(titleIcon.getImage());//获取图片
		setResizable(false);
		setVisible(true);
		menuPanel = new JPanel();
		commonPanel = new JPanel();
		commonPanel.setLayout(null);
		ImagePanel jDialogImage = new ImagePanel("/image/3.jpg");
		jDialogImage.setBounds(0, 0, 500, 450);
		commonPanel.add(jDialogImage);
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		setLayout(new BorderLayout());
		add(commonPanel, BorderLayout.CENTER);
		add(menuPanel, BorderLayout.NORTH);
		initialize();
		listener();
	}
/*初始化
 * 
 */
	private void initialize() {	
		jMenuItem_ReLogin = new JMenuItem();
		jMenuItem_ReLogin.setText("重新登录");
		jMenuItem_CheckLog = new JMenuItem();
		jMenuItem_CheckLog.setText("操作日志");
		jMenuItem_Change = new JMenuItem();
		jMenuItem_Change.setText("修改密码");
		jMenuItem_Exit = new JMenuItem();
		jMenuItem_Exit.setText("退出");
		jMenuItem_StudentClass = new JMenuItem();
		jMenuItem_StudentClass.setText("学生班级管理");
		jMenuItem_ChooseCourseClass = new JMenuItem();
		jMenuItem_ChooseCourseClass.setText("选修课班级管理");
		jMenuItem_Teacher = new JMenuItem();
		jMenuItem_Teacher.setText("教师管理");
		jMenuItem_Course = new JMenuItem();
		jMenuItem_Course.setText("课程管理");
		jMenuItem_StudentInformation = new JMenuItem();
		jMenuItem_StudentInformation.setText("学生查询");
		jMenuItem_CheckAll = new JMenuItem();
		jMenuItem_CheckAll.setText("学生总览");
		jMenuItem_About = new JMenuItem();
		jMenuItem_About.setText("关于");
		
		jMenu_Start = new JMenu();
		jMenu_Start.setText("开始菜单");
		jMenu_Start.add(jMenuItem_Change);
		jMenu_Start.add(jMenuItem_CheckLog);
		jMenu_Start.add(jMenuItem_ReLogin);
		jMenu_Start.addSeparator(); // 分割线
		jMenu_Start.add(jMenuItem_Exit);
		jMenu_Manage = new JMenu();
		jMenu_Manage.setText("管理");
		jMenu_Manage.add(jMenuItem_StudentClass);
		jMenu_Manage.add(jMenuItem_ChooseCourseClass);
		jMenu_Manage.add(jMenuItem_Teacher);
		jMenu_Manage.add(jMenuItem_Course);
		jMenu_Check = new JMenu();
		jMenu_Check.setText("查询");
		jMenu_Check.add(jMenuItem_StudentInformation);
		jMenu_Check.add(jMenuItem_CheckAll);
		jMenu_Help = new JMenu();
		jMenu_Help.setText("帮助");
		jMenu_Help.add(jMenuItem_About);
		jJMenuBar = new JMenuBar();
		jJMenuBar.setPreferredSize(new Dimension(10, 30));
		
		jJMenuBar.add(jMenu_Start);
		jJMenuBar.add(jMenu_Manage);
		jJMenuBar.add(jMenu_Check);
		jJMenuBar.add(jMenu_Help);
		setJMenuBar(jJMenuBar);
		
	}
	/*为菜单项添加监听
	 * 
	 */
	private void listener() {
		jMenuItem_ReLogin.addActionListener(this);
		jMenuItem_Change.addActionListener(this);
		jMenuItem_CheckLog.addActionListener(this);
		jMenuItem_Exit.addActionListener(this);
		jMenuItem_StudentClass.addActionListener(this);
		jMenuItem_ChooseCourseClass.addActionListener(this);
		jMenuItem_Teacher.addActionListener(this);
		jMenuItem_Course.addActionListener(this);
		jMenuItem_StudentInformation.addActionListener(this);
		jMenuItem_CheckAll.addActionListener(this);
		jMenuItem_About.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		LogRecord lo = new LogRecord();
		if(e.getSource() == jMenuItem_ReLogin) {
			lo.addLog(PassWord.userNames+ "   执行重新登录操作！");
			dispose();
			new PassWord();
		}else if(e.getSource() == jMenuItem_CheckLog) {
			lo.addLog(PassWord.userNames + "   浏览操作日志模块！");
			LogPanel log = new LogPanel();
			commonPanel.removeAll();
			commonPanel.add(log);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);	
		}else if(e.getSource() == jMenuItem_Change) {
			lo.addLog(PassWord.userNames + "   浏览修改密码模块！");
			commonPanel.removeAll();
			ModifyPanel modify = new ModifyPanel(op);
			commonPanel.add(modify);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);	
		}else if(e.getSource() == jMenuItem_Exit) {
			lo.addLog(PassWord.userNames + "   退出本系统！");
			System.exit(0);
		}else if(e.getSource() == jMenuItem_StudentClass) {
			lo.addLog(PassWord.userNames + "   浏览学生管理模块！");
			commonPanel.removeAll();
			sd = new Stree();
			sd.jp.setBounds(5, 0, 475, 370);
			commonPanel.add(sd.jp);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if(e.getSource() == jMenuItem_ChooseCourseClass) {
			lo.addLog(PassWord.userNames + "   浏览选课班级管理模块！");
			commonPanel.removeAll();
			cd = new Ctree(this);
			cd.jp.setBounds(5, 0, 475, 370);
			commonPanel.add(cd.jp);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if(e.getSource() == jMenuItem_Teacher) {
			lo.addLog(PassWord.userNames + "   浏览教师管理模块！");
			commonPanel.removeAll();
			td = new Ttree(this);
			td.jp.setBounds(5, 0, 475, 370);
			commonPanel.add(td.jp);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);

		}else if(e.getSource() == jMenuItem_Course) {
			lo.addLog(PassWord.userNames + "   浏览课程管理！");
			commonPanel.removeAll();
			CourseManagePanel cmp = new CourseManagePanel();
			commonPanel.add(cmp);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
			
		}else if(e.getSource() == jMenuItem_StudentInformation) {
			lo.addLog(PassWord.userNames + "   浏览学生信息表模块！");
			commonPanel.removeAll();
			QueryPanel qry = new QueryPanel(op);
			commonPanel.add(qry);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if(e.getSource() == jMenuItem_CheckAll) {
			lo.addLog(PassWord.userNames + "   浏览学生信息一览表模块！");
			commonPanel.removeAll();
			CheckAllstudentPanel cas = new CheckAllstudentPanel();
			commonPanel.add(cas);
			commonPanel.update(commonPanel.getGraphics());
			add(commonPanel, BorderLayout.CENTER);
			this.setVisible(true);
		}else if(e.getSource() == jMenuItem_About) {
			lo.addLog(PassWord.userNames + "   浏览关于模块！");
			About ab = new About();
			ab.setVisible(true);
		}
		
	}
	
}





