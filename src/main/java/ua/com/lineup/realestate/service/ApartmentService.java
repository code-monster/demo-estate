package ua.com.lineup.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.lineup.realestate.model.Apartment;
import ua.com.lineup.realestate.model.User;
import ua.com.lineup.realestate.model.request.ApartmentRequest;
import ua.com.lineup.realestate.repository.ApartmentRepository;
import ua.com.lineup.realestate.repository.UserRepository;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;


    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, UserRepository userRepository) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
    }

    public Apartment createApartment(ApartmentRequest apartmentRequest) {

        User owner = null;
        if (apartmentRequest.getOwnerId() != null) {
            owner = userRepository.findById(apartmentRequest.getOwnerId()).orElse(null);
        }

        Apartment apartment = new Apartment(apartmentRequest.getAddress(),
                apartmentRequest.getRooms(),
                owner
        );
        return apartmentRepository.save(apartment);
    }

    public Apartment update(ApartmentRequest apartmentRequest) {

        Apartment apartment = apartmentRepository.findById(apartmentRequest.getId()).orElse(null);
        if (apartment == null) {
            return null;
        }
        User owner = null;
        if (apartmentRequest.getOwnerId() != null) {
            owner = userRepository.findById(apartmentRequest.getOwnerId()).orElse(null);
        }
        apartment.setOwner(owner);
        apartment.setAddress(apartmentRequest.getAddress());
        apartment.setRooms(apartmentRequest.getRooms());
        return apartmentRepository.save(apartment);
    }


    public void deleteById(Long id) {
        apartmentRepository.deleteById(id);
    }

    public Page<Apartment> findAll(Pageable pageable) {
        return apartmentRepository.findAll(pageable);
    }

    public Apartment findById(Long id) {
        return apartmentRepository.findById(id).orElse(null);
    }

}
