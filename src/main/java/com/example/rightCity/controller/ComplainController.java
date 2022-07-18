package com.example.rightCity.controller;

import com.example.rightCity.entity.ComplainEntity;
import com.example.rightCity.service.ComplainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/complain")
public class ComplainController {

   private final ComplainService complainService;

    public ComplainController(ComplainService complainService) {
        this.complainService = complainService;
    }

    @PostMapping("/addComplainByID")
    public ResponseEntity addComplainByUserID(@RequestBody ComplainEntity complain,
                                              @RequestParam Long userId){
        try {
            complainService.addComplainByUserId(complain, userId);
            return ResponseEntity.ok("Complain added");
        } catch ( Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }
    }


    @DeleteMapping("/{ID}")
    public ResponseEntity deleteComplainById(@PathVariable Long ID){
        try{
            complainService.deleteComplainById(ID);
            return ResponseEntity.ok("Complain deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Delete error");
        }
    }


    @GetMapping("/getComplainByID/{ID}")
    public ResponseEntity getComplainById(@PathVariable long ID) {
        try {
            return ResponseEntity.ok(complainService.getComplainById(ID));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.notFound().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}

