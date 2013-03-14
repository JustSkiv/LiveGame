import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created with IntelliJ IDEA.
 * User: s1212283
 * Date: 13.03.13
 * Time: 13:12
 * To change this template use File | Settings | File Templates.
 */
public class SField extends JPanel {
  final int CELL_SIZE = 10;
  int[][] matrixConfiguration;
  Graphics2D g2;

  public SField(int[][] tMatrixConfiguration) {
    setMatrixConfiguration(tMatrixConfiguration);
  }

  public void setMatrixConfiguration(int[][] tMatrixConfiguration) {
    matrixConfiguration = tMatrixConfiguration;
  }

  public int[][] getMatrixConfiguration() {
    return matrixConfiguration;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g2 = (Graphics2D) g;

    // очищаем фон
    Rectangle r = getBounds();
    g2.setBackground(Color.black);
    g2.clearRect(0, 0, r.width, r.height);

    // очищаем фон
    Rectangle r2 = getBounds();
    //g2.clearRect(0, 0, r2.width, r2.height);

    for (int i = 0; i < matrixConfiguration.length; i++) {
      for (int j = 0; j < matrixConfiguration.length; j++) {
        if (matrixConfiguration[i][j] > 0) {
          g.setColor(Color.blue);
          g.fillRect(i * (CELL_SIZE + 1), j * (CELL_SIZE + 1), CELL_SIZE, CELL_SIZE);
        }
      }
    }

  }

  public int getFieldSize() {
    int size = matrixConfiguration.length * (CELL_SIZE + 1);
    return size;
  }
}
