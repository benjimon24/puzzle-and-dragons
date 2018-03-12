import processing.core.PApplet;


public class Main extends PApplet{
    Board boardObj = new Board(50,50, 50);
    Orb[] board = boardObj.getBoard();
    int selectedOrb;
    boolean isAnimating = false;

    public static void main(String[] args) {
        // write your code here
        PApplet.main("Main", args);
    }

    public void settings(){
        size(400,600);

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
            }
        }

        boardObj.moveOrbs();
//        isAnimating = !boardObj.inDefaultPosition();

    }

    public void mousePressed(){
//        if (!isAnimating){
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
//        }
    }

    public void mouseDragged() {
//        if (!isAnimating) {
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
//        }
    }

    public void mouseReleased() {
            if (boardObj.getSelected() != null){
                boardObj.getSelected().setSelected(false);
            }
            boardObj.deleteMatches();
            boardObj.cascadeAll();
//            boardObj.populateBoard();
        }


}

