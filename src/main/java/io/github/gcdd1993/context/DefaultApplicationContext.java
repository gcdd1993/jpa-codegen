package io.github.gcdd1993.context;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
@RequiredArgsConstructor
public class DefaultApplicationContext implements ApplicationContext {

    public DefaultApplicationContext(String configLocation) {
        Config config = ConfigFactory.load(configLocation);
        this.environment = new Environment(config);
    }

    private Map<String, Object> attributes = new ConcurrentHashMap<>();

    @Getter
    private final Environment environment;

    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public void refresh() {
        prepareRefresh();


    }

    private void prepareRefresh() {

    }


}
