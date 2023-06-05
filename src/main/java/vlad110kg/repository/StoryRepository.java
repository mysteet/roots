package vlad110kg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad110kg.entity.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
}
