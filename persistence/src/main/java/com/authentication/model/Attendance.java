package com.authentication.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

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
@ToString
@Where(clause = "ATD_IS_DELETED=0")
@Table(name = "T_ATTENDANCE")
public class Attendance extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ATD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ATD_DATE", nullable = false)
    private Date date;

    @Column(name = "ATD_IS_DELETED", nullable = false)
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "ATD_USER_ID")
    private User user;
}
