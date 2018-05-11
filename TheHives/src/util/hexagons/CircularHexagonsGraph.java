/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

import util.Matrix;
import util.MatrixElementFactory;
import util.Vector2i;

/**
 *
 * @author Thomas
 * @param <E>
 * @param <H>
 */
public class CircularHexagonsGraph<E, H extends Hexagon<E>> extends HexagonsGraph<E>
{

    Matrix<E> matrix;
    Matrix<H> hexagons;
    CircularPositionMaker maker;

    public CircularHexagonsGraph(Matrix<E> matrix, NeighborsShifter shifter, MatrixElementFactory<H> factory)
    {
        super();
        this.matrix = matrix;
        assert matrix.sizeX() % 2 == 0 && matrix.sizeY() % 2 == 0;

        Vector2i dim = matrix.getDimensions();

        this.maker = new CircularPositionMaker(dim);

        hexagons = new Matrix<>(dim.x, dim.y);
        hexagons.setAll(factory);

        for (int y = 0; y < dim.y; ++y)
        {
            for (int x = 0; x < dim.x; ++x)
            {
                Vector2i pos = new Vector2i(x, y);
                
                Hexagon<E> h = hexagons.getAt(pos);
                h.setValue(matrix.getAt(pos));

                for (HexagonSide side : HexagonSide.values())
                {
                    Vector2i neighbor_pos = shifter.shift(new Vector2i(pos), side);
                    h.setNeighbor(side, hexagons.getAt(maker.circular(neighbor_pos)));
                }
            }
        }
        setCenter(hexagons.getAt(dim.x / 2, dim.y / 2));
    }

    public H getHexagon(Vector2i pos)
    {
        return hexagons.getAt(maker.circular(pos));
    }
    
    public Matrix<E> getData()
    {
        return matrix;
    }

    @Override
    public String toString()
    {
        String res = "";
        res += toStringLine("  ___  ", "   ");
        res += "\n";
        for (int y = 0; y < matrix.getData().length; y++)
        {
            res += toStringLine(" /   \\ ", "   ");
            res += "\n";
            res += toStringThirdLine(y);
            res += "\n";
            res += toStringLine("\\     /", "   ");
            res += "\n";
            res += toStringFifthLine(y);
            res += "\n";
        }
        res += "     ";
        res += toStringLine("\\     / ", "  ");
        res += "\n";
        res += "      ";
        res += toStringLine("\\___/ ", "    ");

        return res;
    }

    private String toStringLine(String isCaseStr, String isNotCaseStr)
    {
        String res = "";
        boolean isCase = true;
        for (Object item : matrix.getData()[0])
        {
            res += isCase ? isCaseStr : isNotCaseStr;
            isCase = !isCase;
        }
        return res;
    }

    private String toStringThirdLine(int y)
    {
        String res = "";
        boolean isCase = true;
        for (Object item : matrix.getData()[y])
        {
            String str = item != null ? item.toString() : " ";
            res += isCase ? "/  " + str + "  \\" : "___";
            isCase = !isCase;
        }
        return res;
    }

    private String toStringFifthLine(int y)
    {
        String res = "";
        boolean isCase = true;
        for (Object item : matrix.getData()[y])
        {
            String str = item != null ? item.toString() : " ";
            res += isCase ? " \\___/ " : " " + str + " ";
            isCase = !isCase;
        }
        return res;
    }
}
