package com.eteosf.teammanager.model.dto;


import io.swagger.model.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamDto {
    private Long id;
    private String name;
    private List<PlayerDto> playerList = new ArrayList<>();

    public TeamDto() {
    }

    public TeamDto(Long id, String name, List<PlayerDto> playerList) {
        this.id = id;
        this.name = name;
        this.playerList = playerList;
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

    public List<PlayerDto> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<PlayerDto> playerList) {
        this.playerList = playerList;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamDto teamDto = (TeamDto) o;

        if (name != null ? !name.equals(teamDto.name) : teamDto.name != null) return false;
        return playerList != null ? playerList.equals(teamDto.playerList) : teamDto.playerList == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (playerList != null ? playerList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "name='" + name + '\'' +
                ", playerList=" + playerList +
                '}';
    }
}
