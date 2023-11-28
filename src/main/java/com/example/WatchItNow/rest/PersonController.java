package com.example.WatchItNow.rest;

import com.example.WatchItNow.dto.BaseDTO;
import com.example.WatchItNow.dto.PersonDTO;
import com.example.WatchItNow.models.Person;
import com.example.WatchItNow.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/people")
@RestController
public class PersonController {

    private PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    private PersonController(PersonService personService, ModelMapper modelMapper){
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> getPeople(){
        List<Person> people = personService.findAll();
        return people
                .stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    private PersonDTO convertToPersonDTO(Person person) {
        final PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        return personDTO;
    }

    @GetMapping("/{personId}")
    public PersonDTO getPerson(@PathVariable(name = "personId") long personId){
        Optional<Person> optionalPerson = personService.getEntity(personId);
        return optionalPerson.map(this::convertToPersonDTO).orElse(null);
    }


    @PostMapping()
    public BaseDTO<Person> create(@RequestBody PersonDTO newPerson){
        Person person = converPersonDTOtoModel(newPerson);
        return convertToPersonDTO(personService.create(person));
    }

    private Person converPersonDTOtoModel(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        return person;
    }

    @PutMapping()
    public BaseDTO<Person> update(@RequestBody PersonDTO updatedPerson){
        Person person = converPersonDTOtoModel(updatedPerson);
        return convertToPersonDTO(personService.update(person));
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<String> delete(@PathVariable long personId){
        boolean isRemoved = personService.remove(personId);
        String deletedMessage = "Person with id: " + personId + " was deleted";
        String notDeletedMessage = "Person with id: " + personId + " does not exist";
        return isRemoved? new ResponseEntity(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage,HttpStatusCode.valueOf(404));
    }

}
