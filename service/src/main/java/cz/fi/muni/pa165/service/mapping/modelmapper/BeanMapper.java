package cz.fi.muni.pa165.service.mapping.modelmapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Bean mapper used for mapping of entities to DTO
 *
 * @author Igor Ignac
 */
public interface BeanMapper {
    /**
     * Maps of objects to specific class
     * @param objects - input of objects
     * @param mapToClass - wanted class
     * @return List of mapped objects
     */
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    /**
     * Maps objects to specific class
     * @param objects - input of objects
     * @param mapToClass - wanted class
     * @return Set of mapped objects
     */
    <T> Set<T> mapToSet(Collection<?> objects, Class<T> mapToClass);

    /**
     * Maps object to specific class
     * @param u - input object
     * @param mapToClass - wanted class
     * @return mapped object
     */
    <T> T mapTo(Object u, Class<T> mapToClass);
}
