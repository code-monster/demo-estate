package ua.com.lineup.realestate.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.lineup.realestate.model.Offer;
import ua.com.lineup.realestate.model.request.OfferRequest;
import ua.com.lineup.realestate.service.OfferService;

@RestController
@RequestMapping("/api/offer")
public class OfferController {

    private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Offer createApartment(@RequestBody OfferRequest offerRequest) {
        return offerService.createOffer(offerRequest);
    }


    @PutMapping
    public Offer update(@RequestBody OfferRequest request) {
        return offerService.update(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        offerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.")
    })
    public ResponseEntity<Page<Offer>> findAll(Pageable pageable) {
        return ResponseEntity.ok(offerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public Offer findById(@PathVariable Long id) {
        return offerService.findById(id);
    }

}
