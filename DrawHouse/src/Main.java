import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/* By: Jannie Zhong, Period 4 APCS
 * 
 * This program models 2 people in front of a house, able to move and interact.
 * In order to interact:
 * 		- press the arrow keys (up, down, left, right) to move the person on the left
 * 		- press the w, s, a, d (up, down, left, right respectively) to move the person on the left
 * 		- press the 1 and 2 keys to scale the house down and up respectively
 * 		- press the space key to make the person on the left wave
 * 		- click anywhere on the screen to make the house move to that point
 */

public class Main{

	public static int startWidth, startHeight;

	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		startWidth = 500;
		startHeight = 500;

			window.setSize(startWidth, startHeight);
		window.setMinimumSize(new Dimension(200,200));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);

	}


}
