
package practica3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Fede & Alberto
 */
public class Inici extends JFrame {

    private static Peça[][] colocades;
    private static JPanel[][] panells;
    private static int[][] Moren;
    private static int nPeçes;
    private static ArrayList<Peça> peçes;
    private int nxn;
    private static int NXN = 600;
    private static int[] camps;
    private int idx = 0;

    public static void main(String[] args) throws IOException {
        if (parametres()) {
            Inici lasPiezasQueNoSeMatan = new Inici(camps[0], camps[1], camps[2],
                    camps[3], camps[4], camps[5], camps[6], camps[7]);
            lasPiezasQueNoSeMatan.solucio();
        }
    }

    private static boolean parametres() {
        JTextField[] valors = new JTextField[8];
        String[] txt = {"Dimensions: ", "Nº Reines: ", "Nº Reis: ",
            "Nº Cavalls: ", "Nº Alfils: ", "Nº Torres: ", "Nº Bufons: ", "Nº Canyons: "};
        ArrayList<Object> message = new ArrayList();
        for (int i = 0; i < valors.length; i++) {
            message.add(txt[i]);
            valors[i] = new JTextField("0");
            if (i == 0) {
                valors[i].setText("8");
            }
            message.add(valors[i]);
        }
        camps = new int[8];
        int opc = -2;
        while (opc != JOptionPane.CANCEL_OPTION && opc != -1 && opc != JOptionPane.OK_OPTION) {
            opc = JOptionPane.showConfirmDialog(null, message.toArray(), "Completa el formulari", JOptionPane.OK_CANCEL_OPTION, 0, new ImageIcon("imatges/rei.png"));
            if (opc != JOptionPane.CANCEL_OPTION && opc != -1) {
                if (!permes(valors)) {
                    opc = -2;
                }
            }
        }
        return JOptionPane.OK_OPTION == opc;
    }

    private static boolean permes(JTextField[] valors) {
        try {
            for (int i = 0; i < valors.length; i++) {
                camps[i] = Integer.parseInt(valors[i].getText());
            }
            if (camps[0] == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Introdueixi noms majors o iguals que 0, y Dimensions ha de ser major a 0", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean resoldre_backtracking() {
        ArrayList<Integer[]> possibles = possiblesPosicions();
        int operadors = 0;
        while (nPeçes < peçes.size() && operadors < possibles.size()) {
            if (esPot(possibles.get(operadors)[0], possibles.get(operadors)[1])) {
                colocades[possibles.get(operadors)[0]][possibles.get(operadors)[1]] = peçes.get(nPeçes);
                noPossibles();
                nPeçes++;
                if (!resoldre_backtracking()) {
                    nPeçes--;
                    colocades[possibles.get(operadors)[0]][possibles.get(operadors)[1]] = null;
                    noPossibles();
                    operadors++;
                }
            } else {
                operadors++;
            }
        }
        return nPeçes == peçes.size();
    }

    private boolean esPot(int i, int j) {
        Peça pieza = peçes.get(nPeçes);
        int[][] mov = new int[Moren.length][Moren.length];
        pieza.mov(mov, i, j);
        for (int x = 0; x < Moren.length; x++) {
            for (int y = 0; y < Moren.length; y++) {
                if (Moren[x][y] == 2 && mov[x][y] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void noPossibles() {
        for (int i = 0; i < Moren.length; i++) {
            for (int j = 0; j < Moren.length; j++) {
                Moren[i][j] = 0;
            }
        }
        for (int i = 0; i < colocades.length; i++) {
            for (int j = 0; j < colocades.length; j++) {
                if (colocades[i][j] != null) {
                    colocades[i][j].mov(Moren, i, j);
                }
            }
        }
    }

    private ArrayList<Integer[]> possiblesPosicions() {
        ArrayList<Integer[]> posibles = new ArrayList();
        for (int i = 0; i < Moren.length; i++) {
            for (int j = 0; j < Moren.length; j++) {
                if (Moren[i][j] == 0) {
                    Integer[] a = {i, j};
                    posibles.add(a);
                }
            }
        }
        return posibles;
    }

    private void Dibuixar() {
        for (int i = 0; i < panells.length; i++) {
            for (int j = 0; j < panells.length; j++) {
                panells[i][j].removeAll();
                if (colocades[i][j] != null) {
                    ImageIcon fot = colocades[i][j].getImatge();
                    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(NXN / nxn, NXN / nxn, Image.SCALE_DEFAULT));
                    panells[i][j].add(new JLabel(icono));
                } else if (Moren[i][j] == 1) {
                    ImageIcon fot = new ImageIcon("imatges/mort.png");
                    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(NXN / nxn, NXN / nxn, Image.SCALE_DEFAULT));
                    panells[i][j].add(new JLabel(icono));
                }
            }
        }
    }

    public Inici(int n, int reines, int reis, int cavalls, int alfils, int torres, int bufons, int canyons) {
        super("Les peçes no es maten");
        setSize(NXN, NXN);
        this.setLocationRelativeTo(null);
        setLayout(new GridLayout(n, n));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panells = new JPanel[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                panells[i][j] = new JPanel(new GridLayout());
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        panells[i][j].setBackground(Color.white);
                    } else {
                        panells[i][j].setBackground(Color.black);
                    }
                } else {
                    if (j % 2 != 0) {
                        panells[i][j].setBackground(Color.white);
                    } else {
                        panells[i][j].setBackground(Color.black);
                    }
                }
                panells[i][j].addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e){
                       System.out.print(e.getComponent().getName());
                    
                    }
                    
});
                
                add(panells[i][j]);
            }
        }
        nxn = n;
        peçes = new ArrayList();
        colocades = new Peça[n][n];
        for (int i = 0; i < reines; i++) {
            peçes.add(new Reina());
        }
        for (int i = 0; i < reis; i++) {
            peçes.add(new Rei());
        }
        for (int i = 0; i < cavalls; i++) {
            peçes.add(new Cavall());
        }
        for (int i = 0; i < alfils; i++) {
            peçes.add(new Alfil());
        }
        for (int i = 0; i < torres; i++) {
            peçes.add(new Torre());
        }
        for (int i = 0; i < bufons; i++) {
            peçes.add(new Bufon());
        }
        for (int i = 0; i < canyons; i++) {
            peçes.add(new Canyon());
        }
        Moren = new int[n][n];
        for (int i = 0; i < Moren.length; i++) {
            for (int j = 0; j < Moren.length; j++) {
                Moren[i][j] = 0;
            }
        }
        this.setResizable(false);
    }

    public void solucio() {
        if (!resoldre_backtracking()) {
            JOptionPane.showMessageDialog(null, "No hi ha solució", "Sense solució", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } else {
            Dibuixar();
            this.setVisible(true);
        }
    }
}
