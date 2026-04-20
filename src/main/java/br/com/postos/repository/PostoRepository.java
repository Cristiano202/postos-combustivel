package br.com.postos.repository;

import br.com.postos.model.Posto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
// O segredo é este "extends JpaRepository". Ele substitui toda a pasta Impl!
public interface PostoRepository extends JpaRepository<Posto, String> {

    Optional<Posto> findByCnpj(String cnpj);
    List<Posto> findByBairroIgnoreCase(String bairro);
    List<Posto> findByCidadeIgnoreCase(String cidade);
    List<Posto> findByBandeiraIgnoreCase(String bandeira);
}