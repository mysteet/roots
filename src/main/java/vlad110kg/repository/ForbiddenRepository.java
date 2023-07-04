package vlad110kg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad110kg.entity.Forbidden;

import java.util.List;

@Repository
public interface ForbiddenRepository extends JpaRepository<Forbidden, Long> {

    List<Forbidden> findAllByKey(String key);
}
