package com.example.rightCity.controller;

import com.example.rightCity.entity.ComplainEntity;
import com.example.rightCity.service.ComplainService;
import com.example.rightCity.message.ComplainMessage;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> addComplainByUserID(@RequestBody @NonNull ComplainEntity complain,
                                              @RequestParam @NonNull Long userId){
        try {
            complainService.addComplainByUserId(complain, userId);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ComplainMessage.COMPLAIN_ADDED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error!");
        }
    }


    @DeleteMapping("/{ID}")
    public ResponseEntity<?> deleteComplainById(@PathVariable @NonNull Long ID) {
        try{
            complainService.deleteComplainById(ID);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ComplainMessage.COMPLAIN_DELETED);

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Delete error");
        }
    }


    @GetMapping("/getComplainByID/{ID}")
    public ResponseEntity<?> getComplainById(@PathVariable @NonNull Long ID) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(complainService.getComplainById(ID));

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

