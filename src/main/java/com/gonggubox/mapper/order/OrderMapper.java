package com.gonggubox.mapper.order;

import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.domain.order.OrderEntity;
import com.gonggubox.dto.order.OrderDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "modifiedAt", ignore = true),
            @Mapping(target = "totalPrice", ignore = true),
            @Mapping(source = "member", target = "member")
    })
    OrderEntity toEntity(OrderDto.OrderPostDto OrderPostDto, MemberEntity member);


    OrderDto.OrderResponseDto toResponseDto(OrderEntity OrderEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", ignore = true),
            @Mapping(target = "modifiedAt", ignore = true),
            @Mapping(target = "member", ignore = true),
            @Mapping(target = "totalPrice", ignore = true)
    })
    public void updateFromPatchDto(OrderDto.OrderPatchDto OrderPatchDto, @MappingTarget OrderEntity OrderEntity);

}
