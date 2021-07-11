package com.phan.codechallenge.reece.controller;

import com.phan.codechallenge.reece.controller.bean.AddressBookRequest;
import com.phan.codechallenge.reece.service.AddressBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.concurrent.Callable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/v1/addressbook", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class AddressBookController {

    private final AddressBookService addressBookService;

    @PostMapping
    public Callable<ResponseEntity> createByUser(@RequestBody AddressBookRequest request) {
        return () -> {
            addressBookService.save(request.getUserName(), request.getBookName());
            return ResponseEntity.ok().build();
        };
    }

    @DeleteMapping
    public Callable<ResponseEntity> deleteByUser(@RequestBody AddressBookRequest request) {
        return () -> {
            addressBookService.delete(request.getUserName(), request.getBookName());
            return ResponseEntity.ok().build();
        };
    }

    @GetMapping(path = "/{user}")
    public Callable<ResponseEntity> retrieveByUser(@PathVariable @NotEmpty @Max(20) String user) {
        return () -> ResponseEntity.ok(addressBookService.getAddressBooks(user));
    }

}
