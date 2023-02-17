package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite;

import io.github.sinri.keel.core.json.UnmodifiableJsonifiableEntity;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;

public class YarnEntity implements UnmodifiableJsonifiableEntity {
    private final JsonObject jsonObject;

    public YarnEntity(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @NotNull
    @Override
    public JsonObject toJsonObject() {
        return this.jsonObject;
    }
}
