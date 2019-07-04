package com.eteosf.teammanager.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="team")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "teamEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerEntity> playerEntities = new ArrayList<>();

    public TeamEntity() {
    }


    public TeamEntity(String name, Long id, List<PlayerEntity> playerEntities) {
        this.name = name;
        this.id = id;
        this.playerEntities = playerEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerEntity> getPlayerEntities() {
        return playerEntities;
    }

    public void setPlayerEntities(List<PlayerEntity> playerEntities) {
        this.playerEntities.clear();
        this.playerEntities.addAll(playerEntities);
        for (PlayerEntity playerEntity : playerEntities) {
            playerEntity.setTeamEntity(this);
        }
    }
}
