package UseCases;

import Abstractions.SubjectInterface;

public class RealSubject implements SubjectInterface {

    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }

}
