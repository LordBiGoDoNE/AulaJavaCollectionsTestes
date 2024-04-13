package service;

import dao.FabricanteDAO;
import model.entity.Fabricante;

import java.util.List;

public class FabricanteService {

    FabricanteDAO fabricanteDAO;

    public FabricanteService() {
        fabricanteDAO = new FabricanteDAO();
    }

    public FabricanteService(FabricanteDAO fabricanteDAO) {
        this.fabricanteDAO = fabricanteDAO;
    }

    public List<Fabricante> obterTodosFabricantes() {
        //Toda Regra de negocio

        return fabricanteDAO.select();
    }

    public void inserirFabricante(String nome) {
        validarNome(nome);

        fabricanteDAO.insert(new Fabricante(nome));
    }

    private void validarNome(String nome){
        if (nome == null || nome.isBlank() || nome.contains("-")) {
            throw new RuntimeException("Nome invalido!");
        }
    }
}
