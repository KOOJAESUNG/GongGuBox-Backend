package com.gonggubox.mapper.admin;

import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.dto.admin.AdminDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface AdminMapper {


    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "createAt",ignore = true),
            @Mapping(target = "modifiedAt",ignore = true),
            @Mapping(source = "adminPassword", target = "password"),
            @Mapping(source = "adminUsername", target = "username"),
            @Mapping(source = "adminEmail", target = "email"),
            @Mapping(source = "adminPhoneNumber", target = "phoneNumber"),
    })
    AdminEntity toEntity(AdminDto.AdminPostDto AdminPostDto);


    @Mappings({
            @Mapping(source = "id", target = "adminId"),
            @Mapping(source = "username", target = "adminUsername"),
            @Mapping(source = "email", target = "adminEmail"),
            @Mapping(source = "phoneNumber", target = "adminPhoneNumber"),
    })
    AdminDto.AdminResponseDto toResponseDto(AdminEntity AdminEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt",ignore = true),
            @Mapping(target = "modifiedAt",ignore = true),
            @Mapping(source = "adminPassword", target = "password"),
            @Mapping(source = "adminUsername", target = "username"),
            @Mapping(source = "adminEmail", target = "email"),
            @Mapping(source = "adminPhoneNumber", target = "phoneNumber"),
    })
    public void updateFromPatchDto(AdminDto.AdminPatchDto AdminPatchDto, @MappingTarget AdminEntity AdminEntity);

}