package umc.spring.service.MemberService;

import umc.spring.domain.Member;

public interface MemberQueryService {

    public Member findMember(Long userId);
}
