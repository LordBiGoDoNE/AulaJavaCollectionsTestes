package dao;

import entity.Fabricante;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FabricanteDAOTest {

    FabricanteDAO dao = new FabricanteDAO();

    @BeforeAll
    static void initDB() {
        Conexao.iniciarBanco("localhost", "18745", "locadora", "postgres", "VrPost@Server");
    }

    @Test
    @Order(1)
    void testSelect() {
        Assertions.assertDoesNotThrow(() -> dao.select());
    }

    @Test
    @Order(3)
    void testSelectPorID() {
        Assertions.assertDoesNotThrow(() -> dao.select());

        Fabricante fabricante = dao.select(99999);

        Assertions.assertNotNull(fabricante);
    }

    @Test
    @Order(2)
    void insert() {
        Assertions.assertDoesNotThrow(() -> dao.insert(new Fabricante(99999, "TESTE")));
    }

    @Test
    @Order(4)
    void update() {
        Assertions.assertDoesNotThrow(() -> dao.update(new Fabricante(99999, "TESTE UODATE")));
    }

    @Test
    @Order(5)
    void delete() {
        Assertions.assertDoesNotThrow(() -> dao.delete(99999));
    }
}