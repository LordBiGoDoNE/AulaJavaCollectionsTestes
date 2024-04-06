package org.example;

import dao.Conexao;
import dao.FabricanteDAO;
import entity.Fabricante;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Conexao.iniciarBanco("localhost", "18745", "locadora", "postgres", "VrPost@Server");

        FabricanteDAO fabricanteDAO = new FabricanteDAO();

        List<Fabricante> fabricantesBanco = fabricanteDAO.select();

        if (fabricantesBanco.isEmpty()) {
            fabricantesBanco.add(new Fabricante("KIA"));
            fabricantesBanco.add(new Fabricante("VOLVO"));
            fabricantesBanco.add(new Fabricante("TESLA"));
            fabricantesBanco.add(new Fabricante("HYUNDAI"));
            fabricantesBanco.add(new Fabricante("VOLKSWAGEN"));

            fabricanteDAO.insert(fabricantesBanco);

            return;
        }

        fabricantesBanco.forEach(fabricante -> System.out.println(fabricante.getNome()));

        Map<Double, Fabricante> mapaFabricantes = new HashMap<>();

        fabricantesBanco.forEach(fabricante -> {
            mapaFabricantes.put(Math.random(), fabricante);
        });

        Fabricante fabricante = mapaFabricantes.get(999);

        if (fabricante == null) {
            System.out.println("Fabricante Inexistente");
        } else {
            System.out.println(fabricante.toString());
        }

        Fabricante fabricanteHenry = new Fabricante("Henry");
        fabricanteHenry.setId(999);

        Fabricante fabricante3 = new Fabricante("Rafael");
        fabricante3.setId(999);

        Map<Integer, Fabricante> map3 = new HashMap<>();
        map3.put(fabricante3.getId(), fabricante3);

        map3.merge(fabricanteHenry.getId(), fabricanteHenry, (v1, v2) -> new Fabricante(v1.getNome()));

        System.out.println("FIM");
    }

}
