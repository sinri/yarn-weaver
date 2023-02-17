package io.github.sinri.yarn.weaver.receptionist.story;

import io.github.sinri.keel.web.http.ApiMeta;
import io.github.sinri.yarn.weaver.YarnWeaver;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.app.ClusterApplicationStateRequest;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

@ApiMeta(routePath = "/story/query-status-of-application")
public class QueryStateOfApplication extends StoryReceptionist {
    public QueryStateOfApplication(RoutingContext routingContext) {
        super(routingContext);
    }

    @Override
    protected Future<Object> handleForFuture() {
        String applicationId = getRoutingContext().body().asJsonObject().getString("application_id");

        return new ClusterApplicationStateRequest(YarnWeaver.yarnRMAddress(), applicationId)
                .request()
                .compose(appStatusResult -> {
                    return Future.succeededFuture(new JsonObject()
                            .put("state", appStatusResult.getState()));
                });
    }
}
