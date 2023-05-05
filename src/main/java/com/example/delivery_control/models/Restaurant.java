package com.example.delivery_control.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String restaurant_name;
    private String restaurant_address;
    private String restaurant_phone;
    private String restaurant_imgurl;
    private String restaurant_site;
    @ManyToOne
    @JoinColumn(name="created_by", nullable = false)
    private UserEntity created_by;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Dish> dishList = new ArrayList<>();

}
