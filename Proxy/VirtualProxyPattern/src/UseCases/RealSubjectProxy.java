package UseCases;

import Abstractions.SubjectInterface;

public class RealSubjectProxy implements SubjectInterface {
    private SubjectInterface realSubject = new RealSubject();

    @Override
    public String greet(String name) {
        System.out.println("Proxy: Logging before greeting...");
        String result = realSubject.greet(name);
        System.out.println("Proxy: Logging after greeting...");
        return result;
    }

}
