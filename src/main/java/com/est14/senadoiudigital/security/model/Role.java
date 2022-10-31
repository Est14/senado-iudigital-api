package com.est14.senadoiudigital.security.model;


import com.est14.senadoiudigital.security.enums.NameRole;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private NameRole nameRole;

    public Role() {
    }

    public Role(NameRole role) {
        this.nameRole = role;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NameRole getNameRole() {
        return nameRole;
    }

    public void setNameRole(NameRole nameRole) {
        this.nameRole = nameRole;
    }


}
