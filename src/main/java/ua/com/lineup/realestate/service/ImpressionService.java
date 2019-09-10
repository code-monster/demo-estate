package ua.com.lineup.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.lineup.realestate.model.Apartment;
import ua.com.lineup.realestate.model.Impression;
import ua.com.lineup.realestate.model.User;
import ua.com.lineup.realestate.model.request.ImpressionRequest;
import ua.com.lineup.realestate.repository.ApartmentRepository;
import ua.com.lineup.realestate.repository.ImpressionRepository;
import ua.com.lineup.realestate.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class ImpressionService {

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final ImpressionRepository impressionRepository;


    @Autowired
    public ImpressionService(ApartmentRepository apartmentRepository, UserRepository userRepository, ImpressionRepository impressionRepository) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
        this.impressionRepository = impressionRepository;
    }

    public Impression create(ImpressionRequest impressionRequest) {

        User manager = null;
        if (impressionRequest.getManagerId() != null) {
            manager = userRepository.findById(impressionRequest.getManagerId()).orElse(null);
        }

        LocalDateTime created = LocalDateTime.now();

        Apartment apartment = null;
        if (impressionRequest.getApartmentId() != null) {
            apartment = apartmentRepository.findById(impressionRequest.getApartmentId()).orElse(null);
        }

        Impression impression = new Impression(apartment, manager, created, impressionRequest.getDateImpression(), impressionRequest.getResult());
        return impressionRepository.save(impression);
    }


    public Impression update(ImpressionRequest impressionRequest) {

        Impression impression = impressionRepository.findById(impressionRequest.getId()).orElse(null);
        if (impression == null) {
            return null;
        }

        User manager = null;
        if (impressionRequest.getManagerId() != null) {
            manager = userRepository.findById(impressionRequest.getManagerId()).orElse(null);
        }

        Apartment apartment = null;
        if (impressionRequest.getApartmentId() != null) {
            apartment = apartmentRepository.findById(impressionRequest.getApartmentId()).orElse(null);
        }

        impression.setApartment(apartment);
        impression.setManager(manager);
        impression.setDateImpression(impressionRequest.getDateImpression());
        impression.setResult(impressionRequest.getResult());

        return impressionRepository.save(impression);
    }

    public void deleteById(Long id) {
        impressionRepository.deleteById(id);
    }

    public Page<Impression> findAll(Pageable pageable) {
        return impressionRepository.findAll(pageable);
    }

    public Impression findById(Long id) {
        return impressionRepository.findById(id).orElse(null);
    }
}
