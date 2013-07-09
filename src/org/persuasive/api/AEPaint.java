package org.persuasive.api;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;

public abstract class AEPaint {

	private String name;
	private final int txtRow1 = 14, txtRow2 = 195, txtRow3 = 381, column = 378;
	private Color color1 = new Color(255, 0, 0, 220);
	private Color color2 = new Color(0, 0, 0);
	private final Color glare = new Color(255, 255, 255, 85);

	private final BasicStroke stroke1 = new BasicStroke(1);

	private final Font font1 = new Font("Arial", 0, 10);
	private final Font font2 = new Font("Arial", 2, 32);

	private final Image img1 = getImage("http://i1209.photobucket.com/albums/cc400/rwkjh/Icon_zps3bca86cf.png");

	public AEPaint(String na) {
		name = na;
	}

	public AEPaint(String na, Color c, Color c1) {
		name = na;
		color1 = c;
		color2 = c1;
	}

	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	private void drawMouseLines(Graphics render) {
		if (!Mouse.isPresent())
			return;

		render.setColor(color2);
		render.drawLine(0, (int) Mouse.getLocation().getY(), (int) Game
				.getDimensions().getWidth(), (int) Mouse.getLocation().getY());
		render.setColor(color1);
		render.drawLine((int) Mouse.getLocation().getX(), 0, (int) Mouse
				.getLocation().getX(), (int) Game.getDimensions().getHeight());

		String texts[] = { "Status: " + status() };

		int width = 0;

		for (String text : texts) {
			int textWidth = render.getFontMetrics().stringWidth(text);
			if (textWidth > width) {
				width = textWidth;
			}
		}

		width += 10;

		int height = 2 + (texts.length * 15);

		render.setColor(new Color(0, 0, 0, 100));
		render.fillRect((int) Mouse.getLocation().getX(), (int) Mouse
				.getLocation().getY(), width, height);
		render.setColor(Color.BLACK);
		render.drawRect((int) Mouse.getLocation().getX(), (int) Mouse
				.getLocation().getY(), width, height);

		render.setColor(Color.WHITE);

		int fontY = (int) Mouse.getLocation().getY() + 12;
		for (String text : texts) {
			render.drawString(text, (int) Mouse.getLocation().getX() + 5, fontY);
			fontY += 15;
		}
	}

	public void draw(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		drawMouseLines(g);
		g.translate(0, 50);
		if (percentage() != -1) {
			drawPercentageBar(6, 457, 509, 16, percentage(), g1);
			g.setFont(font1);
	        g.setColor(Color.WHITE);
	        g.drawString("Percent TNL: "+percentage()+"%", 207, 470);

		}
		g.setColor(color1);
		g.fillRect(6, 367, 509, 92);
		g.setColor(color2);
		g.setStroke(stroke1);
		g.drawRect(6, 367, 509, 92);
		g.setFont(font1);
		FontMetrics x = g.getFontMetrics();
		if (txtList1() != null) {
			for (int i = 0; i < (txtList1().length); i++) {
				g.drawString(txtList1()[i], txtRow1, column
						+ (x.getHeight() * i));
			}
		}

		g.drawLine(371, 370, 371, 458);
		g.drawLine(185, 370, 185, 458);
		if (txtList2() != null) {
			for (int i = 0; i < (txtList2().length); i++) {
				g.drawString(txtList2()[i], txtRow2, column
						+ (x.getHeight() * i));
			}
		}
		if (txtList3() != null) {
			for (int i = 0; i < (txtList3().length); i++) {
				g.drawString(txtList3()[i], txtRow3, column
						+ (x.getHeight() * i));

			}
		}
		g.setFont(font2);
		g.drawString(name, 11, 367);
		g.drawImage(img1, 570, 22, null);
		g.setFont(font1);

	}

	private void drawPercentageBar(int x, int y, int width, int height,
			double perc, Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON));
		g.setColor(color2);
		g.fillRect(x, y, width, height);

		int dperc = (int) (((perc > 100 ? 100 : perc) / 100) * width);
		g.setColor(color1);
		g.fillRect(x, y, (width > height ? dperc : width),
				(height > width ? dperc : height));
		g.setColor(Color.black);
		g.setStroke(stroke1);

		g.drawRect(x, y, width, height);
		g.setColor(glare);
		g.fillRect(x, y, width, (height/2));
	}

	public abstract String[] txtList1();

	public abstract String[] txtList2();

	public abstract String[] txtList3();

	public abstract String status();

	public int getPercentToLevel(final int index, final int endLvl) {

		final int lvl = Skills.getRealLevel(index);
		if (index == Skills.DUNGEONEERING && (lvl == 120 || endLvl > 120)) {
			return 0;
		} else if (lvl == 99 || endLvl > 99) {
			return 0;
		}
		final int xpTotal = Skills.XP_TABLE[endLvl] - Skills.XP_TABLE[lvl];
		if (xpTotal == 0) {
			return 0;
		}
		final int xpDone = Skills.getExperience(index) - Skills.XP_TABLE[lvl];
		return 100 * xpDone / xpTotal;
	}

	public int skill() {
		return -1;
	}

	public double percentage() {
		if (skill() == -1) {
			return -1;
		}
		return getPercentToLevel(skill(), (Skills.getRealLevel(skill()) + 1));
	}

}
