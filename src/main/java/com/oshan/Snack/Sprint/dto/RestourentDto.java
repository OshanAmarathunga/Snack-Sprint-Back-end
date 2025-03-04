package com.oshan.Snack.Sprint.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestourentDto {
    private String title;
    @Column(length = 1000)
    private List<String> images;
    private String description;
    private Long id;

}
