package utils;

import dto.GreeterDto;
import entity.GreeterEntity;

public class EntityToDto {
    public static GreeterDto greeterEntityToDto(GreeterEntity entity) {
        return new GreeterDto(String.valueOf(entity.getIdentifier()), entity.getName());
    }

}
