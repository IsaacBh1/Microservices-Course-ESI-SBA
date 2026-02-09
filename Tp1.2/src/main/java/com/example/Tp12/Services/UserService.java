package com.example.Tp12.Services;

import com.example.Tp12.DTOs.UserDTO;
import com.example.Tp12.Entities.User;
import com.example.Tp12.Repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepo UserRepository;
    
    @Transactional
    public User updateUser(Long id, UserDTO dto) {
        User User = UserRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User non trouvé"));
        
        User.setNom(dto.getNom());
        User.setEmail(dto.getEmail());
        
        return UserRepository.save(User);
    }
    
    @Transactional
    public User patchUser(Long id, UserDTO dto) {
        User User = UserRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User non trouvé"));
        
        if (dto.getNom() != null) {
            User.setNom(dto.getNom());
        }
        if (dto.getEmail() != null) {
            User.setEmail(dto.getEmail());
        }
        
        return UserRepository.save(User);
    }
}