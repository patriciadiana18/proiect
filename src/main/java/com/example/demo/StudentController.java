package com.example.demo;

import java.util.List;

import com.example.demo.entity.Student;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class StudentController {
    private final StudentRepository repository;
    private final StudentModelAssembler modelAssembler;

    StudentController(StudentRepository repository, StudentModelAssembler modelAssembler){
        this.repository = repository;
        this.modelAssembler = modelAssembler;
    }

    @GetMapping("/students")
    CollectionModel<EntityModel<Student>> all(){
        List<EntityModel<Student>> Students = repository.findAll().stream()
                .map(modelAssembler::toModel).toList();
        return CollectionModel.of(Students,
                linkTo(methodOn(StudentController.class).all()).withSelfRel());
    }

    @PostMapping("/students")
    ResponseEntity<?> newStudent(@RequestBody Student newStudent){
        EntityModel<Student> entityModel =
                modelAssembler.toModel(repository.save(newStudent));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    @GetMapping("/students/{id}")
    EntityModel<Student> one(@PathVariable Long id){
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return modelAssembler.toModel(student);
    }


    @PutMapping("/students/{id}")
    ResponseEntity<?> replaceStudent(@RequestBody Student newStudent,@PathVariable Long id){
        Student updatedStudent = repository.findById(id)
                .map(Student -> {
                    Student.setAge(newStudent.getAge());
                    Student.setGrades(newStudent.getGrades());
                    Student.setFirstName(newStudent.getFirstName());
                    Student.setScholarship(newStudent.isScholarship());
                    Student.setLastName(newStudent.getLastName());
                    return repository.save(newStudent);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    return repository.save(newStudent);
                });
        EntityModel<Student> entityModel = modelAssembler.toModel(updatedStudent);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/students/{id}")
    ResponseEntity<?> deleteStudent(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
