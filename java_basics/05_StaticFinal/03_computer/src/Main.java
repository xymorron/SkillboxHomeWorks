import java.security.Key;

public class Main {

    public static void main(String[] args) {
        Cpu cpu1 = new Cpu(2000, 6, Cpu_manufacter.INTELL, 120);
        Ram ram1 = new Ram(RamType.DDR3, 2048, 30);
        Hdd hdd1 = new Hdd(HddType.HDD, 512, 200);
        Monitor monitor1 = new Monitor(24, Monitor_type.IPS, 2000);
        Keyboard keyboard1 = new Keyboard(KeyboardType.CHICLET, false, 300);
        Computer comp1 = new Computer("MySuperComp", ComputerVendor.ASUS ,cpu1,
                ram1, hdd1, monitor1, keyboard1);
        System.out.println(comp1);
        System.out.println("\nComputer's weight: " + comp1.getWeight());
        System.out.println("\nLet's upgrade our computer with a new memory and a new ssd! " +
                "And a new one keyboard!!!\n");
        Ram ram2 = new Ram(RamType.DDR3, 8192, 60);
        Hdd hdd2 = new Hdd(HddType.SSD, 1024, 100);
        Keyboard keyboard2 = new Keyboard(KeyboardType.CHICLET, true, 300);
        comp1.setRam(ram2);
        comp1.setHdd(hdd2);
        comp1.setKeyboard(keyboard2);
        System.out.println(comp1);
        System.out.println("\nComputer's new weight: " + comp1.getWeight());
    }
}
