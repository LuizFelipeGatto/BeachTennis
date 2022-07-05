package br.edu.pds.piloto.repository;

import br.edu.pds.piloto.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CidadeRepositorio extends JpaRepository<Cidade, Long> {

}
