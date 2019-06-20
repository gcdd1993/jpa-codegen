package ${packageName};

import ${entity.packageName}.${entity.className};
import org.springframework.data.jpa.repository.JpaRepository;
<#if imports??>
<#list imports as import>
import ${import};
</#list>
</#if>

/**
 * ${comments}
 *
 * @author ${author}
 * Created On ${date}.
 */
public interface ${className} extends JpaRepository<${entity.className}, ${entity.idClassName}> {
}