package ${packageName};

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${entity.packageName}.${entity.className};
import ${lastRenderResponse.repository.packageName}.${lastRenderResponse.repository.className};
import ${lastRenderResponse.form.packageName}.${lastRenderResponse.form.className};
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
public class ${className} {

    @Autowired
    private ${lastRenderResponse.repository.className} ${lastRenderResponse.repository.className?uncap_first};

    public ${entity.className} create(${lastRenderResponse.form.className} form) {
        ${entity.className} ${entity.className?uncap_first} = new ${entity.className}();
        BeanUtils.copyProperties(form, ${entity.className?uncap_first});
        // TODO
        return ${lastRenderResponse.repository.className?uncap_first}.save(${entity.className?uncap_first});
    }

    public void delete(${entity.idClassName} id) {
        ${lastRenderResponse.repository.className?uncap_first}.findById(id)
                .ifPresent(${lastRenderResponse.repository.className?uncap_first}::delete);
    }

    public Optional<${entity.className}> update(${lastRenderResponse.form.className} form, ${entity.idClassName} id) {
        return ${lastRenderResponse.repository.className?uncap_first}.findById(id)
                .map(${entity.className?uncap_first} -> {
                    BeanUtils.copyProperties(form, ${entity.className?uncap_first});
                    return ${lastRenderResponse.repository.className?uncap_first}.save(${entity.className?uncap_first});
                });
    }

    public Optional<${entity.className}> get(${entity.idClassName} id) {
        return ${lastRenderResponse.repository.className?uncap_first}.findById(id);
    }

    public List<${entity.className}> list() {
        return ${lastRenderResponse.repository.className?uncap_first}.findAll();
    }

}