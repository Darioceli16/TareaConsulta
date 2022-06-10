package ThreadModel;

public class RunThreads {
    public static void main(String[] args) throws InterruptedException {
        int[][] mat1Values = {
                {5, 8},
                {3, 7},
                {2, 4},
        };
        Matriz a = new Matriz(mat1Values);
        int[][] mat2Values = {
                {8,2,6},
                {7,5,3}
        };
        Matriz b = new Matriz(mat2Values);

        Matriz result = a.multiplyWithThreads(b);

        System.out.println(result);
    }

}




