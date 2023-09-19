package com.gonggubox.mapper.admin;

import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.domain.admin.NoticeEntity;
import com.gonggubox.dto.admin.AdminDto;
import com.gonggubox.dto.admin.NoticeDto;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public abstract class NoticeMapper {

    @Autowired
    private AdminMapper adminMapper;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "modifiedAt", ignore = true),
            @Mapping(target = "imageUrlList", ignore = true),
            @Mapping(source = "admin", target = "admin")
    })
    public abstract NoticeEntity toEntity(NoticeDto.NoticePostDto NoticePostDto, AdminEntity admin);


    @Mapping(source = "admin", target = "admin", qualifiedByName = "adminEntityToAdminResponseDto")
    public abstract NoticeDto.NoticeResponseDto toResponseDto(NoticeEntity NoticeEntity);

    @Named("adminEntityToAdminResponseDto")
    AdminDto.AdminResponseDto adminEntityToAdminResponseDto(AdminEntity admin) {
        if (admin == null) return null;
        return adminMapper.toResponseDto(admin);
    }


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "modifiedAt", ignore = true),
            @Mapping(target = "imageUrlList", ignore = true),
            @Mapping(target = "admin", ignore = true),
    })
    public abstract void updateFromPatchDto(NoticeDto.NoticePatchDto NoticePatchDto, @MappingTarget NoticeEntity NoticeEntity);

}
