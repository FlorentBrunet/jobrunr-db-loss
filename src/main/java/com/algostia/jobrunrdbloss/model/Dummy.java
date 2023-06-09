package com.algostia.jobrunrdbloss.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dummy")
@Getter
@Setter
public class Dummy {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "value")
    private long value;
}
