import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: skiv
 * Date: 14.03.13
 * Time: 8:29
 * To change this template use File | Settings | File Templates.
 */
public class Controller {

  public int[][] nextStep(int[][] tMatrixConfiguration) {

    //test
    /*int test[][] = getSurround(1, 1, tMatrixConfiguration);
    int testC = getSurroundCount(1, 1, tMatrixConfiguration);
    int testCh = checkCell(1, 1, tMatrixConfiguration);

    System.out.println(Arrays.deepToString(test));
    System.out.println(testC);
    System.out.println(testCh);*/

    int[][] resultMatrixConfiguration = new int[tMatrixConfiguration.length][tMatrixConfiguration.length];

    for (int i = 0; i < tMatrixConfiguration.length; i++) {
      for (int j = 0; j < tMatrixConfiguration.length; j++) {
        resultMatrixConfiguration[i][j] = checkCell(i, j, tMatrixConfiguration);
      }
    }

    return resultMatrixConfiguration;
  }

  public int checkCell(int cellI, int cellJ, int[][] tMatrixConfiguration) {
    int surroundCount = getSurroundCount(cellI, cellJ, tMatrixConfiguration);

    int cell = tMatrixConfiguration[cellI][cellJ];
    int resultCell = 0;

    //если клетка "мертвая"
    if (cell == 0) {
      //жизнь зарождается:
      if (surroundCount == 3) resultCell = 1;
    }

    //если клетка "живая"
    if (cell > 0) {
      //клетка выживает
      if (surroundCount == 3 || surroundCount == 2) resultCell = cell;
    }

    //В остальных же случаях клетка умирает или остается мертвой

    return resultCell;
  }

  //Возвращает количество "соседей" указанной ячейки
  public int getSurroundCount(int cellI, int cellJ, int[][] tMatrixConfiguration) {
    int[][] surroundMatrix = getSurround(cellI, cellJ, tMatrixConfiguration);
    int surroundCount = 0;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (!(i == 1 && j == 1)) //не считать "себя"
          if (surroundMatrix[i][j] > 0) surroundCount++;
      }
    }

    return surroundCount;
  }

  //Функция для получения получения матрицы 3х3,
  //где центром является указанная ячейка, а остальные - ее окружение на поле
  public int[][] getSurround(int cellI, int cellJ, int[][] tMatrixConfiguration) {
    //та самая матрица, которая в итоге и будет результатом
    int[][] surroundMatrix = new int[3][3];

    //cellNumbers[0] - перечисление номеров столбцов
    //cellNumbers[1] - перечисление номеров строк
    int[][] cellNumbers = new int[2][3];

    int lastCellNumber = tMatrixConfiguration.length - 1;

    //Сие усложнение вызвано тем, что вселенная "тороидальная": если зайти за её правый край, окажешься на левом
    if (cellI == 0) {
      cellNumbers[0] = new int[]{lastCellNumber, cellI, cellI + 1};
    } else if (cellI == lastCellNumber) {
      cellNumbers[0] = new int[]{cellI - 1, cellI, 0};
    } else {
      cellNumbers[0] = new int[]{cellI - 1, cellI, cellI + 1};
    }

    //с верхом и низом то же самое. (см. предыдущий блок)
    if (cellJ == 0) {
      cellNumbers[1] = new int[]{lastCellNumber, cellJ, cellJ + 1};
    } else if (cellJ == lastCellNumber) {
      cellNumbers[1] = new int[]{cellJ - 1, cellJ, 0};
    } else {
      cellNumbers[1] = new int[]{cellJ - 1, cellJ, cellJ + 1};
    }

    //заполняем результирующую матрицу
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int currentLineNumber = cellNumbers[1][i];
        int currentRowNumber = cellNumbers[0][j];
        surroundMatrix[i][j] = tMatrixConfiguration[currentRowNumber][currentLineNumber];
      }
    }

    return surroundMatrix;
  }

}
