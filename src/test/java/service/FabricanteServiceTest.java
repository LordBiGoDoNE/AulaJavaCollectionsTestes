package service;

import config.ConexaoConfig;
import dao.FabricanteDAO;
import model.entity.Fabricante;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class FabricanteServiceTest {

    FabricanteDAO daoMock = mock(FabricanteDAO.class);

    FabricanteService service = new FabricanteService(daoMock);

    @BeforeEach
    void inicializacao() {
        System.out.println("INICIANDO TESTE");
    }

    @AfterEach
    void finalizacao() {
        System.out.println("FINALIZANDO TESTE");
    }

    @Test
    void testeContrutores() {
        Assertions.assertDoesNotThrow(() -> {
            new FabricanteService();
        });
    }

    @Test
    void obterTodosFabricantes() {
        when(daoMock.select()).thenReturn(List.of(new Fabricante("Rafael")));

        List<Fabricante> fabricantes = service.obterTodosFabricantes();

        Assertions.assertFalse(fabricantes.isEmpty());
    }

    @Test
    void inserirFabricanteComNomeValido() {
        when(daoMock.insert(Mockito.any(Fabricante.class))).thenReturn(0);

        Assertions.assertDoesNotThrow(() -> {
            service.inserirFabricante("Rafael");
        });
    }

    @Test
    void inserirFabricanteComNomeInvalido() {
        when(daoMock.insert(Mockito.any(Fabricante.class))).thenReturn(0);

        Assertions.assertThrows(RuntimeException.class, () -> {
            service.inserirFabricante(null);
        });

        Assertions.assertThrows(RuntimeException.class, () -> {
            service.inserirFabricante("   ");
        });

        Assertions.assertThrows(RuntimeException.class, () -> {
            service.inserirFabricante("Raf-ael");
        });
    }
}