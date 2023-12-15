package com.example.brief_gestion_competitions_aftas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @NotNull(message = "Name cannot be null")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Start time cannot be null")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @NotNull(message = "Start time cannot be null")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @NotNull(message = "Location cannot be null")
    @Size(min = 2, max = 50, message = "Location must be between 2 and 50 characters")
    private String location;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be greater than 0")
    private int amount;

    @NotNull(message = "Total member cannot be null")
    @Min(value = 0, message = "Total member must be greater than 0")
    private int totalMember;

    @OneToMany(mappedBy = "competition")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Ranking> ranking;

    @OneToMany(mappedBy = "competition")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Hunting> hunting;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;
}
