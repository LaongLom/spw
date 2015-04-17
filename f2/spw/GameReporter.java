package f2.spw;

public interface GameReporter {

	long getScore();
	long getTimes();
	boolean isRungame();
	boolean isCooldown_bg();
	boolean gameIsWin();
}
