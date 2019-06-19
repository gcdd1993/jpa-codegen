package io.github.gcdd1993.generator;

import io.github.gcdd1993.model.BaseTemplate;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
public interface ICodeGenerator {

    /**
     * 执行代码生成
     *
     * @param template 模板
     */
    void generate(BaseTemplate template);

}
