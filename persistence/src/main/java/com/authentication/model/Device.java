package com.authentication.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.authentication.utils.DeviceType;

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
@Table(name = "T_DEVICE", uniqueConstraints = { @UniqueConstraint(columnNames = { "DEV_IP_ADRESS", "DEV_PORT" }) })
public class Device extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DEV_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "DEV_IP_ADRESS", nullable = false)
    private String ipAdress;

    @Column(name = "DEV_PORT", nullable = false)
    private Integer port;

    @Column(name = "DEV_DEVICE_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @OneToOne()
    @JoinColumn(name = "DEV_AFFILIATE_ID", referencedColumnName = "AFF_ID")
    private Affiliate affiliate;

}
