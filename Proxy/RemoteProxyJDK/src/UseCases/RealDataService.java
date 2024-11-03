package UseCases;

import Abstractions.DataService;

public class RealDataService implements DataService {
    @Override
    public String fetchData() {
        return "Data from remote server";
    }
}
