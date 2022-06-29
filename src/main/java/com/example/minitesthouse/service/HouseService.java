package com.example.minitesthouse.service;

import com.example.minitesthouse.model.House;
import com.example.minitesthouse.repository.IHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class HouseService implements IHouseService{
    @Autowired
    IHouseRepository houseRepository;
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    public House save(House house) {
        return houseRepository.save(house);
    }

    public void remove(Long id) {
        houseRepository.deleteById(id);
    }
}
