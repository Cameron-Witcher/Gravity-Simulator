package me.cameron.tp2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int DELAY = 1;
	List<Ball> balls = new ArrayList<>();
	List<Ball> balls__remove = new ArrayList<>();
	Timer timer;
	Random random;
	
	double cx = 0;
	double cy = 0;

	public Screen() {
		setBackground(Color.WHITE);
		timer = new Timer(DELAY, this);
		timer.start();
		random = new Random();
		MouseListener mouseListener = new MouseListener();
		addKeyListener(new TAdapter());
		addMouseMotionListener(mouseListener);
		addMouseListener(mouseListener);
		setFocusable(true);
		requestFocusInWindow();
		load();

	}

	public void load() {
		try {
			for (int i = 0; i != 10; i++) {
				balls.add(new Ball(random.nextInt(getWidth()), random.nextInt(getHeight())));
			}
		} catch (IllegalArgumentException ex) {
			for (int i = 0; i != 1; i++) {
				balls.add(new Ball(random.nextInt(500), random.nextInt(500)));
			}
		}
	}

	public void unload() {
		balls.clear();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		cx = 0;
		cy = 0;
		for (Ball ball : balls) {
			cx = cx + ball.x;
			cy = cy + ball.y;
		}
		cx = cx / balls.size();
		cy = cy / balls.size();
		
		for(Ball ball : balls) {
			ball.update();
			ball.draw(g);
		}
		
		for(Ball ball : balls__remove) {
			balls.remove(ball);
		}

		
		g.dispose();
	}

	private void reset() {
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_R) {
				unload();
				load();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				pause();
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				reset();
				load();
			}

		}
	}

	private class MouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {

			// mx = e.getX();
			// my = e.getY();

		}
	}

	public void pause() {
	}

}
