package com.example.VodReco.domain;

import com.example.VodReco.dto.WishResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_wish")
public class UserWish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private int id;

//    @Column(nullable = false) //unique = true) // unique = true) wish, rating은 email 겹쳐도 됨. 사용자가 여러 개의 vod에 대한 평가 내림(231104)
    private String email;
//    @Column(nullable = false)//, unique = true)
    private String contentId;
    private Integer wish;

    @Builder
    public UserWish(String email, String contentId, Integer wish) {
        this.email = email;
        this.contentId = contentId;
        this.wish = wish;
    }

    public UserWish() {

    }

    public WishResponseDto toWishResponseDto(UserWish userWish) {
        return WishResponseDto.builder()
                .email(email)
                .contentId(contentId)
                .wish(wish)
                .build();
    }
}
