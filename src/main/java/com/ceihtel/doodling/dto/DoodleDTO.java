package com.ceihtel.doodling.dto;

import lombok.Data;

import java.util.List;

@Data
public class DoodleDTO {
    private Long id;

    private List<DoodleDTO> children;
}
