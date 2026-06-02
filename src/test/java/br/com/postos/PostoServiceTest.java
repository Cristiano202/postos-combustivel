package br.com.postos;

import br.com.postos.model.Posto;
import br.com.postos.repository.PostoRepository;
import br.com.postos.service.PostoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify; // USE ESTE
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class) // Avisa que vamos usar Mocks
public class PostoServiceTest {

    @Mock
    private PostoRepository postoRepository; // Cria o "repositório de mentira"

    @InjectMocks
    private PostoService postoService; // Injeta o mock dentro da sua Service real

    @Test
    @DisplayName("Deve salvar um posto com sucesso e gerar ID de 6 dígitos")
    void SalvarPostoComSucesso() {
        Posto posto = new Posto();
        posto.setNomeFantasia("Posto Teste");
        posto.setCnpj("12345678000199");

        when(postoRepository.save(any(Posto.class))).thenReturn(posto);

        Posto postoSalvo = postoService.salvar(posto);
        assertNotNull(postoSalvo);
        assertEquals("Posto Teste", postoSalvo.getNomeFantasia());

        // Verifica se o repositório foi chamado e 1 vez
        verify(postoRepository, times(1)).save(any(Posto.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar cadastrar posto com CNPJ já existente")
    void deveLancarExcecaoQuandoCnpjJaExistir() {

        Posto postoExistente = new Posto();
        postoExistente.setId("123456");
        postoExistente.setCnpj("12345678000199");
        postoExistente.setNomeFantasia("Posto Antigo");

        // Novo posto tentando se cadastrar com o mesmo CNPJ (ID dele é null)
        Posto postoNovo = new Posto();
        postoNovo.setCnpj("12345678000199");
        postoNovo.setNomeFantasia("Posto Novo");

        // Configura o Mockito para simular que achou o CNPJ no banco
        when(postoRepository.findByCnpj("12345678000199")).thenReturn(Optional.of(postoExistente));

        //  joga RuntimeException
        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            postoService.salvar(postoNovo);
        });

        // Valida se o metodo save NUNCA foi chamado
        verify(postoRepository, times(0)).save(any(Posto.class));
    }


}