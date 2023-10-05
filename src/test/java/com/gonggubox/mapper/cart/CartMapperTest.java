package com.gonggubox.mapper.cart;

import com.gonggubox.constant.MemberClass;
import com.gonggubox.domain.cart.CartEntity;
import com.gonggubox.domain.cart.CartItemEntity;
import com.gonggubox.domain.item.ItemEntity;
import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.dto.cart.CartDto;
import com.gonggubox.dto.member.MemberDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * todo : mock이 아닌 데이터로 다시 짜야할듯
 */
@SpringBootTest
@Transactional
class CartMapperTest {

    @Autowired
    CartMapper cartMapper;

    @Mock
    CartEntity cart;

    @Mock
    MemberEntity member;

    @Mock
    CartItemEntity cartItem1, cartItem2, cartItem3;

    @Mock
    ItemEntity item1, item2, item3;

    List<CartItemEntity> cartItemEntityList = new ArrayList<>();


    @Test
    void toResponseDto() {

        CartDto.CartResponseDto responseDto = cartMapper.toResponseDto(cart);

        Assertions.assertEquals(1L,responseDto.getCartId());
        Assertions.assertEquals(14000L,responseDto.getCartTotalPrice());
        Assertions.assertEquals(cartMapper.cartItemEntityListToCartItemResponseDtoList(cartItemEntityList).get(0).getItemCount()
                ,responseDto.getCartItemList().get(0).getItemCount());
        Assertions.assertEquals(cartMapper.memberEntityToMemberResponseDto(member).getMemberEmail(),responseDto.getCartOwnerMemberInfo().getMemberEmail());


    }

    @Test
    void cartItemEntityListToCartItemResponseDtoList() {
        List<CartDto.CartItemResponseDto> response = cartMapper.cartItemEntityListToCartItemResponseDtoList(cartItemEntityList);
        int i=1;
        for (CartDto.CartItemResponseDto cartItemResponseDto : response) {
            Assertions.assertEquals(i,cartItemResponseDto.getItemCount());
            Assertions.assertEquals((long) i*1000,cartItemResponseDto.getItemInfo().getItemPrice());
            i++;
        }
    }

    @Test
    void memberEntityToMemberResponseDto() {
        MemberDto.MemberResponseDto memberResponseDto = cartMapper.memberEntityToMemberResponseDto(member);
        Assertions.assertEquals(member.getUsername(),memberResponseDto.getMemberUsername());
        Assertions.assertEquals(member.getMemberClass(),memberResponseDto.getMemberClass());
        Assertions.assertEquals(member.getEmail(),memberResponseDto.getMemberEmail());
        Assertions.assertEquals(member.getId(),memberResponseDto.getMemberId());
        Assertions.assertEquals(member.getPhoneNumber(), memberResponseDto.getMemberPhoneNumber());
    }

    @Test
    void getTotalPrice() {
        Assertions.assertEquals(14000L,cartMapper.getTotalPrice(cartItemEntityList));
    }

    @BeforeEach
    void setUp() {
        when(member.getUsername()).thenReturn("member");
        when(member.getMemberClass()).thenReturn(MemberClass.GOLD);
        when(member.getId()).thenReturn(1L);
        when(member.getEmail()).thenReturn("member@email.com");
        when(member.getPhoneNumber()).thenReturn("010-1111-1111");
        when(member.getGroupMemberList()).thenReturn(null);

        when(cartItem1.getItem()).thenReturn(item1);
        when(cartItem2.getItem()).thenReturn(item2);
        when(cartItem3.getItem()).thenReturn(item3);
        when(item1.getPrice()).thenReturn(1000L);
        when(item2.getPrice()).thenReturn(2000L);
        when(item3.getPrice()).thenReturn(3000L);
        when(cartItem1.getCount()).thenReturn(1);
        when(cartItem2.getCount()).thenReturn(2);
        when(cartItem3.getCount()).thenReturn(3);

        List<CartItemEntity> cartItemTemp = new ArrayList<>();
        cartItemTemp.add(cartItem1);
        cartItemTemp.add(cartItem2);
        cartItemTemp.add(cartItem3);

        cartItemEntityList.addAll(cartItemTemp);

        when(cart.getId()).thenReturn(1L);
        when(cart.getCartItemList()).thenReturn(cartItemEntityList);
        when(cart.getMember()).thenReturn(member);

    }
}