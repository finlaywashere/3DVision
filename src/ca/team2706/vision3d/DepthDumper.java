package ca.team2706.vision3d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class DepthDumper implements Runnable {

	private static List<Integer[]> buffers = new ArrayList<Integer[]>();
	private static File dumpDir = new File("data/");
	private static boolean dumpData = false;

	private static Thread thread;

	public static void add(Integer[] frame) {

		buffers.add(frame);

	}

	public static void start() {
		if (dumpData) {
			dumpDir.mkdirs();
		}
		thread = new Thread(new DepthDumper());
		thread.start();
	}

	@Override
	public void run() {
		Display pointDisplay = new Display();
		while (true) {
			try {
				if (buffers.size() > 0) {
					
					long startTime = System.currentTimeMillis();
					
					Integer[] data = buffers.get(buffers.size()-1);
					buffers.remove(buffers.size()-1);
					if(buffers.size() > 2) {
						buffers.clear();
					}

					Point[][] points = new Point[640][480];
					for(int y = 0; y < 480; y++) {
						for(int x = 0; x < 640; x++) {
							if(data[(y*640+x) * 2] == null)
								data[(y*640+x) * 2] = -1;
							int dataInt = data[(y*640+x) * 2];
							Point p = new Point(x, y, dataInt);
							points[x][y] = p;
						}
					}
					
					BufferedImage image = new BufferedImage(640,480,BufferedImage.TYPE_INT_RGB);
					Graphics g = image.createGraphics();
					for(int y = 0; y < 480; y++) {
						for(int x = 0; x < 640; x++) {
							// Kinda makes it look ok, theres prolly a better way to do it tho
							double z = Math.log((points[x][y].getZ()/1000000)) * 25;
							if(z > 255)
								z = 255;
							if(z < 0)
								z = 0;
							Color c;
							c = new Color((int)z,0,0);
							if(z == 255)
								c = new Color(0,0,255);
							if(z == 0)
								c = new Color(0,255,0);
							g.setColor(c);
							g.fillRect(x, y, 1, 1);
						}
					}
					g.dispose();
					
					pointDisplay.setImage(image);
					pointDisplay.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
