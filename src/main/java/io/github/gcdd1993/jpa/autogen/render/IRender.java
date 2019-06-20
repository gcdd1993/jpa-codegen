package io.github.gcdd1993.jpa.autogen.render;

import io.github.gcdd1993.jpa.autogen.model.EntityInfo;

/**
 * TODO
 *
 * @author gaochen
 * Created on 2019/6/20.
 */
public interface IRender {
    RenderingResponse render(EntityInfo entityInfo, String module);
}
