package UseCases;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private final Map<String, ParticleFlyweight> flyweights = new HashMap<>();;

    public ParticleFlyweight getFlyweight(String cor, String sprite) {
        String key = cor + "-" + sprite;

        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new ParticleFlyweight(cor, sprite));
        }
        return flyweights.get(key);
    }

}
