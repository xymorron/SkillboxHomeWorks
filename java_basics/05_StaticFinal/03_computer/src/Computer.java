public class Computer {
    private final String name;
    private final ComputerVendor vendor;
    private Cpu cpu;
    private Ram ram;
    private Hdd hdd;
    private Monitor monitor;
    private Keyboard keyboard;

    public String getName() {
        return name;
    }

    public ComputerVendor getVendor() {
        return vendor;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public Hdd getHdd() {
        return hdd;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public void setHdd(Hdd hdd) {
        this.hdd = hdd;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Computer(String name, ComputerVendor vendor, Cpu cpu, Ram ram, Hdd hdd, Monitor monitor, Keyboard keyboard) {
        this.name = name;
        this.vendor = vendor;
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
        this.monitor = monitor;
        this.keyboard = keyboard;
    }

    public int getWeight() {
        return cpu.getWeight() + ram.getWeight() + hdd.getWeight() +
                monitor.getWeight() + keyboard.getWeight();
    }

    @Override
    public String toString() {
        return "Computer spec:\n" +
                "\nName:\t\t" + name +
                "\nVendor:\t\t" + vendor +
                "\nCPU:\t\t" + cpu +
                "\nRAM:\t\t" + ram +
                "\nHDD:\t\t" + hdd +
                "\nMonitor:\t" + monitor +
                "\nKeyboard:\t" + keyboard;
    }
}
