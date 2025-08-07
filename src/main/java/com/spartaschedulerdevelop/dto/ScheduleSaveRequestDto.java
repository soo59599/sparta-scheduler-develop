package com.spartaschedulerdevelop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
