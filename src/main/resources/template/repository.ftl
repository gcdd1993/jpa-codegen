package ${entity_package};

import ${entity_fullName};
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ${comments}
 * @author ${author}
 * Created On ${date}.
 */
public interface ${entity_simpleName}${repository_suffix} extends JpaRepository<${entity_simpleName}, ${entity_id}> {
}