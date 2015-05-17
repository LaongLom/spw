
import java.awt.Color;
import java.awt.Graphics2D;

public class Trap extends Sprite{
	
	public boolean trapControl = true;
	Trapfunction c;
	
	public Trap(int x, int y) {
		super(x, y, 20, 20);
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		if(c.setBG)
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
			
		g.fillRect(x, y, width, height);		
	}
	
}