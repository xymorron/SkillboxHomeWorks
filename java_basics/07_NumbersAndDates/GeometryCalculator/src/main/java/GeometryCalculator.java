public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        return Math.PI * Math.pow(Math.abs(radius), 2);
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return Math.PI * Math.pow(Math.abs(radius), 3) * 4 / 3;
    }

    public static boolean isTrianglePossible(double a, double b, double c) {
        return (a < b + c && b < a +c && c < a + b);
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (isTrianglePossible(a, b, c)) {
            double semiperimeter = (a + b + c) / 2;
            return Math.sqrt(semiperimeter * (semiperimeter - a) *
                    (semiperimeter - b) * (semiperimeter - c));
        }
        return -1;
    }
}
