package aracar.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aracar.Models.Automovel;

public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
}
