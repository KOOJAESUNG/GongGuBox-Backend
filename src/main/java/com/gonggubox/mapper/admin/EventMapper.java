package com.gonggubox.mapper.admin;

import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.domain.admin.EventEntity;
import com.gonggubox.dto.admin.AdminDto;
import com.gonggubox.dto.admin.EventDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public abstract class EventMapper {

    @Autowired
    private AdminMapper adminMapper;

    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "createAt",ignore = true),
            @Mapping(target = "modifiedAt",ignore = true),
            @Mapping(target = "imageUrlList",ignore = true),
            @Mapping(source = "eventPostDto.eventTitle", target = "title"),
            @Mapping(source = "eventPostDto.eventContent", target = "content"),
            @Mapping(source = "admin",target = "admin")
    })
    public abstract EventEntity toEntity(EventDto.EventPostDto eventPostDto, AdminEntity admin);



    @Mappings({
            @Mapping(source = "admin", target = "eventWriterAdminInfo", qualifiedByName = "adminEntityToAdminResponseDto"),
            @Mapping(source = "id", target = "eventId"),
            @Mapping(source = "content", target = "eventContent"),
            @Mapping(source = "imageUrlList", target = "eventImageList"),
            @Mapping(source = "title", target = "eventTitle")
    })
    public abstract EventDto.EventResponseDto toResponseDto(EventEntity EventEntity);

    @Named("adminEntityToAdminResponseDto")
    AdminDto.AdminResponseDto adminEntityToAdminResponseDto(AdminEntity admin) {
        if(admin == null) return null;
        return adminMapper.toResponseDto(admin);
    }


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt",ignore = true),
            @Mapping(target = "modifiedAt",ignore = true),
            @Mapping(target = "imageUrlList",ignore = true),
            @Mapping(target = "admin",ignore = true),
            @Mapping(source = "eventTitle", target = "title"),
            @Mapping(source = "eventContent", target = "content"),
    })
    public abstract void updateFromPatchDto(EventDto.EventPatchDto EventPatchDto, @MappingTarget EventEntity EventEntity);

}
