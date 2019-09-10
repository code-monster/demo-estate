package ua.com.lineup.realestate.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.lineup.realestate.model.Impression;
import ua.com.lineup.realestate.model.request.ImpressionRequest;
import ua.com.lineup.realestate.service.ImpressionService;

@RestController
@RequestMapping("/api/impression")
//@PreAuthorize("hasAuthority('ADMIN')")
public class ImpressionController {

    private ImpressionService impressionService;

    @Autowired
    public ImpressionController(ImpressionService impressionService) {
        this.impressionService = impressionService;
    }

    @PostMapping
    public Impression create(@RequestBody ImpressionRequest request) {
        return impressionService.create(request);
    }

    @PutMapping
    public Impression update(@RequestBody ImpressionRequest request) {
        return impressionService.update(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        impressionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.")
    })
    public ResponseEntity<Page<Impression>> findAll(Pageable pageable) {
        return ResponseEntity.ok(impressionService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public Impression findById(@PathVariable Long id) {
        return impressionService.findById(id);
    }
}
