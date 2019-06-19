package ${packageName};

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${entity.fullName};
import ${repository_full_name};
<#if imports??>
<#list imports as import>
import ${import};
</#list>
</#if>

import java.util.List;
import java.util.Optional;

/**
 * ${comments}
 *
 * @author ${author}
 * Created On ${date}.
 */
@Service
public class ${entity.simpleName}${service_suffix} {

    @Autowired
    private ${repository_simple_name} ${repository_simple_name?uncap_first};

    public ${entity.simpleName} create(${entity.simpleName}Form form) {
        ${entity.simpleName} ${entity.simpleName?uncap_first} = new ${entity.simpleName}();
        BeanUtils.copyProperties(form, ${entity.simpleName?uncap_first});
        // TODO
        return ${repository_simple_name?uncap_first}.save(${entity.simpleName?uncap_first});
    }

    public void delete(${entity.idClass.simpleName} id) {
        ${repository_simple_name?uncap_first}.findById(id)
                .ifPresent(${repository_simple_name?uncap_first}::delete);
    }

    public Optional<${entity.simpleName}> update(${entity.simpleName}Form form, ${entity.idClass.simpleName} id) {
        return ${repository_simple_name?uncap_first}.findById(id)
                .map(${entity.simpleName?uncap_first} -> {
                    BeanUtils.copyProperties(form, ${entity.simpleName?uncap_first});
                    return ${repository_simple_name?uncap_first}.save(${entity.simpleName?uncap_first});
                });
    }

    public Optional<${entity.simpleName}> get(Long id) {
        return ${repository_simple_name?uncap_first}.findById(id);
    }

    public List<${entity.simpleName}> list() {
        return ${repository_simple_name?uncap_first}.findAll();
    }

}