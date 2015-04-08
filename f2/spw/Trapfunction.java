package f2.spw;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Trapfunction{
	
	public static boolean trapControl = true;
	public static boolean setBG = false;
	
	
	public static void controlVehicle(KeyEvent e, SpaceShip v) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move_x(1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move_x(-1);
			break;
		case KeyEvent.VK_UP:
			v.move_y(1);
			break;
		case KeyEvent.VK_DOWN:
			v.move_y(-1);
			break;
		}
	}
	
	 


	
}