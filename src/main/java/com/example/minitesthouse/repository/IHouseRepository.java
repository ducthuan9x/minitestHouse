package com.example.minitesthouse.repository;

import com.example.minitesthouse.model.House;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IHouseRepository extends PagingAndSortingRepository<House, Long> {
}
