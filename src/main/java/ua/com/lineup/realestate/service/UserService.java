package ua.com.lineup.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.lineup.realestate.exception.AppException;
import ua.com.lineup.realestate.model.Role;
import ua.com.lineup.realestate.model.User;
import ua.com.lineup.realestate.model.request.SignUpRequest;
import ua.com.lineup.realestate.repository.RoleRepository;
import ua.com.lineup.realestate.repository.UserRepository;
import ua.com.lineup.realestate.security.UserPrincipal;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getCurrentUser(UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId()).orElse(null);
    }

    public User update(SignUpRequest request) {

        User user = null;
        if (request.getId() != null) {
            user = userRepository.findById(request.getId()).orElse(null);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new AppException("User Role not set. Add default roles to database."));

        user.setRole(userRole);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
