import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Game extends JPanel implements Runnable,KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500,HEIGHT=500;
	private Thread thread;
	public boolean running,right=false,left=false,up=false,down=false,gameover=false;
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	private Food food;
	private ArrayList<Food> foods;
	private Random r;
	private int xCoor = 10, yCoor=10,size=5,ticks=0,score=0;
	
	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		snake = new ArrayList<BodyPart>();
		foods = new ArrayList<Food>();
		r = new Random();
		start();
	}
	public void start() { 
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public void stop() { 
		running = false;
		try {
			thread.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	public void GameOver() {
		running = false;
		gameover = true;
		repaint();
	}
	public void tick() {
		if(snake.size()==0) {
			b = new BodyPart(xCoor,yCoor,10);
			snake.add(b);
		}
		if(foods.size()==0) {
			int xCoor = r.nextInt(49);
			int yCoor = r.nextInt(49);
			food = new Food(xCoor,yCoor,10);
			foods.add(food);
		}
		for(int i =0; i< foods.size();i++) {
			if(xCoor==foods.get(i).getxCoor()&&yCoor == foods.get(i).getyCoor()) {
				size++;
				foods.remove(i);
				score++;
				i++;
			}
		}
		if(xCoor<0||xCoor>49||yCoor<0||yCoor>49) {
			GameOver();
		}
		ticks++;
		if(ticks>500000) {
			if(right)xCoor++;
			if(left)xCoor--;
			if(up)yCoor--;
			if(down)yCoor++;
			ticks = 0;
			b = new BodyPart(xCoor,yCoor,10);
			snake.add(b);
			if(snake.size()>size) {
				snake.remove(0);
			}
				
			
		}
	}
	public void paint(Graphics g) {
		if(running) {
			g.clearRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH,HEIGHT);
			
			
			for(int i = 0; i < WIDTH/10; i++) {
				g.drawLine(i*10, 0, i*10, HEIGHT);
			}
			for(int i = 0; i < HEIGHT/10; i++) {
				g.drawLine(0, i*10,HEIGHT, i*10);
			}
			for(int i = 0; i < snake.size(); i++) {
				snake.get(i).Draw(g);
			}
			for(int i = 0 ; i <foods.size();i++) {
				foods.get(i).Draw(g);
			}
			Font font = new Font("Sans Serif", Font.BOLD, 25);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score),250 , 50);
		}
		if(gameover) {
			g.clearRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH,HEIGHT);
			Font font = new Font("Sans Serif", Font.BOLD, 25);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Your score is...",170 , 150);
			g.drawString(Integer.toString(score),250 , 250);
			
			
		}
		
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if((key==KeyEvent.VK_RIGHT||key==KeyEvent.VK_D)&&!left) {
			right = true;
			up=false;
			down=false;
			left=false;
		}
		if((key==KeyEvent.VK_LEFT||key==KeyEvent.VK_A)&&!right) {
			up=false;
			down=false;
			left=true;
			right=false;
		}
		if((key==KeyEvent.VK_UP||key==KeyEvent.VK_W)&&!down) {
			right = false;
			down=false;
			left=false;
			up=true;
		}
		if((key==KeyEvent.VK_DOWN||key==KeyEvent.VK_S)&&!up) {
			right = false;
			up=false;
			left=false;
			down=true;
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running) {
			tick();
			repaint();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
