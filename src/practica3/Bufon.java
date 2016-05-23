
package practica3;

import javax.swing.ImageIcon;

/**
 * @author Fede & Alberto
 */
public class Bufon extends Peça {

    @Override
    public ImageIcon getImatge() {
        return new ImageIcon("imatges/bufon.png");
    }

    @Override
    public void mov(int[][] mov, int i, int j) {
        int x = i - 1;
        if (x > -1 && x < mov.length) {
            for (int y = 0; y < mov.length; y++) {
                if (y != j) {
                    mov[x][y] = 1;
                }
            }
        }
        x = i + 1;
        if (x > -1 && x < mov.length) {
            for (int y = 0; y < mov.length; y++) {
                if (y != j) {
                    mov[x][y] = 1;
                }
            }
        }
        x = j - 1;
        if (x > -1 && x < mov.length) {
            for (int y = 0; y < mov.length; y++) {
                if (y != i) {
                    mov[y][x] = 1;
                }
            }
        }
        x = j + 1;
        if (x > -1 && x < mov.length) {
            for (int y = 0; y < mov.length; y++) {
                if (y != i) {
                    mov[y][x] = 1;
                }
            }
        }
        mov[i][j] = 2;
    }
}
