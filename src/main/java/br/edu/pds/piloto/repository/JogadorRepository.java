package br.edu.pds.piloto.repository;

import br.edu.pds.piloto.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

}
