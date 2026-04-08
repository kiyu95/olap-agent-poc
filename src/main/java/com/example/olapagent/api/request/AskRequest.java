package com.example.olapagent.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AskRequest {

    @NotBlank
    private String question;
}