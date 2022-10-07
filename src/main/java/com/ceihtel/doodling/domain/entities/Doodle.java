package com.ceihtel.doodling.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doodle {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    private Doodle parent;

    @OneToMany(mappedBy = "parent")
    private List<Doodle> children = new ArrayList<>();

    public boolean hasChild(Doodle doodle) {
        return children.contains(doodle) || children.stream().anyMatch(child -> child.hasChild(doodle));
    }
}
