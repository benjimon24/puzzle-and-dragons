import processing.core.PApplet;


public class Main extends PApplet{
    Board boardObj = new Board();
    Orb[] board = boardObj.getBoard();
    int orbSize = 50;
    int boardPosX = 200;
    int boardPosY=  200;
    int selectedOrb;

    public static void main(String[] args) {
        // write your code here
        PApplet.main("Main", args);
    }

    public void settings(){
        size(600,600);

    }
    public void setup(){
        boardObj.populateBoard();

    }
    public void draw(){
        background(0);
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
                ellipse(orb.getPosX(),orb.getPosY(), orbSize, orbSize);
            }
        }
    }

    public void mousePressed(){
        for (int i = 0; i < board.length; i++) {
            Orb orb = board[i];
            if (mouseX > orb.getPosX() - (orbSize / 2) &&
                mouseX < orb.getPosX() + (orbSize / 2) &&
                mouseY > orb.getPosY() - (orbSize / 2) &&
                mouseY < orb.getPosY() + (orbSize / 2)){
                orb.setSelected(true);
                selectedOrb = i;
            }
        }
    }

    public void mouseDragged() {
        for (int i = 0; i < board.length; i++) {
            Orb orb = board[i];
            if (orb.isSelected()) {
                orb.setPosX(mouseX);
                orb.setPosY(mouseY);
            } else if (mouseX > orb.getPosX() - orbSize / 2 &&
                    mouseX < orb.getPosX() + orbSize / 2 &&
                    mouseY > orb.getPosY() - orbSize / 2 &&
                    mouseY < orb.getPosY() + orbSize / 2) {
                Orb temp = board[selectedOrb];
                board[selectedOrb] = board[i];
                board[i] = temp;
                board[selectedOrb].setPosX(((selectedOrb % 6) * 50) + boardPosX);
                board[selectedOrb].setPosY(((selectedOrb / 6) * 50) + boardPosY);
                selectedOrb = i;
            }
        }
    }

    public void mouseReleased() {
        boardObj.resetOrbPositions();
        boardObj.deleteMatches();
//        boardObj.populateBoard();
    }

}

