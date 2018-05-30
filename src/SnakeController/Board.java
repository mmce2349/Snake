package SnakeController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import Resources.*;

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
		setBackground(Color.BLACK);
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
			
			for(int z = 0; z < dots; z++)
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
	
	private void checkApple()
	{
		if((x[0] == appleX) && (y[0] == appleY))
		{
			dots++;
			locateApple();
		}
	}
	//joint movement
	private void move()
	{
		for(int z = dots; z > 0; z --)
		{
			x[z] = x[(z-1)];
			y[z] = y[(z-1)];
			
		}
		if(leftDirection) 
		{
			x[0]-= dotSize;
			
		}
		if(rightDirection)
		{
			x[0]+= dotSize;
		}
		if(upDirection)
		{
			y[0] -= dotSize;
			
		}
		if(downDirection)
		{
			y[0] += dotSize;
		}
	}
	//borders and self collision
	private void checkCollision()
	{
		for (int z = dots; z > 0; z--)
		{
			if((z > 4) && (x[0] == x[z]) && (y[0] == y[z]))
			{
				inGame = false;
			}
				
		}
		if(y[0] >= boardHeight)
		{
			inGame = false;
		}
		if(y[0] < 0)
		{
			inGame = false;
		}
		if (x[0] >= boardWidth)
		{
			inGame = false;
		}
		if(x[0] < 0)
		{
			inGame = false;
		}
		if(!inGame)
		{
			gameTimer.stop();
		}
	}
	//randomly generate location of apple
	private void locateApple()
	{
		int location = (int) (Math.random() * randomPosition);
		appleX = ((location * dotSize));
		
		location = (int)(Math.random() * randomPosition);
		appleY = ((location * dotSize));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(inGame)
		{
			checkApple();
			checkCollision();
			move();
		}
		repaint();
	}
	private class TAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e) 
		{
			int key = e.getKeyCode();
			
			if((key == KeyEvent.VK_LEFT) && (!rightDirection))
			{
				leftDirection = true;
				upDirection = false;
				downDirection = false;
			}
			if((key == KeyEvent.VK_RIGHT) && (!leftDirection))
			{
				rightDirection = true;
				upDirection = false;
				downDirection = false;
			}
			if((key == KeyEvent.VK_UP) && (!downDirection))
			{
				upDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
			if((key == KeyEvent.VK_DOWN) && (!upDirection))
			{
				downDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
			
		}
	}
}
