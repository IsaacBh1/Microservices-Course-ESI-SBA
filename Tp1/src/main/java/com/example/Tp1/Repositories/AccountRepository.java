package com.example.Tp1.Repositories;

import com.example.Tp1.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Add custom query methods if needed
}