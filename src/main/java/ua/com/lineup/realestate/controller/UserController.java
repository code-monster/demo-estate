package ua.com.lineup.realestate.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.lineup.realestate.model.User;
import ua.com.lineup.realestate.model.request.SignUpRequest;
import ua.com.lineup.realestate.security.CurrentUser;
import ua.com.lineup.realestate.security.UserPrincipal;
import ua.com.lineup.realestate.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me/")
    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    public User getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return userService.getCurrentUser(currentUser);
    }

    @PutMapping
    public User update(@RequestBody SignUpRequest request) {
        return userService.update(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.")
    })
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/id/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

}
