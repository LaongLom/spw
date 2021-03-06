//package f2.spw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import java.awt.Font;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;
	Graphics2D big;
	Readfile rf = new Readfile();
	
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(Color.BLACK);
		rf.readfile();
	}

	public void updateGameUI(GameReporter reporter){
	
		
		big.clearRect(0, 0, 400, 600);		
		big.setColor(Color.WHITE);
		big.setFont(new Font("Helvetica", Font.PLAIN, 15));
		
		big.drawString("BestTime", 70, 20);
		big.drawString(rf.gbt(),170, 20);
		
		big.drawString("Time", 250, 20);
		big.drawString(String.format("%10d", reporter.getTimes()), 300, 20);
		
		if(reporter.isCooldown_bg()){
			big.setBackground(Color.RED);
		}
		else
			big.setBackground(Color.BLACK);
		
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		if(!reporter.isRungame() && reporter.gameIsWin()){
			setGameWin();
			rf.readfile();
		}
		else if(!reporter.isRungame()){
			setGameOver();
		}
		
		
			
		repaint();
	}
	
	private void setGameOver(){
		big.setColor(Color.WHITE);
		big.setFont(new Font("TimesRoman", Font.BOLD, 30));
		big.drawString("GAME OVER", 100, 280);
		big.setFont(new Font("Helvetica", Font.PLAIN, 15));
		big.drawString("Enter to PLAY again", 125, 300);
	}
	
	private void setGameWin(){
		big.setColor(Color.WHITE);
		big.setFont(new Font("TimesRoman", Font.BOLD, 30));
		big.drawString("Win!!", 160, 280);
		big.setFont(new Font("Helvetica", Font.PLAIN, 15));
		big.drawString("Enter to PLAY again", 125, 300);
	}
	public String getReadfile(){
		return rf.gbt();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
