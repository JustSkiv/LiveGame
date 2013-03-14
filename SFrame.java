import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: s1212283
 * Date: 13.03.13
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
public class SFrame extends JFrame {
  final static boolean shouldFill = true;
  final static boolean shouldWeightX = true;
  final static boolean RIGHT_TO_LEFT = false;

  SFrame() {
    final Random generator = new Random();

    JPanel mainPanel = new JPanel();

    GridBagLayout gbl = new GridBagLayout();
    mainPanel.setLayout(gbl);

    GridBagConstraints c = new GridBagConstraints();

    c.anchor = GridBagConstraints.WEST;
    c.fill = GridBagConstraints.BOTH;
    c.gridheight = 1;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.gridx = GridBagConstraints.RELATIVE;
    c.gridy = GridBagConstraints.RELATIVE;
    c.insets = new Insets(0, 0, 0, 0);

    //c.weightx = 0.0;
    //c.weighty = 0.0;


    //Генерируем тестовую матрицу (для проверки поля)
    int[][] matrix;
    int mSize = 40;
    matrix = new int[mSize][mSize];

    for (int i = 0; i < mSize; i++) {
      for (int j = 0; j < mSize; j++) {
        matrix[i][j] = generator.nextInt(2);
      }
    }

    /*matrix[1][1] = 1;
    matrix[1][2] = 1;
    matrix[1][3] = 1;
    matrix[2][1] = 1;
    matrix[2][2] = 0;
    matrix[2][3] = 1;
    matrix[3][1] = 1;
    matrix[3][2] = 1;
    matrix[3][3] = 1;*/


    //создаем игровое поле
    final SField panel = new SField(matrix);

    //размеры ячейки для игрового поля
    c.ipady = panel.getFieldSize();
    c.ipadx = panel.getFieldSize();

    gbl.setConstraints(panel, c);

    mainPanel.add(panel);

    add(mainPanel);

    c.ipady = 0;
    c.ipadx = 0;
    c.gridwidth = 1;



    JButton generateButton = new JButton("Следующий шаг");
    gbl.setConstraints(generateButton, c);
    mainPanel.add(generateButton);

    generateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //Генерируем тестовую матрицу (для проверки поля)
        /*int[][] matrix;
        int mSize = 20;
        matrix = new int[mSize + 1][mSize + 1];

        for (int i = 0; i < mSize; i++) {
          for (int j = 0; j < mSize; j++) {
            matrix[i][j] = generator.nextInt(2);
          }
        }*/

        Controller controller = new Controller();

        int[][] newMatrix = controller.nextStep(panel.getMatrixConfiguration());

        panel.setMatrixConfiguration(newMatrix);
        panel.repaint();
      }
    });

    JButton exitButton = new JButton("Выход");
    gbl.setConstraints(exitButton, c);
    mainPanel.add(exitButton);
    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });



    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setSize(800, 600);
    pack();
    setTitle("Игра \"Жизнь\"");
    setVisible(true);
  }
}
