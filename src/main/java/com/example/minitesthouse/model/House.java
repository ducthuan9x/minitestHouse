package com.example.minitesthouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "ko để trống dm!")
    private String name;
    @NotBlank(message = "ko để trống dm!")
    private String address;

    @Min(value = 1 ,message = "tối thiểu 1 phòng")
    @Max(value = 10 ,message = "tối đa 10 phòng")
    private int numberOfBedrooms;
    @Min(value = 1 ,message = "tối thiểu 1 phòng")
    @Max(value = 3 ,message = "tối đa 3 phòng")
    private int numberOfBathrooms;
    @Min(value = 1000 ,message = "giá siêu rẻ")
    private int price;
    private String img;

    public House() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
