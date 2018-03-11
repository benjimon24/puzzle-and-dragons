public class Orb {
    private int color;
    private boolean selected;
    private int posX;
    private int posY;

    public Orb(int color, int x, int y){
        this.color = color;
        this.posX = x;
        this.posY = y;
        this.selected = false;
    }

    public int getR() {
        int r = 0 ;

        switch (color) {
            case 0: r = 255; //red
                break;
            case 1: r = 0; //blue
                break;
            case 2: r = 60; //green
                break;
            case 3: r = 255; //yellow
                break;
            case 4: r = 125; //purple
                break;
            case 5: r = 255; //orange
                break;
        }
        return r;
    }

    public int getG() {
        int g = 0;
        switch (color) {
            case 0: g = 60;
                break;
            case 1: g = 100;
                break;
            case 2: g = 255;
                break;
            case 3: g = 255;
                break;
            case 4: g = 25;
                break;
            case 5: g = 150;
                break;
        }
        return g;
    }

    public int getB() {
        int b = 0;
        switch (color) {
            case 0: b=  60;
                break;
            case 1: b=  255;
                break;
            case 2: b=  60;
                break;
            case 3: b=  60;
                break;
            case 4: b=  255;
                break;
            case 5: b=  255;
                break;
        }
        return b;
    }

    public int getColor() {
        return color;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int poxY) {
        this.posY = poxY;
    }

    public boolean isSelected(){
        return selected;
    }

    public void setSelected(boolean select){
        this.selected = select;
    }

    @Override
    public String toString() {
        return color + " " + posX + " " + posY + " " + selected;
    }
}
