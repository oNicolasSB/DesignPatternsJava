package Domain;

import Abstractions.PersonAdapter;

public class PhysicalPerson implements PersonAdapter {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public PhysicalPerson(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getRegister() {
        return getCpf();
    }
}
