import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.Math;

public class Board {
    private Orb[] board = new Orb[30];
    private int boardPosX;
    private int boardPosY;
    private int orbSize;

    public Board(int posX, int posY, int size){
        boardPosX = posX;
        boardPosY = posY;
        orbSize = size;
    }

    public Orb[] getBoard() {
        return board;
    }

    public int getBoardPosX() {
        return boardPosX;
    }

    public int getBoardPosY() {
        return boardPosY;
    }

    public int getOrbSize() {
        return orbSize;
    }

    public void populateBoard(){
        for (int i = 0; i < board.length; i++){
            if (board[i] == null) {
                Random rng = new Random();
                int color = rng.nextInt(6);
                Orb orb = new Orb(color, ((i % 6) * orbSize) + boardPosX, boardPosY - 200 + (i / 6) * orbSize);
                board[i] = orb;
            }
        }
    }

    public void resetOrbPositions(){
        for (int i = 0; i < board.length; i++){
            if (board[i] != null) {
                Orb orb = board[i];
                orb.setPosX(((i % 6) * orbSize) + boardPosX);
                orb.setPosY(((i / 6) * orbSize) + boardPosY);
            }
        }
    }

    public Orb getSelected(){
        for (int i = 0; i < board.length; i++) {
            if (board[i] != null && board[i].isSelected()) {
                return board[i];
            }
        }
        return null;
    }

    public void moveOrbs(){
        for (int i = 0; i < board.length; i++){
            if (board[i] != null && !isOrbAtDefault(i) && !board[i].isSelected()){
                Orb orb = board[i];
                if (Math.abs(orb.getPosX() - orbDefaultX(i)) > 12 || Math.abs(orb.getPosY() - orbDefaultY(i)) > 12) {
                    orb.setPosX((int) (orb.getPosX() + Math.signum(orbDefaultX(i) - orb.getPosX()) * 3));
                    orb.setPosY((int) (orb.getPosY() + Math.signum(orbDefaultY(i) - orb.getPosY()) * 3));
                } else {
                    orb.setPosX((int) (orb.getPosX() + Math.signum(orbDefaultX(i) - orb.getPosX()) ));
                    orb.setPosY((int) (orb.getPosY() + Math.signum(orbDefaultY(i) - orb.getPosY()) ));
                }
            }
        }
    }

    public boolean inDefaultPosition(){
        for (int i = 0; i < board.length; i++){
            if (board[i] != null && !isOrbAtDefault(i)) { return false; }
        }
        return true;
    }

    public boolean isOrbAtDefault(int i){
        Orb orb = board[i];
        return orb.getPosX() == orbDefaultX(i) && orb.getPosY() == orbDefaultY(i) && !board[i].isSelected();
    }

    public int orbDefaultX(int i) {
        return ((i % 6) * orbSize) + boardPosX;
    }

    public int orbDefaultY(int i) {
        return ((i / 6) * orbSize) + boardPosY;
    }

    public HashSet findMatches(){
        HashSet<Integer> matches = new HashSet<>();
        matches.addAll(findColMatches());
        matches.addAll(findRowMatches());
        return matches;
    }

    public void deleteMatches(){
        HashSet<Integer> matches = findMatches();
        for (int i : matches) {
            board[i] = null;
        }
    }

    public ArrayList findColMatches(){
        ArrayList<Integer> matches = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            ArrayList<Integer> row = getCol(i);
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < row.size(); j++) {
                if (board[row.get(j)] != null) {
                    if (temp.isEmpty() || board[temp.get(0)].getColor() == board[row.get(j)].getColor()) {
                        temp.add(row.get(j));
                    } else {
                        if (temp.size() >= 3) {
                            matches.addAll(temp);
                        }
                        temp = new ArrayList<>();
                        temp.add(row.get(j));
                    }
                    if (j == row.size() - 1) {
                        if (temp.size() >= 3) {
                            matches.addAll(temp);
                        }
                    }
                }
            }
        }
        return matches;
    }

    public ArrayList<Integer> findRowMatches(){
        ArrayList<Integer> matches = new ArrayList<>();
        for (int i = 0; i < board.length; i += 6){
            ArrayList<Integer> row = getRow(i);
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j = 0; j < row.size(); j++) {
                if (board[row.get(j)] != null) {
                    if (temp.isEmpty() || board[temp.get(0)].getColor() == board[row.get(j)].getColor()) {
                        temp.add(row.get(j));
                    } else {
                        if (temp.size() >= 3) {
                            matches.addAll(temp);
                        }
                        temp = new ArrayList<>();
                        temp.add(row.get(j));
                    }
                    if (j == row.size() - 1) {
                        if (temp.size() >= 3) {
                            matches.addAll(temp);
                        }
                    }
                }
            }
        }
        return matches;
    }

    public ArrayList<Integer> getRow(int orbIndex){
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (i / 6 == orbIndex / 6){ row.add(i); }
        }
        return row;
    }

    public ArrayList<Integer> getCol(int orbIndex){
        ArrayList<Integer> col = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (i % 6 == orbIndex % 6){ col.add(i); }
        }
        return col;
    }

    public void cascadeAll(){
        for (int i = 0; i < 6; i++){
            ArrayList<Integer> col = getCol(i);
            cascadeCol(col);
        }
    }

    public void cascadeCol(ArrayList<Integer> col){
        int j = col.size() - 1;
        for (int i = col.size() - 1; i >= 0; i--){
            if (board[col.get(i)] != null){
                Orb temp = board[col.get(i)];
                board[col.get(i)] = board[col.get(j)];
                board[col.get(j)] = temp;
                j--;
            }
        }
    }
}
