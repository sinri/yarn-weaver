package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application;

import io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class ApplicationStatItem extends YarnEntity {

    public ApplicationStatItem(JsonObject jsonObject) {
        super(jsonObject);
    }

    public String state() {
        return readString("state");
    }

    public String type() {
        return readString("type");
    }

    public Integer count() {
        return readInteger("count");
    }
}
