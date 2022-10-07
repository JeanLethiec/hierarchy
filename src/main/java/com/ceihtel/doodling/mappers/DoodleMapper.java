package com.ceihtel.doodling.mappers;

import com.ceihtel.doodling.domain.entities.Doodle;
import com.ceihtel.doodling.domain.repositories.DoodleRepository;
import com.ceihtel.doodling.dto.CreateDoodleDTO;
import com.ceihtel.doodling.dto.DoodleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DoodleMapper {

    private final DoodleRepository doodleRepository;

    public List<DoodleDTO> map(List<Doodle> doodles) {
        return doodles.stream().map(this::map).toList();
    }

    public DoodleDTO map(Doodle doodle) {
        if (doodle == null) {
            return null;
        }

        var dto = new DoodleDTO();
        dto.setId(doodle.getId());
        dto.setChildren(map(doodle.getChildren()));

        return dto;
    }

    public Doodle map(CreateDoodleDTO createDoodleDTO) {
        if (createDoodleDTO == null) {
            return null;
        }

        var doodle = new Doodle();

        if (createDoodleDTO.getParentId() != null) {
            var parent = doodleRepository.findById(createDoodleDTO.getParentId())
                                         .orElseThrow(() -> new IllegalArgumentException(String.format("Parent doodle with id '%s' does not exist.", createDoodleDTO.getParentId())));
            doodle.setParent(parent);
        }

        return doodle;
    }
}
