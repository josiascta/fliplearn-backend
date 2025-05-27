package com.ifpb.ads.fliplearn.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
    @NotNull
    private String login;
    @NotNull
    private String senha;
}
