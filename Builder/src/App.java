import UseCases.Director;
import UseCases.House;
import UseCases.LuxuryHouseBuilder;
import UseCases.SimpleHouseBuilder;

public class App {
    public static void main(String[] args) throws Exception {
        Director director = new Director();

        // Build a simple house
        SimpleHouseBuilder simpleBuilder = new SimpleHouseBuilder();
        director.setBuilder(simpleBuilder);
        director.constructHouse();
        House simpleHouse = simpleBuilder.getResult();
        System.out.println("Simple House: " + simpleHouse);

        // Build a luxury house
        LuxuryHouseBuilder luxuryBuilder = new LuxuryHouseBuilder();
        director.setBuilder(luxuryBuilder);
        director.constructHouse();
        House luxuryHouse = luxuryBuilder.getResult();
        System.out.println("Luxury House: " + luxuryHouse);
    }
}
