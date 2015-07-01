package course.choose.panel;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private Image backgroundImage;
	
	public ImagePanel(String str) {
		// ��ϵͳ�м���ͼƬ
		backgroundImage = new ImageIcon(getClass().getResource(str)).getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}
}


