public class Dimension {


    private final int height;
    private final int width;
    private final int length;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public Dimension(int height, int width, int length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public Dimension setHeight(int height) {
        return new Dimension(height, width, length);
    }

    public Dimension setWidth(int width) {
        return new Dimension(height, width, length);
    }

    public Dimension setLength(int length) {
        return new Dimension(height, width, length);
    }

    public int volume() {
        return height * width * length;
    }

    public void print() {
        System.out.println("" + height + " x " + width + " x " + length);
    }
}
