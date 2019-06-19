package io.github.gcdd1993.model;

import lombok.Data;

/**
 * 实体信息
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
@Data
public class EntityInfo {

    /**
     * 实体全类名
     */
    private String fullName;

    /**
     * 实体类名
     */
    private String simpleName;

    /**
     * 主键全类名
     */
    private String idClass;

    /**
     * 包名
     */
    private String packageName;

}
