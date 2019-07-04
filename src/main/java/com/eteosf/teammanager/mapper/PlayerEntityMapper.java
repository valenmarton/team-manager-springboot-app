package com.eteosf.teammanager.mapper;

import com.eteosf.teammanager.model.dto.PlayerDto;
import com.eteosf.teammanager.model.entity.PlayerEntity;
import io.swagger.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerEntityMapper {
    public static PlayerDto playerObjToPlayerServiceDto(Player player) {
        PlayerDto playerDto = new PlayerDto(player.getId(),player.getName(),player.getAge());
        return playerDto;
    }

    public static Player playerServiceDtoToPlayerObj(PlayerDto playerDto) {
        final Player player = new Player();
        player.setId(playerDto.getId());
        player.setName(playerDto.getName());
        player.setAge(playerDto.getAge());
        return player;
    }

    public static PlayerEntity playerServiceDtoToPlayerEntity(PlayerDto playerDto) {
        final PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(playerDto.getId());
        playerEntity.setName(playerDto.getName());
        playerEntity.setAge(playerDto.getAge());
        return playerEntity;
    }

    public static PlayerDto playerEntityToPlayerServiceDto(PlayerEntity playerEntity) {
        final PlayerDto playerDto = new PlayerDto();
        playerDto.setId(playerEntity.getId());
        playerDto.setName(playerEntity.getName());
        playerDto.setAge(playerEntity.getAge());

        return playerDto;
    }

    public static List<PlayerEntity> playerDtoListToEntityList (List<PlayerDto> dtoList) {
        final List<PlayerEntity> playerEntityList = new ArrayList<>();
        for (PlayerDto playerDto : dtoList) {
            final PlayerEntity playerEntity = PlayerEntityMapper.playerServiceDtoToPlayerEntity(playerDto);
            playerEntityList.add(playerEntity);
        }
        return playerEntityList;
    }

    public  static List<PlayerDto> playerEntityListToDtoList(List<PlayerEntity> entityList) {
        final List<PlayerDto> playerDtoList = new ArrayList<>();
        for (PlayerEntity playerEntity : entityList) {
            final PlayerDto playerDto = PlayerEntityMapper.playerEntityToPlayerServiceDto(playerEntity);
            playerDtoList.add(playerDto);
        }
        return playerDtoList;
    }

    public  static List<Player> playerDtoListToPlayerObjList (List<PlayerDto> playerDtoList) {
        final List<Player> playerList = new ArrayList<>();
        for (PlayerDto playerDto : playerDtoList) {
            final Player player = PlayerEntityMapper.playerServiceDtoToPlayerObj(playerDto);
            playerList.add(player);
        }
        return playerList;
    }

}
