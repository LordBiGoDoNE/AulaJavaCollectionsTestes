import config.ConexaoConfig;
import dao.FabricanteDAO;
import model.entity.Fabricante;
import service.FabricanteService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ConexaoConfig.iniciarBanco("localhost", "18745", "locadora", "postgres", "VrPost@Server");

        FabricanteService service = new FabricanteService();

        List<Fabricante> fabricantes = service.obterTodosFabricantes();

        if (fabricantes.isEmpty()) {
            service.inserirFabricante("KIA");
            service.inserirFabricante("VOLVO");
            service.inserirFabricante("TESLA");
            service.inserirFabricante("HYUNDAI");
            service.inserirFabricante("VOLKSWAGEN");
        }
    }

}
