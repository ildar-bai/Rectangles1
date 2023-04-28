import java.util.Scanner;

public class Rectangles {

    public static void main(String[] args) {
        System.out.println("Введите количество прямоугольников");
        Scanner quantity = new Scanner(System.in);
        int n = quantity.nextInt();
        System.out.println("Введите координаты нижней левой и верхней правой вершины прямоугольников");
        System.out.println("Пример:");
        System.out.println("х1 у1 х2 у2");
        System.out.println("х3 у3 х4 у4");

        double[][] rectangles = getMatrix(n);

        //Находим прямоугольник который включает в себя все остальные прямоугольники
        double maxX = rectangles[0][0];
        double minX = rectangles[0][0];
        double maxY = rectangles[0][1];
        double minY = rectangles[0][1];

        for (int i = 0; i < n; i++) {
            if (rectangles[i][0] > maxX)
                maxX = rectangles[i][0];
            if (rectangles[i][2] > maxX)
                maxX = rectangles[i][2];
            if (rectangles[i][0] < minX)
                minX = rectangles[i][0];
            if (rectangles[i][2] < minX)
                minX = rectangles[i][2];
            if (rectangles[i][1] > maxY)
                maxY = rectangles[i][1];
            if (rectangles[i][3] > maxY)
                maxY = rectangles[i][3];
            if (rectangles[i][1] < minY)
                minY = rectangles[i][1];
            if (rectangles[i][3] < minY)
                minY = rectangles[i][3];
        }

        System.out.println("Введите шаг определяющий точность расчета площади");
        Scanner step = new Scanner(System.in);
        double e = step.nextDouble();

        // Проверяем все точки в большом прямоугольнике:
        double area = 0;
        int dotsX = (int)Math.ceil((maxX - minX) / e);
        int dotsY = (int)Math.ceil((maxY - minY) / e);

        for (int i = 0; i < dotsX; i++) {
            for (int j = 0; j < dotsY; j++) {
                if (inclusion(minX + e*i, minY + e*j, n, rectangles))
                          area = area + (e*e);
                }
            }
        System.out.println("Площадь = " + area);
        }

    static double[][] getMatrix(int n){
        double[][] array = new double[n][];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            array[i] = new double[4];
            for (int j = 0; j < 4; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        return array;
    }
    static boolean inclusion(double x, double y, int n, double[][] rectangles){
        for (int i = 0; i < n; i++) {
           if ((x >= rectangles[i][0])&&(x < rectangles[i][3])&&(y >= rectangles[i][1])&&(y < rectangles[i][3])) {
               return true;
           }
        }
        return false;
    }

}
