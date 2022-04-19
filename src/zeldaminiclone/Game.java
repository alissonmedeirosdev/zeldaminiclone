package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	public static int WIDTH = 480, HEIGHT = 480;
	public Player player; 
	public World world;
	public List<Enemy> enemy = new ArrayList<Enemy>();
	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		new Spritesheet();
		world = new World();
		player = new Player(32,32);
		enemy.add(new Enemy(32,32));
		
	}
	
	public void tick() {
		player.tick();
		
		for(int i = 0; i < enemy.size(); i++) {
			enemy.get(i).tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		
//		Para não ficar piscando
		g.setColor(new Color(0,135,13));
		g.fillRect(0,0, WIDTH, HEIGHT);
		
//		==============================================================
		
		player.render(g);
		
		for(int i = 0; i < enemy.size(); i++) {
			enemy.get(i).render(g);
		}
		
		world.render(g);
		
//		==============================================================
		
		bs.show();
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame(); 
		
//		Configurações da Tela 
		frame.add(game);
		frame.setTitle("Mini Zelda");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
//		??
		new Thread(game).start();
	}
	
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT ) {
			player.left = true;
		}
		
		if (e.getExtendedKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}else if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT ) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			player.shoot = true;
		}
		
		if (e.getExtendedKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}else if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
