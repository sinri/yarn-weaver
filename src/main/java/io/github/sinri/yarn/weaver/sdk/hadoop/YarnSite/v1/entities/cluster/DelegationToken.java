package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.cluster;

import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.vertx.core.json.JsonObject;

public class DelegationToken extends YarnEntity {

    public DelegationToken(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * The delegation token
     */
    public String Token() {
        return readString("token");
    }

    /**
     * The user who is allowed to renew the delegation token
     */
    public String Renewer() {
        return readString("renewer");
    }

    /**
     * The owner of the delegation token
     */
    public String Owner() {
        return readString("owner");
    }

    /**
     * The kind of delegation token
     */
    public String Kind() {
        return readString("kind");
    }

    /**
     * The expiration time of the token
     */
    public Long ExpirationTime() {
        return readLong("expiration-time");
    }

    /**
     * The maximum validity of the token
     */
    public Long MaxValidity() {
        return readLong("max-validity");
    }
}
