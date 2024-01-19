package umc.spring.service.MemberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDto;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDto.JoinDto request);
}
