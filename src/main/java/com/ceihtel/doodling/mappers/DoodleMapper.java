package com.ceihtel.doodling.mappers;

import com.ceihtel.doodling.domain.entities.Doodle;
import com.ceihtel.doodling.domain.repositories.DoodleRepository;
import com.ceihtel.doodling.dto.CreateDoodleDTO;
import com.ceihtel.doodling.dto.DoodleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DoodleMapper {

    private final DoodleRepository doodleRepository;

    public List<DoodleDTO> map(List<Doodle> doodles) {
        return cleanupTree(doodles).stream().map(this::map).toList();
    }

    /**
     * Restructure the original list as a tree
     * @param initialDoodles List of all doodles, straight from the repository
     * @return Tree structure of doodles with their children
     */
    public List<Doodle> cleanupTree(List<Doodle> initialDoodles) {
        var cleanedDoodles = new ArrayList<Doodle>();

        initialDoodles.forEach(initialDoodle -> {
            if (initialDoodles.stream().noneMatch(x -> x.hasChild(initialDoodle))) {
                cleanedDoodles.add(initialDoodle);
            }
        });

        return cleanedDoodles;
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
