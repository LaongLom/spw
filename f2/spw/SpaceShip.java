package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 10;
	private int i = 0;
	private int z = 1;
	public boolean shild = false;
	GameEngine ge;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		if(shild){
			if(ge.countTime % 50 == 0)
			shilder(g);
		}
		
	}
	
	public void shilder(Graphics2D g){
		double r = 30.0;
		int n = 10;
		double a,b;
		
		if(z == 1){//(+,-),(-,+)

			
			a = i*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(Color.darkGray);
			g.fillRect((int)(x+a+7), (int)(y+b+10), 5, 5);
			
			a = (i+2)*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(Color.orange);
			g.fillRect((int)(x+a+7), (int)(y+b+10), 5, 5);
			
			a = (i+4)*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(Color.pink);
			g.fillRect((int)(x+a+7), (int)(y+b+10), 5, 5);
					
			/*...............*/	
			
			a = i*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(Color.MAGENTA);
			g.fillRect((int)(x-a+7), (int)(y-b+10), 5, 5);
			
			a = (i+2)*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(Color.yellow);
			g.fillRect((int)(x-a+7), (int)(y-b+10), 5, 5);
			
			a = (i+4)*r/n;
			b = Math.sqrt( r*r - a*a );
			g.setColor(Color.white);
			g.fillRect((int)(x-a+7), (int)(y-b+10), 5, 5);
			
			if(i <= 5)
				i+=2;
			else{
				i=9;
				z=2;
			}
		}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public void move_y(int direction){
		y += (step * direction);
	
		if(y < 0)
			y = 0;
		if(y > 550)
			y = 550;
	}
	public void setPosition(){
		x = 180;
		y = 550;
	}
	public boolean isWin(){
		if(y < 50){
			return true;
		}
		return false;
	}

}
