//This file contains the game logic
//Import the essential tools
import java.awt.*;
import java.awt.event.*;
import java.io.PipedInputStream;
import java.util.ArrayList;
import javax.swing.*;

//Class (Our class will inherite the JPanel class)
public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    //Demensions
    int boardWidth = 360;
    int boardHeight = 640;

    // Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    //Bird
    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    //inner class
    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        //Constructor
        Bird(Image img) {
            this.img = img;
        }

    }

    // Pipes (obsticles)
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    //inner class pipe class
    class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;      

        //constructor
        Pipe(Image img){
            this.img = img;           
        }
    }

    //Field for bird
    //Game logic
    Bird bird;
    int velocityX = -4; //speed at which pipe moves to the left
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;

    //The timer for the loop
    Timer gameLoop;
    Timer placePipesTimer;

    //set Constructor
    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        //setBackground(Color.BLUE);
        setFocusable(true);
        addKeyListener(this);

        //load the images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        
        //Bird 
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        //Place pipes timer
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });

        //Game Timer
        gameLoop = new Timer(1000/60, this); //1000/60 = 16.6
        gameLoop.start();
    }

    //Methods
    public void placePipes() {
        
        Pipe topPipe = new Pipe(topPipeImg);
        pipe.add(topPipe);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //background
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null );
        
        //bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        
        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipeHeight, null);
        }
    
    }

    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            velocityY = -9;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
