
import javax.swing.*;

//This the main file where the game willl execute

//Import the essential tools

public class App{
    public static void main(String[] args){
        //Define the dimensions for our game window
        int boardWidth = 360;
        int boardHeight = 640;

        //Frame
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(boardWidth, boardHeight);//set the size of the window
        frame.setLocationRelativeTo(null); //palce window center of screen
        frame.setResizable(false); // make user not able to resize the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//enable user to close the game

        //Create a flappy bird object
        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack(); //we include this to exclude the title bar size from dimensions
        frame.setVisible(true); //this will make the window visible
        
    }
}