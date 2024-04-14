package io.ussopm.AdminService.util;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MappingClasses {

    private final ModelMapper mapper;
    public <T> T convertToModel(Object someDto, Class<T> targetType) {
        return mapper.map(someDto, targetType);
    }

    public <T> T convertToDTO(Object someClass, Class<T> targetDTO) {
        return mapper.map(someClass, targetDTO);
    }
}
