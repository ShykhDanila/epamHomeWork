package com.epam.hw34.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class BookDto {

    @JsonProperty(access = READ_ONLY)
    public String id;

    @NotBlank
    public String title;

    //    @ValidDescription
    public String description;

    @Positive
    public int pages;

    @Positive
    public int publicationYear;

    @Size(min = 1)
    public Set<Genre> genres;
}
