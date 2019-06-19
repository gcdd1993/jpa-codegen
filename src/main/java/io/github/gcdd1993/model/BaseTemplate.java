package io.github.gcdd1993.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/19.
 */
@Data
public abstract class BaseTemplate {

    /**
     * 作者
     */
    protected String author;

    /**
     * 日期
     */
    protected String date;

    /**
     * 包名
     */
    protected String packageName;

    /**
     * 类名
     */
    protected String className;

    /**
     * 导入的全类名
     */
    protected List<String> imports;

    /**
     * ftl模板文件位置
     */
    protected String template;

    /**
     * 实体信息
     */
    protected EntityInfo entityInfo;

    /**
     * 解析为模板参数
     *
     * @return 模板参数
     */
    public abstract Map<String, Object> toParams();

}
