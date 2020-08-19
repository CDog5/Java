import java.awt.Color;
import java.awt.Graphics;

public class BodyPart {
	public int xCoor,yCoor,width,height;
	public BodyPart(int xCoor, int yCoor,int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}
	public void Tick() {
		
	}
	public void Draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(xCoor * width, yCoor*height, width, height);
	}
	public int getxCoor() {
		return this.xCoor;
	}
	public int getyCoor() {
		return this.yCoor;
	}
	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}
	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
}
