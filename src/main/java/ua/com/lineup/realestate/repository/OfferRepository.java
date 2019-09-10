package ua.com.lineup.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.lineup.realestate.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
