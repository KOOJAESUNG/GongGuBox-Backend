package com.gonggubox.mapper.cart;

import com.gonggubox.domain.cart.CartEntity;
import com.gonggubox.domain.cart.CartItemEntity;
import com.gonggubox.dto.cart.CartDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public interface CartMapper {

//    @Mappings({
//            @Mapping(target = "id",ignore = true),
//            @Mapping(source = "member",target = "member")
//    })
//    CartEntity toEntity(CartDto.CartPostDto CartPostDto, MemberEntity member);


    @Mapping(target = "totalPrice",expression = "java(this.getTotalPrice(cart.getCartItemList()))")
    CartDto.CartResponseDto toResponseDto(CartEntity cart);

    default Long getTotalPrice(List<CartItemEntity> cartItemList){
        long totalPrice = 0L;
        for (CartItemEntity cartItemEntity : cartItemList) {
            totalPrice+=cartItemEntity.getItem().getPrice()* cartItemEntity.getCount();
        }
        return totalPrice;
    }

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mappings({
//            @Mapping(target = "id", ignore = true),
//            @Mapping(target = "member", ignore = true),
//    })
//    public void updateFromPatchDto(CartDto.CartPatchDto CartPatchDto, @MappingTarget CartEntity cart);

}
