package com.example.Tp12.Repositories;

import com.example.Tp12.Entities.User;
import com.example.Tp12.Projections.UserVmCountProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "users")
public interface UserRepo extends JpaRepository<User, Long> {
    
    List<User> findByEmailEndingWith(String domaine);
    
    @Query("SELECT u.id as idUser, COUNT(mv) as nombreVms " +
           "FROM User u LEFT JOIN u.machinesVirtualles mv " +
           "GROUP BY u.id")
    List<UserVmCountProjection> getUsersWithVmCount();
}