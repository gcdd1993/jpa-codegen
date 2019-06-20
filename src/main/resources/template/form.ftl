package ${packageName};

import lombok.Data;

/**
 * ${comments}
 *
 * @author ${author}
 * Created On ${date}.
 */
@Data
public class ${className} {

    <#list fields as field>
    private ${field.className} ${field.name};
    </#list>

}