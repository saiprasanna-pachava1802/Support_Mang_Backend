
package com.supportmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "user_technologies",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_user_technology",
            columnNames = {"user_id", "technology_name"}
        )
    }
)
public class UserTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "technology_name", nullable = false, length = 100)
    private String technologyName;

    public UserTechnology() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }
}
