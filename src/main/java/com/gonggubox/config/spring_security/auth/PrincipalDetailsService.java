package com.gonggubox.config.spring_security.auth;


import com.gonggubox.domain.admin.AdminEntity;
import com.gonggubox.domain.member.MemberEntity;
import com.gonggubox.repository.admin.AdminRepository;
import com.gonggubox.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("PrincipalDetailsService : 진입");

		if (username.contains("admin_")) {
			AdminEntity adminEntity = adminRepository.findByUsername(username);

			// session.setAttribute("loginUser", user);
			return new com.gonggubox.config.spring_security.auth.PrincipalDetails(adminEntity);
		} else {
			//MemberEntity memberEntity = memberRepository.findByUsername(username);

			MemberEntity memberEntity = memberRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("찾을 수 없는 username: " + username));
			// session.setAttribute("loginUser", user);
			return new com.gonggubox.config.spring_security.auth.PrincipalDetails(memberEntity);
		}
	}
}
