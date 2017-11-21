package com.trendyol.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONFIGURATION", schema = "TYOL")
public class ConfigurationEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

    @Column(name = "status")
    private int status;

    @Column(name = "applicationName")
    private String applicationName;
}
