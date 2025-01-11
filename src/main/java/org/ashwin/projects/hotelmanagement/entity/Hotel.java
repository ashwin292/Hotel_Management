package org.ashwin.projects.hotelmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private String city;

    @ElementCollection
    @CollectionTable(name = "photos", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "list_value")
    private List<String> photos;

    @ElementCollection
    @CollectionTable(name = "amenities", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "list_value")
    private List<String> amenities;

//    @Column(columnDefinition = "TEXT[]")
//    private String[] photos;

//    @Column(columnDefinition = "TEXT[]")
//    private String[] amenities;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    private HotelContactInfo contactInfo;

    @Column(nullable = false)
    private Boolean isActive;

    @ManyToOne
    private User owner;
}
