package br.edu.pds.piloto.repository;

import br.edu.pds.piloto.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepositorio extends JpaRepository<Cidade, Long> {

}
