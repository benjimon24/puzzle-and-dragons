import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;

public class Board {
    private Orb[] board = new Orb[30];

    public Board(){
    }

    public Orb[] getBoard() {
        return board;
    }

    public void populateBoard(){
        for (int i = 0; i < board.length; i++){
            if (board[i] == null) {
                Random rng = new Random();
                int color = rng.nextInt(6);
                Orb orb = new Orb(color, ((i % 6) * 50) + 200, ((i / 6) * 50) + 200);
                board[i] = orb;
            }
        }
    }

    public void resetOrbPositions(){
        for (int i = 0; i < board.length; i++){
            Orb orb = board[i];
            orb.setPosX(((i % 6) * 50) + 200);
            orb.setPosY(((i / 6) * 50) + 200);
            orb.setSelected(false);
        }
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
                if (temp.isEmpty() || board[temp.get(0)].getColor() == board[row.get(j)].getColor()){
                    temp.add(row.get(j));
                } else {
                    if (temp.size() >= 3) { matches.addAll(temp); }
                    temp = new ArrayList<>();
                    temp.add(row.get(j));
                }
                if (j == row.size() - 1) {
                    if (temp.size() >= 3) { matches.addAll(temp); }
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
                if (temp.isEmpty() || board[temp.get(0)].getColor() == board[row.get(j)].getColor()){
                    temp.add(row.get(j));
                } else {
                    if (temp.size() >= 3) { matches.addAll(temp); }
                    temp = new ArrayList<>();
                    temp.add(row.get(j));
                }
                if (j == row.size() - 1) {
                    if (temp.size() >= 3) { matches.addAll(temp); }
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


}
