package ua.com.lineup.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.lineup.realestate.model.Apartment;
import ua.com.lineup.realestate.model.User;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByOwner(User owner);
}
