package com.gonggubox.config.oAuth;
import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.repository.member.MemberRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;




    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    /**
     *
     * 카카오
     */

    public String getKakaoAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + kakaoClientId);
            sb.append("&redirect_uri=" + kakaoRedirectUri);
            sb.append("&code=" + authorize_code);
            sb.append("&client_secret=" + kakaoClientSecret);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("access_token : " + access_Token);
            log.info("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        log.info("access_token!!! : " + access_Token);
        return access_Token;
    }




    public void createKakaoUser(String token) {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            int id = element.getAsJsonObject().get("id").getAsInt();
            boolean hasNickname = element.getAsJsonObject().get("properties").getAsJsonObject().has("nickname");
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String nickname ="";
            String email = "";



            if(hasNickname){
                nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
            }
            if(hasEmail){
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            log.info("id : " + id);
            log.info("nickname: " + nickname);
            log.info("email : " + email);


            //카카오 로그인 토큰은 email과 password로 만들어줌
            String username = email;
            //패스워드 = 카카오 Id + token
            String password = id + token;

            //패스워드 확인
            log.info("password: " + password);

            // DB에 중복된 회원 있는지 확인
            Optional<MemberEntity> existingMember = memberRepository.findByUsername(username);

            if (existingMember.isPresent()) {
                log.error("이미 가입되어 있는 회원입니다.");
            } else {
                // 패스워드 인코딩
                String encodedPassword = encoder.encode(password);

                //DB 저장
                MemberEntity newMember = new MemberEntity();
                newMember.setUsername(username);
                newMember.setPassword(encodedPassword);
                log.info("encodedPassword: " + encodedPassword);

                memberRepository.save(newMember);
            }


            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 구글
     */


    public String getGoogleAccessToken(String authorize_code) {
        String result = "";
        String accessToken = "";
        String reqURL = "https://oauth2.googleapis.com/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로 설정
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // POST 요청에 필요한 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + googleClientId);
            sb.append("&redirect_uri=" + googleRedirectUri);
            sb.append("&code=" + authorize_code);
            sb.append("&client_secret=" + googleClientSecret);

            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON 타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;

            while ((line=br.readLine()) != null) {
                result += line;
                result += System.lineSeparator();
            }

            br.close();
            bw.close();

            log.info("response body : " + result);

            // Gson 라이브러리에 포함된 클래스로 JSON 파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();

                if (jsonObject.has("access_token") && !jsonObject.get("access_token").isJsonNull()) {
                    accessToken = jsonObject.get("access_token").getAsString();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    public void createGoogleUser(String token) {

        String reqURL = "https://www.googleapis.com/userinfo/v2/me";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            String id = element.getAsJsonObject().get("id").getAsString();
            String email = element.getAsJsonObject().get("email").getAsString();
            String name = element.getAsJsonObject().get("name").getAsString();

            log.info("id : " + id);
            log.info("email: " + email);
            log.info("name : " + name);


            //구글 로그인 토큰은 email과 password로 만들어줌
            String username = email;
            //패스워드 = 카카오 Id + token
            String password = id + token;

            //패스워드 확인
            log.info("password: " + password);

            // DB에 중복된 회원 있는지 확인
            Optional<MemberEntity> existingMember = memberRepository.findByUsername(username);

            if (existingMember.isPresent()) {
                log.error("이미 가입되어 있는 회원입니다.");
            } else {
                // 패스워드 인코딩
                String encodedPassword = encoder.encode(password);

                //DB 저장
                MemberEntity newMember = new MemberEntity();
                newMember.setUsername(username);
                newMember.setPassword(encodedPassword);
                log.info("encodedPassword: " + encodedPassword);

                memberRepository.save(newMember);
            }


            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
