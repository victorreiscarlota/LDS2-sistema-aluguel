package aracar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aracar.Models.Credito;


public interface CreditoRepository extends JpaRepository<Credito, Long> {
}
