package zeldaminiclone;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	public static BufferedImage spritesheet;
	
	public static BufferedImage[] player_front;
	
	public static BufferedImage[] enemy_front;
	
	public static BufferedImage tileWall;
	
//	260,219
	
	public Spritesheet() {
//		Carregar a imagem
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		player_front = new BufferedImage[2];
		enemy_front = new BufferedImage[2];
		
//		SPRITESHEET PLAYER
		player_front[0] = Spritesheet.getSprite(0, 11, 16, 16);
		player_front[1] = Spritesheet.getSprite(16, 11, 16, 16);
		
//		SPRITESHEET ENEMY
		enemy_front[0] = Spritesheet.getSprite(260, 219, 16, 16);
		enemy_front[1] = Spritesheet.getSprite(278, 219, 16, 16);
		
		tileWall = Spritesheet.getSprite(302,218, 16, 16);
	}

//	Pega a sub image dessa imagem
	public static BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
}
