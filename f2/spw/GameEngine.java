package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
	private Trapfunction c;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v;	
	
	private Timer timer;
	
	private static final int TIMEtrap = 3000
	public int cd_move = 0;
	private long score = 0;
	private double difficulty = 0.1;
	private long countTime = 0;
	
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
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		cooldown();
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 1000;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er,tr;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
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
	}
	
	public void die(){
		timer.stop();
		gp.updateGameUI(this);
	}
	
	public void startOver(){
		gp.sprites.clear();
		trap.clear();
		enemies.clear();
		generateTrap();
		v.setPosition();
		gp.sprites.add(v);
		difficulty = 0.1;
		countTime = 0;
		cd_move = 0;
		v.shild = false;
		timer.restart();	
	}
	public void cooldown() {
		countTime = countTime + 50;
		cd_move = cd_move - 50;

		if(cd_move < 0)
			cd_move = 0;

	}
	public boolean isCooldown_move() {
		if(cd_move == 0){
			c.trapControl = true;
			return false;
		}
		else 
			return true;
	}
	
	public void randomTrap() {
		int random;
		
		random = (int)(Math.random()*4);
		
		if(random < 2){
			cd_move = cd_move + TIMEtrap;
			c.trapControl = false;
		}
		else if(random < 4){
			v.shild = true;
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
		}
	}

	public long getScore(){
		return score;
	}
	public long getTimes(){		
		return countTime/1000;
	}
	public boolean isRungame(){
		return timer.isRunning();
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
