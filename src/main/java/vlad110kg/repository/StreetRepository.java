package vlad110kg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad110kg.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {

}
