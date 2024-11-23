package UseCases;

import Abstractions.Visitor;

public class XmlExporterVisitor implements Visitor {

    public XmlExporterVisitor() {
    }

    @Override
    public void VisitCity(City city) {
        System.out.println("<Title> Visiting city with coords: </Title> \n<Point> \n <latitude> " +
                city.getLatitude() + " </latitude> \n <longitude> " + city.getLongitude() + " </longitude> \n</Point>");
    }

    @Override
    public void VisitFactory(Factory factory) {
        System.out.println("<Title> Visiting factory with coords: </Title> \n<Point> \n <latitude> " +
                factory.getLatitude() + " </latitude> \n <longitude> " + factory.getLongitude()
                + " </longitude> \n</Point>");
    }

}
