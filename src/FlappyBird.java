//This file contains the game logic
//Import the essential tools
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

//Class (Our class will inherite the JPanel class)
public class FlappyBird extends JPanel{
    //Demensions
    int boardWidth = 360;
    int boardHeight = 640;

    // Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;
    //set Constructor
    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        //setBackground(Color.BLUE);

        //load the images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
    }

    //Methods
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //background
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null )
    }
}
