package io.github.gcdd1993.jpa.autogen.metadata;

/**
 * 实体解析器
 *
 * @author gaochen
 * Created on 2019/6/21.
 */
public interface IEntityParser {

    /**
     * 将指定实体类解析为实体信息
     *
     * @param clazz 实体类
     * @return 实体信息
     */
    EntityInfo parse(Class<?> clazz);
}
