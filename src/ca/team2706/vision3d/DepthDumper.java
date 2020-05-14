package ca.team2706.vision3d;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
					SceneData sceneData = new SceneData(points, 640, 480);
					
					
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
