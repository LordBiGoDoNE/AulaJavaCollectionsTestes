package dao;

import config.ConexaoConfig;
import model.entity.Fabricante;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FabricanteDAO implements BasicCrudDAO<Fabricante> {

    @Override
    public Fabricante select(int id) {
        String query = String.format("""
                SELECT * FROM fabricante WHERE id = %d;
                """, id);

        try (Statement stmt = ConexaoConfig.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                Fabricante fabricante = new Fabricante();
                fabricante.setId(rs.getInt("id"));
                fabricante.setNome(rs.getString("nome"));

                return fabricante;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Fabricante> select() {
        List<Fabricante> listaFabricante = new ArrayList<>();

        String query = """
                SELECT * FROM fabricante;
                """;

        try (Statement stmt = ConexaoConfig.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Fabricante fabricante = new Fabricante();
                fabricante.setId(rs.getInt("id"));
                fabricante.setNome(rs.getString("nome"));

                listaFabricante.add(fabricante);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaFabricante;
    }

    @Override
    public int insert(Fabricante entidade) {
        String query;

        if (entidade.getId() > 0) {
            query = String.format("""
                    INSERT INTO fabricante (id, nome) VALUES (%d,'%s');
                    """, entidade.getId(), entidade.getNome());
        } else {
            query = String.format("""
                    INSERT INTO fabricante (nome) VALUES ('%s');
                    """, entidade.getNome());
        }

        try (Statement stmt = ConexaoConfig.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insert(List<Fabricante> listaFabricante) {
        StringBuilder queryBuilder = new StringBuilder();

        for (Fabricante fabricante : listaFabricante) {
            queryBuilder.append(String.format("""
                    INSERT INTO fabricante (nome) VALUES ('%s');
                    """, fabricante.getNome()));
        }

        try (Statement stmt = ConexaoConfig.getConn().createStatement();) {
            return stmt.executeUpdate(queryBuilder.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Fabricante fabricanteAtualizar) {
        String query = String.format("""
                        UPDATE fabricante 
                         SET 
                           nome = '%s'
                         WHERE id= %d;
                        """, fabricanteAtualizar.getNome(),
                fabricanteAtualizar.getId());

        try (Statement stmt = ConexaoConfig.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        String query = String.format("""
                DELETE FROM fabricante
                 WHERE id= %d;
                """, id);

        try (Statement stmt = ConexaoConfig.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
