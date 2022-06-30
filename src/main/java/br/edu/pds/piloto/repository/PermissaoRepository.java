package br.edu.pds.piloto.repository;

import br.edu.pds.piloto.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
