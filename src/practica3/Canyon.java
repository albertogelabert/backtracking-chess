
package practica3;

import javax.swing.ImageIcon;

/**
 * @author Fede & Alberto
 */
public class Canyon extends Peça {
    @Override
    public ImageIcon getImatge() {
        return new ImageIcon("imatges/canyon.png");
    }
    
    @Override
    public void mov(int[][] mov, int i, int j) {
        for (int x = 0; x < mov.length; x++) {
            mov[x][j] = 1;
            mov[i][x] = 1;
        }
        int x = i - 1;
        if (x > -1 && x < mov.length) {
            for (int y = 0; y < mov.length; y++) {
                mov[x][y] = 1;
            }
        }
        x = i + 1;
        if (x > -1 && x < mov.length) {
            for (int y = 0; y < mov.length; y++) {
                mov[x][y] = 1;
            }
        }
        x = j - 1;
        if (x > -1 && x < mov.length) {
            for (int y = 0; y < mov.length; y++) {
                mov[y][x] = 1;
            }
        }
        x = j + 1;
        if (x > -1 && x < mov.length) {
            for (int y = 0; y < mov.length; y++) {
                mov[y][x] = 1;
            }
        }
        mov[i][j] = 2;
    }
}
