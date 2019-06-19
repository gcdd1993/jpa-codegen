package io.github.gcdd1993.context;

import com.typesafe.config.Config;
import lombok.experimental.Delegate;

/**
 * 环境配置
 *
 * @author gaochen
 * Created on 2019/6/19.
 */
public class Environment {

    @Delegate
    private final Config config;

    public Environment(Config config) {
        this.config = config;
    }
}
