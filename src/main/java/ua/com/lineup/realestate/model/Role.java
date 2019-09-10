package ua.com.lineup.realestate.model;

import lombok.Getter;
import org.hibernate.annotations.NaturalId;
import ua.com.lineup.realestate.model.enumeration.RoleName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;
}
