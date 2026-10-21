
package com.supportmanagement.service;

import com.supportmanagement.entity.User;
import com.supportmanagement.entity.UserTechnology;
import com.supportmanagement.repository.UserRepository;
import com.supportmanagement.repository.UserTechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTechnologyService {

    private final UserTechnologyRepository userTechnologyRepository;
    private final UserRepository userRepository;

    public UserTechnologyService(
            UserTechnologyRepository userTechnologyRepository,
            UserRepository userRepository) {
        this.userTechnologyRepository = userTechnologyRepository;
        this.userRepository = userRepository;
    }

    public UserTechnology addTechnology(Long userId, String technologyName) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with ID: " + userId));

        UserTechnology userTechnology = new UserTechnology();
        userTechnology.setUser(user);
        userTechnology.setTechnologyName(technologyName);

        return userTechnologyRepository.save(userTechnology);
    }

    public List<UserTechnology> getTechnologiesByUserId(Long userId) {
        return userTechnologyRepository.findByUserId(userId);
    }

    public void deleteTechnologiesByUserId(Long userId) {
        userTechnologyRepository.deleteByUserId(userId);
    }
}
