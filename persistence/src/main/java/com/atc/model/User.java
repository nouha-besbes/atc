package com.atc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "attendences")
@Table(name = "T_USER")
public class User extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "USR_FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "USR_LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "USR_EMAIL_ADRESS", nullable = false)
    private String email;

    @Column(name = "USR_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USR_FINGER_PRINT", nullable = true)
    private String fingerPrint;

    @Column(name = "USR_CARD_REF", nullable = true)
    private String cardRef;

    @ManyToOne
    @JoinColumn(name = "USR_AFFILIATE_ID")
    private Affiliate affiliate;

    @OneToMany(mappedBy = "user")
    private List<Attendance> attendences;

    public User(Long id, String firstName, String lastName, String email) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
