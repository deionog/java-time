package com.dog.contactapi.domain;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Table(name = "contacts")
public class Contact {
    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String title;
    private String photoUrl;
    private String status;


}
