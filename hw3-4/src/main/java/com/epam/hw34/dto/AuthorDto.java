package com.epam.hw34.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class AuthorDto {
    @JsonProperty(access = READ_ONLY)
    public String id;
    @NotBlank
    public String name;
    public String nickname;
}
