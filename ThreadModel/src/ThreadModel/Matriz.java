package ThreadModel;

import java.util.ArrayList;
import java.util.List;

public class Matriz {
    private int[][] values;

    public Matriz(int[][] values) {
        this.values = values;
    }
    public Matriz multiplyWithThreads(Matriz b) throws InterruptedException {
        List<TaskCalcElement> threads;

        if (values.length == b.values[0].length) {
            int[][] output = new int[values.length][b.values[0].length];
            threads = new ArrayList<>();

            for (var i = 0; i < output.length; i++) {
                for (var j = 0; j < output[0].length; j++) {
                    TaskCalcElement thread = new TaskCalcElement(this, b, i, j);
                    thread.start();
                    threads.add(thread);
                }
            }
            setWaitThreads(threads);
            for (var t : threads) {
                output[t.getRowIndex()][t.getColIndex()] = t.getElement();
            }
            return new Matriz(output);
        } else {
            throw new IllegalArgumentException("No es posible");
        }
    }
    private void setWaitThreads(List<TaskCalcElement> threads) throws InterruptedException {
        for(var t : threads) {
            t.join();
        }
    }
    public int[] getRow(int rowIndex) {
        if( rowIndex >= 0 && rowIndex < values.length) {
            return values[rowIndex];
        } else {
            throw new IllegalArgumentException("Indice no válido");
        }
    }

    public int[] getColumn(int colIndex) {
        int[] output = new int[values.length];
        if( colIndex < values[0].length)  {
            for (var i = 0; i < values.length; i ++) {
                output[i] = values[i][colIndex];
            }
        }
        return output;
    }

    public String toString() {
        String output = "";
        for (var row : values) {
            output += "{";
            for (var value : row) {
                output += value + "\t";
            }
            output += "}\n";
        }
        return "{\n" + output + "}";
    }
}




