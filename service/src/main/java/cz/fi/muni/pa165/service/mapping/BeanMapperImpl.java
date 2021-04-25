package cz.fi.muni.pa165.service.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.dozermapper.core.Mapper;

import java.util.*;

/**
 * Bean mapper used for mapping of entities to DTO
 *
 * @author Igor Ignac
 */

@Service
public class BeanMapperImpl implements BeanMapper {

    private Mapper modelMapper;

    @Autowired
    public BeanMapperImpl(Mapper modelMapper) { this.modelMapper = modelMapper; }

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(modelMapper.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public <T> Set<T> mapToSet(Collection<?> objects, Class<T> mapToClass) {
        Set<T> mappedCollection = new HashSet<>();
        for (Object object : objects) {
            mappedCollection.add(modelMapper.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return null;
    }
}
