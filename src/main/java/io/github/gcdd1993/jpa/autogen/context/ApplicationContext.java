package io.github.gcdd1993.jpa.autogen.context;

import java.util.Map;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
public interface ApplicationContext {


    /**
     * 设置全局变量
     * @param name 变量名
     * @param value 值
     */
    void setAttribute(String name, Object value);

    String getAttribute(String name);

    <V> V getAttribute(String name, Class<V> vClass);

    <V> V getOrDefaultAttribute(String name, V defaultValue, Class<V> vClass);

    Map<String, Object> getAttributes();

    /**
     * 刷新上下文，加载配置以及资源
     */
    void refresh();

}
