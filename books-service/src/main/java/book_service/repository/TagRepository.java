package book_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book_service.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    
}
