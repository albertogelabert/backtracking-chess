
package practica3;

import javax.swing.ImageIcon;

/**
 * @author Fede & Alberto
 */
public class Rei extends Peça{
    @Override
    public ImageIcon getImatge() {
        return new ImageIcon("imatges/rei.png");
    }
    
    @Override
    public void mov(int[][] mov, int i, int j) {
        try {
            mov[i][j + 1] = 1;
            mov[i + 1][j + 1] = 1;
            mov[i - 1][j + 1] = 1;
        } catch (Exception e) {
        }
        try {
            mov[i][j - 1] = 1;
            mov[i - 1][j - 1] = 1;
            mov[i + 1][j - 1] = 1;
        } catch (Exception e) {
        }
        try {
            mov[i - 1][j] = 1;
        } catch (Exception e) {
        }
        try {
            mov[i + 1][j] = 1;
        } catch (Exception e) {
        }
        mov[i][j] = 2;
    }
}
