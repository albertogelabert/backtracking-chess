
package practica3;

import javax.swing.ImageIcon;

/**
 * @author Fede & Alberto
 */
public class Torre extends Peça {

    @Override
    public ImageIcon getImatge() {
        return new ImageIcon("imatges/torre.png");
    }

    @Override
    public void mov(int[][] mov, int i, int j) {
        for (int x = 0; x < mov.length; x++) {
            mov[x][j] = 1;
            mov[i][x] = 1;
        }
        mov[i][j] = 2;
    }
}
