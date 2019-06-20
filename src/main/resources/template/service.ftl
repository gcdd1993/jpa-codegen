package ${packageName};

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${entity.fullName};
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

    public ${entity.simpleName} create(${lastRenderResponse.form.className} form) {
        ${entity.simpleName} ${entity.simpleName?uncap_first} = new ${entity.simpleName}();
        BeanUtils.copyProperties(form, ${entity.simpleName?uncap_first});
        // TODO
        return ${lastRenderResponse.repository.className?uncap_first}.save(${entity.simpleName?uncap_first});
    }

    public void delete(${entity.idClass.simpleName} id) {
        ${lastRenderResponse.repository.className?uncap_first}.findById(id)
                .ifPresent(${lastRenderResponse.repository.className?uncap_first}::delete);
    }

    public Optional<${entity.simpleName}> update(${lastRenderResponse.form.className} form, ${entity.idClass.simpleName} id) {
        return ${lastRenderResponse.repository.className?uncap_first}.findById(id)
                .map(${entity.simpleName?uncap_first} -> {
                    BeanUtils.copyProperties(form, ${entity.simpleName?uncap_first});
                    return ${lastRenderResponse.repository.className?uncap_first}.save(${entity.simpleName?uncap_first});
                });
    }

    public Optional<${entity.simpleName}> get(${entity.idClass.simpleName} id) {
        return ${lastRenderResponse.repository.className?uncap_first}.findById(id);
    }

    public List<${entity.simpleName}> list() {
        return ${lastRenderResponse.repository.className?uncap_first}.findAll();
    }

}