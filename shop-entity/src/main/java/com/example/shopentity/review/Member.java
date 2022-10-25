package com.example.shopentity.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(of = {"id","name"})
@Setter
@Getter
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String name;
}
