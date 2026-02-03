package com.example.Tp1.Controllers;
import com.example.Tp1.Entities.Account;
import com.example.Tp1.Repositories.AccountRepository;
import com.example.Tp1.Repositories.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/client-api")
@RequiredArgsConstructor
public class ClientController {

    private final AccountRepository _accountRepository;
    private final ClientRepository _clientRepository;  

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return _accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return _accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        if (account.getClient() == null || account.getClient().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        
        return _clientRepository.findById(account.getClient().getId())
            .map(existingClient -> {
                account.setClient(existingClient);  
                Account savedAccount = _accountRepository.save(account);
                return ResponseEntity.ok(savedAccount);
            })
            .orElse(ResponseEntity.notFound().build());  
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        return _accountRepository.findById(id).map(account -> {
            account.setLogin(accountDetails.getLogin());
            account.setPassword(accountDetails.getPassword());
            account.setRole(accountDetails.getRole());
            account.setActive(accountDetails.isActive());
            account.setCreationDate(accountDetails.getCreationDate());

            if (accountDetails.getClient() != null && accountDetails.getClient().getId() != null) {
                _clientRepository.findById(accountDetails.getClient().getId())
                    .ifPresent(account::setClient);
            }
            
            Account updatedAccount = _accountRepository.save(account);
            return ResponseEntity.ok(updatedAccount);
        }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        return _accountRepository.findById(id)
            .map(account -> {
                _accountRepository.delete(account);
                return ResponseEntity.ok().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}