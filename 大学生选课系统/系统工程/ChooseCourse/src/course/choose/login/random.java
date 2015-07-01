package course.choose.login;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class random {
	private String sRand = "";

	public BufferedImage creatImage() {
		// ���ڴ��д���ͼ��
		int width = 48, height = 18;
		int fontSize = 18;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// ��ȡͼ��������
		Graphics g = image.getGraphics();

		// ���������
		Random random = new Random();

		// �趨����ɫ
		g.setColor(new Color(186, 227, 244));
		g.fillRect(0, 0, width, height);

		// �趨����
		g.setFont(new Font("Times New Roman", Font.BOLD, fontSize));

		// ���߿�
		// �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
		g.setColor(getRandColor(180, 250));
		for (int i = 0; i < 50; i++) {
			int x = random.nextInt(width - 5);
			int y = random.nextInt(height - 5);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// ȡ�����������֤��(4λ����)
		int fontNum = 4;
		int w0 = 4;
		int fontw = (width - (fontNum + 1) * w0) / fontNum;
		for (int i = 0; i < fontNum; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// ����֤����ʾ��ͼ����
			g.setColor(new Color(random.nextInt(10), random.nextInt(20), random
					.nextInt(30)));
			g.drawString(rand, w0 + (fontw + w0) * i, height - 2);
		}
		// ͼ����Ч
		g.dispose();
		return image;
	}

	private Color getRandColor(int fc, int bc) {// ������Χ��������ɫ
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;

		int n = bc - fc;
		int r = fc + random.nextInt(n);
		int g = fc + random.nextInt(n);
		int b = fc + random.nextInt(n);
		return new Color(r, g, b);
	}

	public String getSRand() {
		return sRand;
	}
	public BufferedImage update(){
		random rm = new random();
		BufferedImage bg =rm.creatImage();
		this.sRand = rm.sRand;
		return(bg);
	}

}
