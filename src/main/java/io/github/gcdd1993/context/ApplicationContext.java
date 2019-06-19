package io.github.gcdd1993.context;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
public interface ApplicationContext {

    /**
     * 运行时环境
     *
     * @return 环境
     */
    Environment getEnvironment();

    /**
     * 获取全局变量
     *
     * @param name 名称
     * @return 值
     */
    Object getAttribute(String name);

    /**
     * 设置全局变量
     *
     * @param name  名称
     * @param value 值
     */
    void setAttribute(String name, Object value);

    /**
     * 刷新上下文，加载配置以及资源
     */
    void refresh();

}
