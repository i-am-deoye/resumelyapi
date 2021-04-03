package com.resumly.resumeapi.modules.user.services.mappers;

import com.resumly.resumeapi.modules.core.services.IBaseMapper;
import com.resumly.resumeapi.modules.user.domain.Authority;
import com.resumly.resumeapi.modules.user.domain.User;
import com.resumly.resumeapi.modules.user.dto.UserDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper implements IBaseMapper<UserDTO, User> {

    public static void baseMapingUserDTOToUser(UserDTO userDTO, User user) {
        user.setLogin(userDTO.getLogin().toLowerCase());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setImageUrl(userDTO.getImageUrl());
        user.setActivated(userDTO.isActivated());
        user.setLangKey(userDTO.getLangKey());
    }

    @Override
    public UserDTO toDTO(User entity) {
        if (Objects.isNull(entity)) return null;

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setActivated(entity.isActivated());
        dto.setImageUrl(entity.getImageUrl());
        dto.setLangKey(entity.getLangKey());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedBy(entity.getLastModifiedBy());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setAuthorities((Set<Authority>) entity.getAuthorities());

        return dto;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if (Objects.isNull(dto)) return null;

        User user = new User();
        user.setId(dto.getId());
        UserMapper.baseMapingUserDTOToUser(dto, user);
        Set<Authority> authorities = dto.getAuthorities();
        user.setAuthorities(authorities);
        return user;
    }

    @Override
    public List<UserDTO> toDTOs(List<User> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> toEntities(List<UserDTO> dtos) {
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

