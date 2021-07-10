package com.phan.codechallenge.reece.controller;

import com.phan.codechallenge.reece.service.AddressBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.concurrent.Callable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AddressBookController {

    private final AddressBookService addressBookService;

    public Callable<String> createByUsers() {
        throw new NotImplementedException();
    }

    public Callable<String> deleteByUsers() {
        throw new NotImplementedException();
    }

    public Callable<String> retrieveByUsers() {
        throw new NotImplementedException();
    }

}
