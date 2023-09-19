package com.gonggubox.mapper.cart;

import com.gonggubox.domain.cart.CartEntity;
import com.gonggubox.domain.cart.CartItemEntity;
import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.dto.cart.CartDto;
import com.gonggubox.dto.member.MemberDto;
import com.gonggubox.mapper.item.ItemMapper;
import com.gonggubox.mapper.member.MemberMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        componentModel = "spring", // 빌드 시 구현체 만들고 빈으로 등록
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입 전략
        unmappedTargetPolicy = ReportingPolicy.ERROR // 일치하지 않는 필드가 있으면 빌드 시 에러
)
public abstract class CartMapper {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private MemberMapper memberMapper;

//    @Mappings({
//            @Mapping(target = "id",ignore = true),
//            @Mapping(source = "member",target = "member")
//    })
//    CartEntity toEntity(CartDto.CartPostDto CartPostDto, MemberEntity member);


    @Mappings({
            @Mapping(target = "totalPrice", expression = "java(this.getTotalPrice(cart.getCartItemList()))"),
            @Mapping(source = "cartItemList", target = "cartItemList", qualifiedByName = "cartItemEntityListToCartItemResponseDtoList"),
            @Mapping(source = "member", target = "member", qualifiedByName = "memberEntityToMemberResponseDto")
    })
    public abstract CartDto.CartResponseDto toResponseDto(CartEntity cart);

    @Named("cartItemEntityListToCartItemResponseDtoList")
    List<CartDto.CartItemResponseDto> cartItemEntityListToCartItemResponseDtoList(List<CartItemEntity> cartItemEntityList) {
        if (cartItemEntityList == null) return null;
        List<CartDto.CartItemResponseDto> temp = new ArrayList<>();
        cartItemEntityList.forEach(o -> {
            temp.add(
                    CartDto.CartItemResponseDto.builder()
                            .count(o.getCount())
                            .item(itemMapper.toResponseDto(o.getItem()))
                            .build()
            );
        });
        return temp;
    }

    @Named("memberEntityToMemberResponseDto")
    MemberDto.MemberResponseDto memberEntityToMemberResponseDto(MemberEntity member) {
        if (member == null) return null;
        return memberMapper.toResponseDto(member);
    }

    Long getTotalPrice(List<CartItemEntity> cartItemList) {
        if (cartItemList == null) return null;
        long totalPrice = 0L;
        for (CartItemEntity cartItemEntity : cartItemList) {
            totalPrice += cartItemEntity.getItem().getPrice() * cartItemEntity.getCount();
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
