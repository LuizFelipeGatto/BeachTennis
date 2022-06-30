package br.edu.pds.piloto.repository;

import br.edu.pds.piloto.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
