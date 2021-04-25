package me.cameron.tp2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	double x;
	double y;

	double G = 0.00000000000000000000100000;

	double F = 0;

	double mass = new Random().nextInt(20);

	double size = mass / 2;

	double mx = 0;
	double my = 0;

	Point vec;

	public Ball(double x, double y) {
		this.x = x;
		this.y = y;

		vec = new Point(0, 0);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) (x - size / 2), (int) (y - size / 2), (int) size, (int) size);
	}

	public void update(Graphics g) {
		F = 0;
		mx=0;
		my=0;

		for (Ball ball : Main.getWindow().getScreen().balls) {
//			mx = 0;
//			my = 0;
//			F = 0;
			// F = (G*M_1*M_2)/r^2
			// F = ma

			F = F+(G * (ball.mass)) / Math.pow(distance(ball), 2);
			if (Double.isInfinite(F)) {
				F = 0;
			}
			mx = mx+(Math.cos(getAngleTo(ball.x, ball.y)));
			my = my+(Math.sin(getAngleTo(ball.x, ball.y)));
//			g.setColor(Color.RED);
//			g.drawLine((int) x, (int) y, (int) (x + mx * F), (int) (y + my * F));

//			move();

			if (getBounds().intersects(ball.getBounds())) {
				if (size > ball.size) {
					Main.getWindow().getScreen().balls__remove.add(ball);
					this.mass = mass + ball.mass;
					size = mass / 2;
					mx = 0;
					my = 0;
				}
			}
		}

		mx = mx/Main.getWindow().getScreen().balls.size();
		my = my/Main.getWindow().getScreen().balls.size();
		F = F / Main.getWindow().getScreen().balls.size();
		g.setColor(Color.RED);
		g.drawLine((int) x, (int) y, (int) (x + mx), (int) (y + my));
//		
		move();
	}

	private void move() {

		x = x + (mx);
		y = y + (my);
//		y = y+my;
	}

	private double getAngleTo(double x, double y) {
		return Math.toRadians((((Math.atan2((this.y - y), (this.x - x)))) * 180 / Math.PI) - 180);
	}

	private double distance(Ball ball) {
		return Math.sqrt(Math.pow(ball.x - x, 2) + Math.pow(ball.y - y, 2));
	}

	public void draw(Graphics g) {
		Color color = g.getColor();

		g.setColor(Color.WHITE);
		g.fillRect((int) (x - size / 2), (int) (y - size / 2), (int) size, (int) size);

		g.setColor(color);
	}

}
