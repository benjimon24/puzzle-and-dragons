import processing.core.PApplet;


public class Main extends PApplet{
    Board boardObj = new Board(50,100, 50, 6);
    Orb[] board = boardObj.getBoard();
    int highScore = 0;
    int selectedOrb;
    boolean gameOver = false;
    int timer = 30000;

    public static void main(String[] args) {
        // write your code here
        PApplet.main("Main", args);
    }

    public void settings(){
        size(800,600);

    }
    public void setup(){
        boardObj.populateBoard();
        while (!boardObj.findMatches().isEmpty()){
            boardObj.deleteMatches();
            boardObj.populateBoard();
        }

    }

    public void draw(){
        background(0);
        if (timer > 0) {timer--;}

        if (timer > 0) {
            //score
            textSize(32);
            fill(255, 255, 255);
            text("Score: " + highScore, 250, 50);
            text ("Timer: " + timer / 100, 25, 50);

            //board
            for (int i = 0; i < board.length; i++) {
                if ((i + (i / 6)) % 2 == 0) {
                    fill(75, 50, 25, 100);
                } else {
                    fill(175, 125, 50, 100);
                }
                rect((i % 6) * boardObj.getOrbSize() + boardObj.getBoardPosX() - boardObj.getOrbSize() / 2,
                        (i / 6) * boardObj.getOrbSize() + boardObj.getBoardPosY() - boardObj.getOrbSize() / 2,
                        boardObj.getOrbSize(),
                        boardObj.getOrbSize());
            }
           // Orbs
            for (int i = 0; i < board.length; i++) {
                if (board[i] != null) {
                    Orb orb = board[i];
                    fill(orb.getR(), orb.getG(), orb.getB());
                    if (orb.isSelected()){
                        stroke(255);
                        strokeWeight(2);
                    } else {
                        noStroke();
                    }
                    ellipse(orb.getPosX(),orb.getPosY(), boardObj.getOrbSize(), boardObj.getOrbSize());
                    noStroke(); //otherwise the whole board has a stroke
                }
            }


            if (!boardObj.inDefaultPosition()) {
                boardObj.moveOrbs();
            } else {
                highScore += boardObj.findMatches().size() * 1000;
                boardObj.deleteMatches();
                boardObj.cascadeAll();
                boardObj.populateBoard();
                }
        } else {
            textSize(32);
            fill(255, 255, 255);
            text("GAME OVER! Your final score is:", 50, 50);
            text(highScore, 50,  200) ;
        }
    }

    public void mousePressed(){
        for (int i = 0; i < board.length; i++) {
            if (board[i] != null) {
                Orb orb = board[i];
                if (mouseX > orb.getPosX() - (boardObj.getOrbSize() / 2) &&
                        mouseX < orb.getPosX() + (boardObj.getOrbSize() / 2) &&
                        mouseY > orb.getPosY() - (boardObj.getOrbSize() / 2) &&
                        mouseY < orb.getPosY() + (boardObj.getOrbSize() / 2)) {
                    orb.setSelected(true);
                    selectedOrb = i;
                }
            }
        }
    }

    public void mouseDragged() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] != null) {
                Orb orb = board[i];
                if (orb.isSelected()) {
                    orb.setPosX(mouseX);
                    orb.setPosY(mouseY);
                } else if (mouseX > orb.getPosX() - boardObj.getOrbSize() / 2 &&
                        mouseX < orb.getPosX() + boardObj.getOrbSize() / 2 &&
                        mouseY > orb.getPosY() - boardObj.getOrbSize() / 2 &&
                        mouseY < orb.getPosY() + boardObj.getOrbSize() / 2) {
                    Orb temp = board[selectedOrb];
                    board[selectedOrb] = board[i];
                    board[i] = temp;
                    board[selectedOrb].setPosX(((selectedOrb % 6) * boardObj.getOrbSize()) + boardObj.getBoardPosX());
                    board[selectedOrb].setPosY(((selectedOrb / 6) * boardObj.getOrbSize()) + boardObj.getBoardPosY());
                    selectedOrb = i;
                }
            }
        }
    }

    public void mouseReleased() {
        if (boardObj.getSelected() != null){
            boardObj.getSelected().setSelected(false);
        }
    }


}

