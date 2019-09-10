package ua.com.lineup.realestate.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.lineup.realestate.model.Apartment;
import ua.com.lineup.realestate.model.request.ApartmentRequest;
import ua.com.lineup.realestate.service.ApartmentService;

@RestController
@RequestMapping("/api/apartment")
public class ApartmentController {

    private ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Apartment createApartment(@RequestBody ApartmentRequest apartmentRequest) {
        return apartmentService.createApartment(apartmentRequest);
    }

    @PutMapping
    public Apartment update(@RequestBody ApartmentRequest request) {
        return apartmentService.update(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        apartmentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.")
    })
    public ResponseEntity<Page<Apartment>> findAll(Pageable pageable) {
        return ResponseEntity.ok(apartmentService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public Apartment findById(@PathVariable Long id) {
        return apartmentService.findById(id);
    }

}
