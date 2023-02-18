package com.serviceworker.controller;

import com.serviceworker.dto.CategoryDto;
import com.serviceworker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
       CategoryDto saveCategory = categoryService.createCategory(categoryDto);
       return  new ResponseEntity<CategoryDto>(saveCategory, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
      List<CategoryDto> findAll =  categoryService.getAllCategory();
        return new ResponseEntity<List<CategoryDto>>(findAll,HttpStatus.OK);
    }

}
