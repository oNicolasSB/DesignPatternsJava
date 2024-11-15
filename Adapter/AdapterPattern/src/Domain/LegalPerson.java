package Domain;

import Abstractions.PersonAdapter;

public class LegalPerson implements PersonAdapter {
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public LegalPerson(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getRegister() {
        return getCnpj();
    }
}
