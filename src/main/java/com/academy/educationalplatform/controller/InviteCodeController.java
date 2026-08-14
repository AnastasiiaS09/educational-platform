package com.academy.educationalplatform.controller;

import com.academy.educationalplatform.dto.AnswerRequest;
import com.academy.educationalplatform.dto.InitiateRequest;
import com.academy.educationalplatform.service.InviteCodeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invitecodes")
public class InviteCodeController {
    private final InviteCodeService inviteCodeService;

    public InviteCodeController(InviteCodeService inviteCodeService) {
        this.inviteCodeService = inviteCodeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public AnswerRequest createInviteCode(@Valid @RequestBody InitiateRequest request) {
        return inviteCodeService.createInviteCode(request);
    }
}
