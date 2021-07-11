package com.phan.codechallenge.reece.controller;

import com.phan.codechallenge.reece.controller.bean.ContactRequest;
import com.phan.codechallenge.reece.controller.bean.ContactResponse;
import com.phan.codechallenge.reece.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
// TODO version control & API toggle
@RequestMapping(path = "/v1/contact", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity addContact(@RequestBody ContactRequest request) {
        contactService.saveContact(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteByUser(@RequestBody ContactRequest request) {
        contactService.deleteContact(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{user}")
    public ResponseEntity<Set<ContactResponse>> retrieveByUser(@PathVariable @NotEmpty @Max(20) String user) {
        Set<ContactResponse> contracts = contactService.getContracts(user);
        return ResponseEntity.ok(contracts);
    }

}
