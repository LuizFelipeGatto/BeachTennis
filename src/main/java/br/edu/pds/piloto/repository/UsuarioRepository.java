package br.edu.pds.piloto.repository;

import br.edu.pds.piloto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
