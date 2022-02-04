public class Elevator {
    private int minFloor, maxFloor;
    private int currentFloor= 1;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    private void moveDown() {
        if (getCurrentFloor() > minFloor) {
            currentFloor--;
        }
    }

    private void moveUp() {
        if (getCurrentFloor() < maxFloor) {
            currentFloor++;
        }
    }

    private boolean floorInRange(int floor) {
        if (floor > maxFloor || floor < minFloor) {
            System.out.println("There is no " + floor + " floor");
            return false;
        }
        return true;
    }

    public void move(int floor) {
        if (!floorInRange(floor)){
            return;
        }
        if (floor == getCurrentFloor()) {
            return;
        }
        System.out.println("Current floor is " + currentFloor);
        System.out.println("Doors are closing!");
        while (getCurrentFloor() != floor) {
            if (floor < getCurrentFloor()) {
                moveDown();
            } else {
                moveUp();
            }
            System.out.println("Current floor is " + currentFloor);
        }

        System.out.println("Doors are opening!");
    }
}
