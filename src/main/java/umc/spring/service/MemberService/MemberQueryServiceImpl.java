package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.base.Code;
import umc.spring.base.exception.MemberException;
import umc.spring.domain.Member;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    public class MemberRepository memberRepository;

    @Override
    public Member findMember(Long userId){
        return memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(Code.MEMBER_NOT_FOUND));
    }
}
