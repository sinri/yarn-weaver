package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.application;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class ApplicationTimeout extends YarnEntity {


    public ApplicationTimeout(JsonObject jsonObject) {
        super(jsonObject);
    }


    /**
     * Valid values are the members of the ApplicationTimeoutType enum. LIFETIME is currently the only valid value.
     *
     * @return Timeout type.
     */
    public ApplicationTimeoutType type() {
        return ApplicationTimeoutType.valueOf(readString("type"));
    }

    /**
     * If UNLIMITED, then application will run forever.
     *
     * @return Time at which the application will expire in ISO8601 yyyy-MM-dd’T’HH:mm:ss.SSSZ format.
     */
    public String expiryTime() {
        return readString("expiryTime");
    }

    /**
     * -1 indicates that application is not configured with timeout. Zero(0) indicates that application has expired with configured timeout type.
     *
     * @return Remaining time for configured application timeout.
     */
    public Long remainingTimeInSeconds() {
        return readLong("remainingTimeInSeconds");
    }
}
