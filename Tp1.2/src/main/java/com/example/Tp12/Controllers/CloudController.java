package com.example.Tp12.Controllers;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Tp12.Entities.VirtualMachine;
import com.example.Tp12.DTOs.CreateVmRequest;
import com.example.Tp12.DTOs.UserDTO;
import com.example.Tp12.Entities.User;
import com.example.Tp12.Services.UserService;
import com.example.Tp12.Services.VmService;

@RestController
@RequestMapping("/rsu")
@RequiredArgsConstructor
public class CloudController {

    private final VmService vmService;
    private final UserService userService;

    @GetMapping("/server/{idServer}/vms")
    public ResponseEntity<List<VirtualMachine>> getVmsByServer(@PathVariable Long idServer) {
        return ResponseEntity.ok(vmService.getVmsByServerId(idServer));
    }

    @PostMapping("/vm")
    public ResponseEntity<VirtualMachine> createVm(@RequestBody CreateVmRequest request) {
        VirtualMachine vm = vmService.createVm(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vm);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<User> patchUser(
            @PathVariable Long id,
            @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.patchUser(id, dto));
    }

    @DeleteMapping("/server/{idServer}/{idVm}")
    public ResponseEntity<Void> deleteVmFromServer(
            @PathVariable Long idServer,
            @PathVariable Long idVm) {
        vmService.deleteVmFromServer(idServer, idVm);
        return ResponseEntity.noContent().build();
    }
}
