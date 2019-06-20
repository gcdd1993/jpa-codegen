package ${packageName};

import ${entity.packageName}.${entity.className};
import ${lastRenderResponse.form.packageName}.${lastRenderResponse.form.className};
import ${lastRenderResponse.service.packageName}.${lastRenderResponse.service.className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ${comments}
 *
 * @author ${author}
 * Created On ${date}.
 */
@RestController
@RequestMapping("/${entity.className?uncap_first}")
public class ${className} {

    @Autowired
    private ${lastRenderResponse.service.className} ${lastRenderResponse.service.className?uncap_first};

    @PostMapping
    public ${entity.className} create(${lastRenderResponse.form.className} form) {
        return ${lastRenderResponse.service.className?uncap_first}.create(form);
    }

    @DeleteMapping
    public void delete(${entity.idClassName} id) {
        ${lastRenderResponse.service.className?uncap_first}.delete(id);
    }

    @PutMapping("/{id}")
    public Optional<${entity.className}> update(@RequestBody ${lastRenderResponse.form.className} form,
                                 @PathVariable("id") ${entity.idClassName} id) {
        return ${lastRenderResponse.service.className?uncap_first}.update(form, id);
    }

    @GetMapping("/{id}")
    public Optional<${entity.className}> get(${entity.idClassName} id) {
        return ${lastRenderResponse.service.className?uncap_first}.get(id);
    }

    @GetMapping
    public List<${entity.className}> list() {
        return ${lastRenderResponse.service.className?uncap_first}.list();
    }

}