package com.authentication.model;

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
import javax.persistence.OneToOne;
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
@ToString(exclude = "users")
@Table(name = "T_AFFILIATE")
public class Affiliate extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AFF_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "AFF_NAME", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "AFF_COMPANY_ID")
    private Company company;

    @OneToMany(mappedBy = "affiliate")
    private List<User> users;

    @OneToOne(mappedBy = "affiliate")
    private Device device;
}
