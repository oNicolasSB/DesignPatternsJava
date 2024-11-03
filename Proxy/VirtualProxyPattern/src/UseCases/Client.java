package UseCases;

import Abstractions.SubjectInterface;

public class Client {
    public void greet(String name) {
        SubjectInterface realSubjectProxy = new RealSubjectProxy();
        System.out.println(realSubjectProxy.greet(name));
    }
}
