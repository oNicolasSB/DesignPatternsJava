import java.util.ArrayList;
import java.util.List;

import Abstractions.Node;
import Abstractions.Visitor;
import UseCases.City;
import UseCases.Factory;
import UseCases.XmlExporterVisitor;

public class App {
    public static void main(String[] args) throws Exception {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(new City(1.0, 1.0));
        nodes.add(new City(2.0, 2.0));
        nodes.add(new City(3.0, 3.0));
        nodes.add(new City(4.0, 4.0));
        nodes.add(new City(5.0, 5.0));
        nodes.add(new City(6.0, 6.0));
        nodes.add(new Factory(7.0, 7.0));
        nodes.add(new Factory(8.0, 8.0));
        nodes.add(new Factory(9.0, 9.0));
        nodes.add(new Factory(10.0, 10.0));
        nodes.add(new Factory(11.0, 11.0));

        Visitor visitor = new XmlExporterVisitor();
        for (Node node : nodes) {
            node.accept(visitor);
        }

    }
}
