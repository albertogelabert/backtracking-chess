
package practica3;

import javax.swing.ImageIcon;

/**
 * @author Fede & Alberto
 */
public class Alfil extends Peça{
    @Override
    public ImageIcon getImatge() {
        return new ImageIcon("imatges/alfil.png");
    }
    
    @Override
    public void mov(int[][] mov, int i, int j) {
        for (int x = i, y = j; x < mov.length && y < mov.length; x++, y++) {
            mov[x][y] = 1;
        }
        for (int x = i, y = j; x >= 0 && y >= 0; x--, y--) {
            mov[x][y] = 1;
        }
        for (int x = i, y = j; x >= 0 && y < mov.length; x--, y++) {
            mov[x][y] = 1;
        }
        for (int x = i, y = j; x < mov.length && y >= 0; x++, y--) {
            mov[x][y] = 1;
        }
        mov[i][j] = 2;
    }
    
}
