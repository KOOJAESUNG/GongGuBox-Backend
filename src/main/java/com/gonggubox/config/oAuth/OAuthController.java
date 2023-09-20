package com.gonggubox.config.oAuth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth2/code")
@Slf4j
public class OAuthController {

    private final OAuthService oAuthService;
    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) {
        log.info("code: " + code);

        // OAuthService를 통해 토큰을 얻어옴
        String accessToken = oAuthService.getAccessToken(code);
        log.info("Access Token: " + accessToken);

        // 얻어온 액세스 토큰을 사용하여 Kakao 사용자 정보를 조회
        oAuthService.createKakaoUser(accessToken);
    }
}
