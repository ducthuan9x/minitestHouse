package com.example.minitesthouse.controller;

import com.example.minitesthouse.model.House;
import com.example.minitesthouse.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD4;
import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/houses")
@CrossOrigin("*")
public class HouseController {
    @Autowired
    IHouseService houseService;
    @GetMapping
    public ResponseEntity<Page<House>> findAllHouse(@PageableDefault(value = 3) Pageable pageable) {
        Page<House> houses =  houseService.findAll(pageable);
        if (houses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

//    public ResponseEntity<Page<House>> findAllHouse(@PageableDefault(value = 2) Pageable pageable) {
//        return  new ResponseEntity<>(houseService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<House> findHouseById(@PathVariable Long id) {
        Optional<House> houseOptional =houseService.findById(id);
        if (!houseOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(houseOptional.get(), HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    @PostMapping()
    public ResponseEntity<House>upfile(@RequestParam("file")MultipartFile file,House house){
        String fileName=file.getOriginalFilename();
        house.setImg(fileName);
        try{
        file.transferTo(new File("E:\\MD4\\miniTestHouse\\src\\main\\resources\\image\\"+fileName));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(houseService.save(house), HttpStatus.CREATED);
    }
}
