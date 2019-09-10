package ua.com.lineup.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.lineup.realestate.model.Apartment;
import ua.com.lineup.realestate.model.Offer;
import ua.com.lineup.realestate.model.User;
import ua.com.lineup.realestate.model.request.OfferRequest;
import ua.com.lineup.realestate.repository.ApartmentRepository;
import ua.com.lineup.realestate.repository.OfferRepository;
import ua.com.lineup.realestate.repository.UserRepository;

@Service
public class OfferService {

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;


    @Autowired
    public OfferService(ApartmentRepository apartmentRepository, UserRepository userRepository, OfferRepository offerRepository) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
    }

    public Offer createOffer(OfferRequest offerRequest) {

        User client = null;
        if (offerRequest.getClientId() != null) {
            client = userRepository.findById(offerRequest.getClientId()).orElse(null);
        }

        User realtor = null;
        if (offerRequest.getRealtorId() != null) {
            realtor = userRepository.findById(offerRequest.getRealtorId()).orElse(null);
        }

        Apartment apartment = null;
        if (offerRequest.getApartmentId() != null) {
            apartment = apartmentRepository.findById(offerRequest.getApartmentId()).orElse(null);
        }

        Offer offer = new Offer(apartment,
                offerRequest.getPrice(),
                offerRequest.getOfferType(),
                offerRequest.getDescription(),
                realtor,
                client,
                offerRequest.isFinished()
        );
        return offerRepository.save(offer);
    }

    public Offer update(OfferRequest offerRequest) {

        Offer offer = offerRepository.findById(offerRequest.getId()).orElse(null);
        if (offer == null) {
            return null;
        }

        User client = null;
        if (offerRequest.getClientId() != null) {
            client = userRepository.findById(offerRequest.getClientId()).orElse(null);
        }

        User realtor = null;
        if (offerRequest.getRealtorId() != null) {
            realtor = userRepository.findById(offerRequest.getRealtorId()).orElse(null);
        }

        Apartment apartment = null;
        if (offerRequest.getApartmentId() != null) {
            apartment = apartmentRepository.findById(offerRequest.getApartmentId()).orElse(null);
        }

        offer.setApartment(apartment);
        offer.setClient(client);
        offer.setRealtor(realtor);
        offer.setFinished(offerRequest.isFinished());
        offer.setOfferType(offerRequest.getOfferType());
        offer.setDescription(offerRequest.getDescription());
        offer.setPrice(offerRequest.getPrice());
        return offerRepository.save(offer);
    }

    public void deleteById(Long id) {
        offerRepository.deleteById(id);
    }

    public Page<Offer> findAll(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    public Offer findById(Long id) {
        return offerRepository.findById(id).orElse(null);
    }
}
