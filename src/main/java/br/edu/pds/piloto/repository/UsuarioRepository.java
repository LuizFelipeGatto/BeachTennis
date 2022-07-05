package br.edu.pds.piloto.repository;

import br.edu.pds.piloto.dto.UsuarioDto;
import br.edu.pds.piloto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(nativeQuery = true, value = "SELECT u.id as id, u.nome as nome, u.cpf as cpf, u.telefone as telefone, u.naturalidade_id, u.senha FROM usuarios u INNER JOIN permissoes p ON u.id = p.usuario_id INNER JOIN roles r ON r.id = p.role_id where r.nome_Role = 'Usuario'")
    List<Usuario> findAllUsuarios();

    @Query(value = "SELECT COUNT(u.id) FROM usuarios as u WHERE u.naturalidade_id = :id", nativeQuery=true)
    Long findByCidade(@Param("id") Long id);
}
