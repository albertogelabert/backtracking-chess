
package practica3;

import javax.swing.ImageIcon;

/**
 * @author Fede & Alberto
 */
public class Cavall extends Peça {

    @Override
    public ImageIcon getImatge() {
        return new ImageIcon("imatges/cavall.png");
    }

    public void mov(int[][] mov, int i, int j) {
        if (i + 2 < mov.length && j + 1 < mov.length) {
            mov[i + 2][j + 1] = 1;
        }
        if (i + 2 < mov.length && j - 1 >= 0) {
            mov[i + 2][j - 1] = 1;
        }
        if (i - 2 >= 0 && j - 1 >= 0) {
            mov[i - 2][j - 1] = 1;
        }
        if (i - 2 >= 0 && j + 1 < mov.length) {
            mov[i - 2][j + 1] = 1;
        }
        if (i + 1 < mov.length && j + 2 < mov.length) {
            mov[i + 1][j + 2] = 1;
        }
        if (i - 1 >= 0 && j + 2 < mov.length) {
            mov[i - 1][j + 2] = 1;
        }
        if (i + 1 < mov.length && j - 2 >= 0) {
            mov[i + 1][j - 2] = 1;
        }
        if (i - 1 >= 0 && j - 2 >= 0) {
            mov[i - 1][j - 2] = 1;
        }
        mov[i][j] = 2;
    }
}
