package io.github.gcdd1993.jpa.autogen.context;

import io.github.gcdd1993.jpa.autogen.constant.AttributeKey;
import io.github.gcdd1993.jpa.autogen.model.EntityInfo;
import io.github.gcdd1993.jpa.autogen.util.ReflectUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/18.
 */
@RequiredArgsConstructor
public class DefaultApplicationContext implements ApplicationContext {

    @Getter
    private final Map<String, Object> attributes = new ConcurrentHashMap<>(256);

    public DefaultApplicationContext(String configLocation) throws IOException {
        this(DefaultApplicationContext.class.getResourceAsStream(configLocation));
    }

    public DefaultApplicationContext(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);

        // move all properties into application context attributes
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            attributes.put(key.toString().replace(".", "_"), value);
        }

        refresh();
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public String getAttribute(String name) {
        return getAttribute(name, String.class);
    }

    @Override
    public <V> V getAttribute(String name, Class<V> vClass) {
        return vClass.cast(attributes.get(name));
    }

    @Override
    public <V> V getOrDefaultAttribute(String name, V defaultValue, Class<V> vClass) {
        return vClass.cast(attributes.getOrDefault(name, defaultValue));
    }

    @Override
    public void refresh() {
        refreshEntityInfo();
    }

    private void refreshEntityInfo() {
        List<Class<?>> entityClassList = ReflectUtils.getClassListByAnnotation(getAttribute(AttributeKey.ENTITY_PACKAGE, String.class), Entity.class);

        List<EntityInfo> entityInfos = entityClassList.stream().map(EntityInfo::buildFromEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        setAttribute(AttributeKey.ENTITY_INFOS, entityInfos);
    }

}
