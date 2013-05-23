package ua.vntu.edu.analysis;

import junit.framework.Assert;
import org.junit.Test;
import ua.edu.vntu.analysis.AnalysisMatrixBuilder;
import ua.edu.vntu.descriptions.Position;

/**
 * Created with IntelliJ IDEA.
 * User: Bychkovskyy
 * Date: 23.05.13
 * Time: 21:11
 */
public class GetFigurePowerTest {
    @Test
    public void test(){
        int [][] matrix = new int[8][8];
        int c = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = c;
                c++;
            }
        }


        AnalysisMatrixBuilder amb = new AnalysisMatrixBuilder();

        amb.print(matrix);

        Position p = new Position('c',8);

        Assert.assertTrue(amb.getPositionPower(p, matrix) == matrix[0][2]);
    }
}
