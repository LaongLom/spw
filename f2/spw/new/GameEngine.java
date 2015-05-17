//package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;



public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
	Writefile wf = new Writefile();
	//Readfile rf = new Readfile();
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v;	
	private Trapfunction c;
	private Timer timer;

	private static final int TIMEtrap = 2000; //milliseconds
	
	private int numTrap = 70; 
	public static long countTime = 0;
	
	public int cd_move = 0;
	private int cd_bg = 0;
	private int cd_bm = 3000;
	
	//private boolean check = true;
	private boolean boom = false;
	
	private long score = 0;
	private double difficulty = 0.3;
	
	private ArrayList<Trap> trap = new ArrayList<Trap>();
	private ArrayList<Worm> worm = new ArrayList<Worm>();
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		generateTrap();
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateTrap(){
		for(int i=0;i<numTrap;i++){
			Trap t = new Trap((int)(Math.random()*390),(int)(Math.random()*500));
			if(t.y >= 50){
				gp.sprites.add(t);
				trap.add(t);
			}
		}
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 50);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
	
		cooldown();
		if(boom)
			cd_bm = cd_bm - 50;
			
		if(Math.random() < difficulty){
			generateEnemy();
		
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Worm> w_iter = worm.iterator();
		
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
			}
		}
		while(w_iter.hasNext()){ 
			Worm w = w_iter.next();
			w.proceed();
		}

				
		gp.updateGameUI(this);
		if(v.isWin()){
			win();
		}
		
		ArrayList<Double> wormElement ;//= new ArrayList<Double>();
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er,tr;
		
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				if(!v.shild){
					//die();
					setboom();
					boom = true;
				}	
				v.shild = false;
				enemies.remove(e);
				gp.sprites.remove(e);
				return;
			}
			if(isBoom())
				die();
		}
		for(Trap t : trap){
		    tr = t.getRectangle();
			if(tr.intersects(vr)){
				randomTrap();
				gp.sprites.remove(t);
				trap.remove(t);
				return;
			}
		}
		for(Worm w : worm){
		    wormElement = w.getsRectangle();
			for(Rectangle2D.Double d : wormElement){
				if(d.intersects(vr)){
					if(!v.shild){
						//die();
						setboom();
						boom = true;
					}
					v.shild = false;
					gp.sprites.remove(w);
					worm.remove(w);
					return;
				}
				if(isBoom())
				die();
			}
			w.worms.clear();
		}
	}
	
	public void die(){	
		timer.stop();
		gp.updateGameUI(this);			
	}
	public void win(){	
		timer.stop();
		if(Integer.parseInt(gp.getReadfile().trim()) > (int)(getTimes()))
			wf.write(String.format("%3d",getTimes()));
		gp.updateGameUI(this);			
	}
	public void setboom(){
		gp.sprites.remove(v);
		v.die = true;
		Boom b = new Boom(v.x,v.y);
		gp.sprites.add(b);
	}
	
	public void startOver(){
		gp.sprites.clear();
		trap.clear();
		enemies.clear();
		worm.clear();
		generateTrap();
		v.setPosition();
		gp.sprites.add(v);
		difficulty = 0.3;
		countTime = 0;
		cd_move = 0;
		cd_bg = 0;
		cd_bm = 3000;
		v.shild = false;
		v.die = false;
		boom = false;
		timer.restart();	
	}
	public void cooldown() {
		countTime = countTime + 50;
		cd_move = cd_move - 50;
		cd_bg = cd_bg - 50;
		if(cd_move < 0)
			cd_move = 0;
		if(cd_bg < 0)
			cd_bg = 0;
	}
	public boolean isCooldown_move() {
		if(cd_move == 0){
			c.trapControl = true;
			return false;
		}
		else 
			return true;
	}
	
	public boolean isCooldown_bg() {
		if(cd_bg == 0){
			c.setBG = false;
			return false;
		}
		else 
			return true;
	}
	
	public boolean isBoom() {	
		if(cd_bm < 0)
				cd_bm = 0;
				
		if(cd_bm == 0){
			return true;
		}
		else 
			return false;
	}
	
	
	public void randomTrap() {
		int random;
		
		random = (int)(Math.random()*6);
		
		if(random < 2){
			cd_move = cd_move + TIMEtrap;
			c.trapControl = false;
		}
		else if(random < 3){
			cd_bg = cd_bg + TIMEtrap;
			c.setBG = true;
		}
		else if(random == 4){
			v.shild = true;
		}
		if(random < 5){
			Worm w = new Worm((int)(Math.random()*390),(int)(Math.random()*200));
			gp.sprites.add(w);
			worm.add(w);
		}
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move_x(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move_x(1);
			break;
		case KeyEvent.VK_UP:
			v.move_y(-1);
			break;
		case KeyEvent.VK_DOWN:
			v.move_y(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		case KeyEvent.VK_ENTER:
			startOver();
			break;
		}
	}

	public int getScore(){
		return cd_move;
	}
	public long getTimes(){		
		return countTime/1000;
	}
	public boolean isRungame(){
		return timer.isRunning();
	}
	public boolean gameIsWin(){
		return v.isWin();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
			
		if(c.trapControl || !isRungame() || !isCooldown_move())
			controlVehicle(e);
		else
			c.controlVehicle(e,v);
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
