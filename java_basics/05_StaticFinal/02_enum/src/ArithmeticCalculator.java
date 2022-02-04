public class ArithmeticCalculator {
    private int operand1, operand2;

    public ArithmeticCalculator(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public int calculate(Operation operation) {
        if (operation == Operation.ADD) {
            return operand1 +operand2;
        }
        if (operation == Operation.SUBTRACT) {
            return operand1 - operand2;
        }
        if (operation == Operation.MULTIPLY) {
            return operand1 * operand2;
        }
        System.out.println("Error!");
        return 0;
    }
}
