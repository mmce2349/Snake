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
	
	private void createGame()
	{
		dots = 3;
		
		for(int z = 0; z < dots; z++) 
		{
			x[z] = 50 - z * 10;
			y[z] = 50;
		}
		
		locateApple();
		
		gameTimer = new Timer(delay, this);
		gameTimer.start();
	}
	
	@Override
	public void paintComponent(Graphics game)
	{
		super.paintComponent(game);
		doDrawing(game);
	}
	
	private void doDrawing(Graphics game)
	{
		if(inGame)
		{
			game.drawImage(apple, appleX, appleY, this);
			
			for(int z=0; z < dots; z++)
			{
				if(z==0) 
				{
					game.drawImage(head, x[z], y[z], this);
				}
				else
				{
					game.drawImage(ball, x[z], y[z],  this);
				}
			}
			Toolkit.getDefaultToolkit().sync();
		}
		else
		{
			gameOver(game);
		}
		
	}
	
	private void gameOver(Graphics game)
	{
		String message = "Game over loser";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);
		
		game.setColor(Color.RED);
		game.setFont(small);
		game.drawString(message, (boardWidth- metr.stringWidth(message)) / 2, boardHeight / 2);
	}
	
	
	
	
}