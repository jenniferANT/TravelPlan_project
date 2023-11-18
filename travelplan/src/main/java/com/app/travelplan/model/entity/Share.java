package com.app.travelplan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "shares")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Share extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "remitter_id") //người gửi
    private User remitter;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "share_receiver",
            joinColumns = @JoinColumn(name = "share_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    )
    private List<User> receiver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
