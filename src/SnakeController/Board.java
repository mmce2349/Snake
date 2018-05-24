package SnakeController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{
	private final int boardWidth = 300;
	private final int boardHeight = 300;
	private final int dotSize = 10;
	private final int totalDots = 900;
	private final int randomPosition = 29;
	private final int delay = 140;
	
	private final int x[] = new int[totalDots];
	private final int y[] = new int[totalDots];
	
	private int dots;
	private int appleX;
	private int appleY;
	
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	private boolean inGame = true;
	 
	private Timer gameTimer;
	private Image ball;
	private Image apple;
	private Image head;
	
	public Board()
	{
		createBoard();
	}
	private void createBoard()
	{
		addKeyListener(new TAdapter());
		setBackground(Color.GRAY);
		setFocusable(true);
		setDoubleBuffered(true);
		
		setPreferredSize(new Dimension(boardWidth, boardHeight));
		loadImages();
		createGame();
	}
	private void loadImages()
	{
		ImageIcon ballImage = new ImageIcon("srs/Resources/dot.png");
		ball = ballImage.getImage();
		
		ImageIcon appleImage = new ImageIcon("srs/Resources/apple.png");
		apple = appleImage.getImage();
		
		ImageIcon headImage = new ImageIcon("srs/Resources/head.png");
		head = headImage.getImage();
	}
	
	
}
