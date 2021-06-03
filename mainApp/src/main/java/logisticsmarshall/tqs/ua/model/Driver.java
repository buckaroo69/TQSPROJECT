package logisticsmarshall.tqs.ua.model;

import lombok.Data;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.Set;

@Data
@Entity
@Table(name = "driver")
public class Driver {
    public enum Vehicle {
        BICLYCLE, CAR, MOTORCYCLE, ONFOOT;
    }

    @Id
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private  User user;



    @Column(name = "phoneNumber", nullable = false)
    private Integer phoneNumber;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "vehicle", nullable = false)
    @Enumerated(EnumType.STRING)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "driver")
    private Set<Delivery> delivery;



    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Reputation> reputation;

}