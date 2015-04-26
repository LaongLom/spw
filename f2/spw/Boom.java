
import java.awt.Color;
import java.awt.Graphics2D;

public class Boom extends Sprite{

	private double r = 30;
	private double a,b;
	private int n = 20;
	private int i;
	
	public Boom(int x, int y) {
		super(x, y, 10, 10);
		
	}
	
		
	@Override
	public void draw(Graphics2D g) {
		
		effect(g);
		
	}
	public void effect(Graphics2D g){
	
		for(i=0;i<n;i++){
		
			a = i*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
			g.fillOval((int)(x+a+7), (int)(y+b+10), width, height);
					
			/*...............*/	
			
			a = i*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
			g.fillOval((int)(x-a+7), (int)(y-b+10), width, height);
			
			/*...............*/
			
			a = i*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
			g.fillOval((int)(x+a+7), (int)(y-b+10), width, height);
						
			/*...............*/
						
			a = i*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
			g.fillOval((int)(x-a+7), (int)(y+b+10), width, height);	
			
		}
		r = r + 5;
	}
}