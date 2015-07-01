package course.choose.login;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class random {
	private String sRand = "";

	public BufferedImage creatImage() {
		// 在内存中创建图象
		int width = 48, height = 18;
		int fontSize = 18;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(new Color(186, 227, 244));
		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.BOLD, fontSize));

		// 画边框
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(180, 250));
		for (int i = 0; i < 50; i++) {
			int x = random.nextInt(width - 5);
			int y = random.nextInt(height - 5);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		int fontNum = 4;
		int w0 = 4;
		int fontw = (width - (fontNum + 1) * w0) / fontNum;
		for (int i = 0; i < fontNum; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(random.nextInt(10), random.nextInt(20), random
					.nextInt(30)));
			g.drawString(rand, w0 + (fontw + w0) * i, height - 2);
		}
		// 图象生效
		g.dispose();
		return image;
	}

	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
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
