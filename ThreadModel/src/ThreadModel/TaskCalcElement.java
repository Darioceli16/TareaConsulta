package ThreadModel;

public class TaskCalcElement extends Thread {
    private Matriz a;
    private Matriz b;
    private int row;
    private int col;
    private int x;

    public TaskCalcElement(Matriz m1, Matriz m2, int rowIndex, int colIndex) {
        this.a = a;
        this.b = b;
        this.row = row;
        this.col = col;
    }

    @Override
    public void  run() {

        x = calValue(a.getRow(row), b.getColumn(col));
    }

    private int calValue (int[] row, int[] col) {
        int aux = 0;
        for(var i = 0; i < row.length; i++) {
            aux += row[i] * col[i];
        }
        return aux;
    }
    public int getElement() {
        return x;
    }
    public int getRowIndex() {
        return row;
    }
    public int getColIndex() {
        return col;
    }
}
