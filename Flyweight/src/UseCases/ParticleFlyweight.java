package UseCases;

public class ParticleFlyweight {
    private String color;
    private String sprite;

    public ParticleFlyweight(String color, String sprite) {
        this.color = color;
        this.sprite = sprite;
    }

    public void render(int x, int y) {
        System.out.println("Rendering " + sprite + " at " + x + ", " + y + " with color " + color);
    }
}
