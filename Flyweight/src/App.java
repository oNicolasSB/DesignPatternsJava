import UseCases.FlyweightFactory;
import UseCases.ParticleContext;
import UseCases.ParticleFlyweight;

public class App {
    public static void main(String[] args) throws Exception {
        FlyweightFactory factory = new FlyweightFactory();

        // criar flyweights
        ParticleFlyweight bulletFlyweight = factory.getFlyweight("Red", "bullet.png");
        ParticleFlyweight missileFlyweight = factory.getFlyweight("Gray", "missile.png");

        // create contexts
        ParticleContext bullet1 = new ParticleContext(10, 20, bulletFlyweight);
        ParticleContext bullet2 = new ParticleContext(15, 25, bulletFlyweight);
        ParticleContext missile = new ParticleContext(5, 10, missileFlyweight);

        bullet1.render();
        bullet2.render();
        missile.render();
    }
}
