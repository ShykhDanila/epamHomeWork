package com.epam.hw34.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class LibraryDto {
    @NotEmpty(message = "Library authorName may not be empty")
    private String name;
    @NotBlank(message = "Library address may not be empty")
    private String address;
}
