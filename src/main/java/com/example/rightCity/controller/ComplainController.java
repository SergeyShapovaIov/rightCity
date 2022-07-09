package com.example.rightCity.controller;

import com.example.rightCity.entity.ComplainEntity;
import com.example.rightCity.service.ComplainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complain")
public class ComplainController {

   private final ComplainService complainService;

    public ComplainController(ComplainService complainService) {
        this.complainService = complainService;
    }

    @PostMapping("/addComplainByID")
    public ResponseEntity addComplainByID(@RequestBody ComplainEntity complain,
                                      @RequestParam Long userId){
        try {
            complainService.addComplainByUserID(complain,userId);
            return ResponseEntity.ok("Complain added");
        } catch ( Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }
    }

}

