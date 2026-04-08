package com.example.olapagent.api.controller;

import com.example.olapagent.api.request.AskRequest;
import com.example.olapagent.api.response.AskResponse;
import com.example.olapagent.application.service.AskOlapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/olap")
@RequiredArgsConstructor
public class OlapQueryController {

    private final AskOlapService askOlapService;

    @PostMapping("/ask")
    public AskResponse ask(@Valid @RequestBody AskRequest request) {
        return askOlapService.ask(request.getQuestion());
    }
}