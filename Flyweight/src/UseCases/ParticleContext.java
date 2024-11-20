package UseCases;

public class ParticleContext {
    private int x;
    private int y;
    private ParticleFlyweight flyweight;

    public ParticleContext(int x, int y, ParticleFlyweight flyweight) {
        this.x = x;
        this.y = y;
        this.flyweight = flyweight;
    }

    public void render() {
        flyweight.render(x, y);
    }
}
