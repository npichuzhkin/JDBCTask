package com.npichuzhkin.JDBCTask.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotNull(message = "Title field must be filled in.")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "Title field can contain only letters")
    @Size(min = 2, max = 200, message = "Title field must contain from 2 to 200 letters.")
    private String title;

    @NotNull(message = "Author field must be filled in.")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "Author field can contain only letters")
    @Size(min = 2, max = 200, message = "Author field must contain from 2 to 200 letters.")
    private String author;

    @NotNull(message = "PublicationYear field must be filled in.")
    @Digits(message = "PublicationYear field can contain only 4 digits", integer = 4, fraction = 0)
    @Min(value = 1, message = "PublicationYear must be at least 1")
    @Max(value = 3000, message = "PublicationYear should be no more than 3000")
    private int publicationYear;

}
