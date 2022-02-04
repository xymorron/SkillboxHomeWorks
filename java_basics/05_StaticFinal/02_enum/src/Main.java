public class Main {
    public static void main(String[] args) {
        int operand1 = 3;
        int operand2 = 8;
        ArithmeticCalculator calculator = new ArithmeticCalculator(operand1, operand2);
        System.out.println("Operand1 " + operand1 + ", operand2 " + operand2);
        System.out.println("Addition: " + calculator.calculate(Operation.ADD));
        System.out.println("Subtraction: " + calculator.calculate(Operation.SUBTRACT));
        System.out.println("Multiplication: " + calculator.calculate(Operation.MULTIPLY));
    }
}
