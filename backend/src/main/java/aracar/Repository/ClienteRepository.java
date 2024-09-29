package aracar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aracar.Models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
