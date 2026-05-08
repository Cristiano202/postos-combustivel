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

import static org.mockito.Mockito.verify; // USE ESTE
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void deveSalvarPostoComSucesso() {

        Posto posto = new Posto();
        posto.setNomeFantasia("Posto Teste");
        posto.setCnpj("12345678000199");


        when(postoRepository.save(any(Posto.class))).thenReturn(posto);


        Posto postoSalvo = postoService.salvar(posto);

        // 3. THEN (Então verificamos se deu certo)
        assertNotNull(postoSalvo);
        assertEquals("Posto Teste", postoSalvo.getNomeFantasia());

        // Verifica se o repositório foi chamado exatamente 1 vez
        verify(postoRepository, times(1)).save(any(Posto.class));
    }
}