package vlad110kg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad110kg.entity.Street;

import java.util.Optional;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {

    Optional<Street> findByTitle(String title);
}
