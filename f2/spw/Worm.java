
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Worm extends Sprite{
	
	//public boolean trapControl = true;
	private int i;
	private int step = 10;
	private int tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7,tmp8,tmp9,tmp10,tmp11,tmp12,tmp13,tmp14;
	
	private ArrayList<Double> worms = new ArrayList<Double>();
	
	GameEngine ge;
	
	public Worm(int x, int y) {
		super(x, y, 10, 10);
		tmp1 = x;
		tmp2 = y;
		tmp3 = x;
		tmp4 = y;
		tmp5 = x;
		tmp6 = y;
		tmp7 = x;
		tmp8 = y;
		tmp9 = x;
		tmp10 = y;
		tmp11 = x;
		tmp12 = y;
		tmp13 = x;
		tmp14 = y;

		
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		//if(ge.countTime % 100 == 0)
			set(x,y);
			
		g.setColor(Color.magenta);
		g.fillRect(tmp1, tmp2, width, height);		
		
		g.setColor(Color.blue);
		g.fillRect(tmp3, tmp4, width, height);
						
		g.setColor(Color.cyan);
		g.fillRect(tmp5, tmp6, width, height);
				
		g.setColor(Color.green);
		g.fillRect(tmp7, tmp8, width, height);
		
		g.setColor(Color.yellow);
		g.fillRect(tmp9, tmp10, width, height);
		
		g.setColor(Color.orange);
		g.fillRect(tmp11, tmp12, width, height);
		
		g.setColor(Color.red);
		g.fillRect(tmp13, tmp14, width, height);
		

	}
	public void set(int x,int y){
		
		tmp13 = tmp11;
		tmp14 = tmp12;
		tmp11 = tmp9;
		tmp12 = tmp10;
		tmp9  = tmp7;
		tmp10 = tmp8;
		tmp7 = tmp5;
		tmp8 = tmp6;
		tmp5 = tmp3;
		tmp6 = tmp4;
		tmp3 = tmp1;
		tmp4 = tmp2;
		tmp1 = x;
		tmp2 = y;
		
	}
	public void proceed(){
		if(Math.random() > 0.5){
			if(Math.random() > 0.5)
				y += step;
			else
				y -= step;
		}
		else{
			if(Math.random() > 0.5)
				x += step;
			else
				x -= step;		
		}
		if(y > 550)
			y = 550;
		if(y < 60)
			y = 60;
			
		if(x > 360)
			x = 360;
		if(x < 0)
			x = 0;
			
	}
	
	
	public ArrayList<Double> getsRectangle() {
	
		//return new Rectangle2D.Double(x, y, width, height);
		
		worms.add(new Rectangle2D.Double(tmp1, tmp2, width, height));
		worms.add(new Rectangle2D.Double(tmp3, tmp4, width, height));
		worms.add(new Rectangle2D.Double(tmp5, tmp6, width, height));
		worms.add(new Rectangle2D.Double(tmp7, tmp8, width, height));
		worms.add(new Rectangle2D.Double(tmp9, tmp10, width, height));
		worms.add(new Rectangle2D.Double(tmp11, tmp12, width, height));
		worms.add(new Rectangle2D.Double(tmp13, tmp14, width, height));
		
		return worms;
	}
	
}