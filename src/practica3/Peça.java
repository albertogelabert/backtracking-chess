package practica3;

import javax.swing.ImageIcon;

/**
 * @author Fede & Alberto
 */
public class Peça {

    public ImageIcon getImatge() {
        return new ImageIcon("imatges/mort.png");
    }

    public void mov(int[][] mov, int i, int j) {
        mov[i][j] = 2;
    }
}
