package ua.com.lineup.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.lineup.realestate.model.Impression;

@Repository
public interface ImpressionRepository extends JpaRepository<Impression, Long> {
}
