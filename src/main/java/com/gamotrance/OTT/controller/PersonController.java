package com.gamotrance.OTT.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamotrance.OTT.Model.Person;
import com.gamotrance.OTT.services.PersonService;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "persons")
    public List<Person> findAll() {
        personService.addPerson();
        return personService.getAllPersons();
    }

}
