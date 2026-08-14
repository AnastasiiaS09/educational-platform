package com.academy.educationalplatform.service;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.InitiateRequest;
import com.academy.educationalplatform.dto.InviteCodeRequest;
import com.academy.educationalplatform.entity.InviteCode;
import com.academy.educationalplatform.entity.InviteCodeStatus;
import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.academy.educationalplatform.mapper.InviteCodeMapper;
import com.academy.educationalplatform.repository.InviteCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InviteCodeService {
    private final InviteCodeRepository inviteCodeRepository;
    private final InviteCodeMapper inviteCodeMapper;

    public InviteCodeService(InviteCodeRepository inviteCodeRepository, InviteCodeMapper inviteCodeMapper) {
        this.inviteCodeRepository = inviteCodeRepository;
        this.inviteCodeMapper = inviteCodeMapper;
    }

    public AnswerRequest createInviteCode(InitiateRequest request) {
        try {
            if (inviteCodeRepository.existsByCode(request.getCode())) {
                throw PlatformException.of(PlatformErrorCode.INVITE_CODE_ALREADY_EXISTS);
            }

            List<Role> roles = List.of(Role.USER);


            List<InviteCodeStatus> codeStatuses = List.of(InviteCodeStatus.AVAILABLE);

            for (InviteCodeStatus codeStatus : codeStatuses) {
                inviteCodeRepository.save(
                        inviteCodeMapper.toInviteCode(request.getCode(), codeStatus)
                );
            }


            AnswerRequest answer = new AnswerRequest();
            answer.setText("Invite code was created");

            return answer;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
