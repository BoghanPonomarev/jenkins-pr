package com.ponomarev.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = "hash")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "[order]")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String hash;

    @Column(nullable = false)
    private String producer;

    @Column(nullable = false)
    private String consumer;

}
