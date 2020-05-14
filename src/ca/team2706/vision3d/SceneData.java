package ca.team2706.vision3d;

public class SceneData {
	private Point[][] data;
	private int width,height;
	public SceneData(Point[][] data, int width, int height) {
		super();
		this.data = data;
		this.width = width;
		this.height = height;
	}
	public Point[][] getData() {
		return data;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setData(int x, int y, Point point) {
		data[x][y] = point;
	}
}
