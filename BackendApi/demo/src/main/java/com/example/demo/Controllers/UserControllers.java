package com.example.demo.Controllers;

import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/User" , method = {RequestMethod.POST,RequestMethod.GET})
public class UserControllers {

    @Autowired
    private UserRepo userRepository;
    @GetMapping("/findAllUserAdmin/{roles}")
    public ResponseEntity<?> findAllUserAdmin(@PathVariable int roles){
        var user = userRepository.findUserByRole(roles);
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/findUserBand")
    public ResponseEntity<?> findUserBand(){
        var user = userRepository.listUserBand();
        return  ResponseEntity.ok(user);
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if (userRepository.existsById(id)){
            userRepository.BandUser(id);
            return  ResponseEntity.ok("Band User Successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete not found");
        }
    }

    @GetMapping("/unlocked/{id}")
    public ResponseEntity<?> unlocked(@PathVariable Long id){
        if (userRepository.existsById(id)){
            userRepository.unlocked(id);
            return  ResponseEntity.ok("Unlocked User Successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete not found");
        }
    }

    @GetMapping("/checkUsername/{username}")
    public ResponseEntity<?> checkUsername(@PathVariable String username){
        if (userRepository.existsByUsername(username)){
            return ResponseEntity.ok(HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username not found");
        }
    }
}
