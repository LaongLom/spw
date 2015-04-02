package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class Trap extends Sprite{
	
	public Trap(int x, int y) {
		super(x, y, 20, 20);
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.YELLOW);			
		g.fillRect(x, y, width, height);
		
	}
	
}