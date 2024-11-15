import java.util.Objects;

import Abstractions.PersonAdapter;
import Domain.LegalPerson;
import Domain.PhysicalPerson;

public class App {
    private static void validateRegister(PersonAdapter adapter) {
        if (Objects.isNull(adapter.getRegister())) {
            System.out.println("Registro não informado");
        } else {
            System.out.println(adapter.getRegister());
        }
    }

    public static void main(String[] args) throws Exception {
        PhysicalPerson person1 = new PhysicalPerson(null);
        LegalPerson person2 = new LegalPerson("58.028.008/0001-46");

        validateRegister(person2);
        validateRegister(person1);
    }
}
