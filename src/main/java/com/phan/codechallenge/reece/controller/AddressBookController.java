package com.phan.codechallenge.reece.controller;

import com.phan.codechallenge.reece.controller.bean.AddressBookRequest;
import com.phan.codechallenge.reece.service.AddressBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/v1/addressbook", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class AddressBookController {

    private final AddressBookService addressBookService;

    @PostMapping
    public ResponseEntity createByUser(@RequestBody AddressBookRequest request) {
        addressBookService.save(request.getUserName(), request.getBookName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteByUser(@RequestBody AddressBookRequest request) {
        addressBookService.delete(request.getUserName(), request.getBookName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{user}")
    public ResponseEntity retrieveByUser(@PathVariable @NotEmpty @Max(20) String user) {
        return ResponseEntity.ok(addressBookService.getAddressBooks(user));
    }

}
