package dao;


import config.ConexaoConfig;
import model.entity.Modelo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ModeloDAO implements BasicCrudDAO<Modelo> {

    @Override
    public Modelo select(int id) {
        String query = String.format("""
                       SELECT * FROM modelo WHERE id = %d;
                       """, id);

        try (Statement stmt = ConexaoConfig.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt("id"));
                modelo.setNome(rs.getString("nome"));
                modelo.setId_fabricante(rs.getInt("id_fabricante"));

                return modelo;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Modelo> select() {
        List<Modelo> listaModelo = new LinkedList<>();

        String query = """
                       SELECT * FROM modelo;
                       """;

        try (Statement stmt = ConexaoConfig.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt("id"));
                modelo.setNome(rs.getString("nome"));
                modelo.setId_fabricante(rs.getInt("id_fabricante"));

                listaModelo.add(modelo);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaModelo;
    }

    @Override
    public int insert(Modelo modelo) {
        String query = String.format("""
                       INSERT INTO modelo (nome, id_fabricante) VALUES ('%s', %d);
                       """, modelo.getNome(), modelo.getId_fabricante());

        try (Statement stmt = ConexaoConfig.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Modelo modeloAtualizar) {
        String query = String.format("""
                       UPDATE fabricante 
                        SET 
                          nome = '%s',
                          nome = '%s'
                        WHERE id= %d;
                       """,
                modeloAtualizar.getNome(),
                modeloAtualizar.getId_fabricante(),
                modeloAtualizar.getId());

        try (Statement stmt = ConexaoConfig.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        String query = String.format("""
                       DELETE FROM modelo
                        WHERE id= %d;
                       """, id);

        try (Statement stmt = ConexaoConfig.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
