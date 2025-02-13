//This file contains the game logic
//Import the essential tools
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
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
    Random random = new Random();

    //The timer for the loop
    Timer gameLoop;
    Timer placePipesTimer;

    //game over
    boolean gameOver = false;        

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
        placePipesTimer.start();

        //Game Timer
        gameLoop = new Timer(1000/60, this); //1000/60 = 16.6
        gameLoop.start();
    }

    //Methods
    public void placePipes() {
        // (0 -1) * pipeHeight/2 ->> (0-256)
        //128
        // 0 - 128 - (0 - 256) --> pipeHeight/4
        int randomPipeY = (int)(pipeY - pipeHeight/4 - Math.random()*pipeHeight/2);
        int openingSpace = boardHeight/4;
        
        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
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
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
    
    }

    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }

        if (bird.y > boardHeight) {
            gameOver = true;
        }
    }

    public boolean  collision(Bird a, Pipe b){
        return a.x < b.x + b.width &&   //a's top left corner doesn't reach b's top right corner
               a.x + a.width > b.x &&   //a's top right corner doesn't reach b's top left corner
               a.y < b.y + b.height &&  //a's top left corner doesn't reach b's bottom left corner
               a.y + a.height > b.y;    //a's bottom left corner doesn't reach b's top left corner
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver){
            placePipesTimer.stop();
            gameLoop.stop();
        }
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
