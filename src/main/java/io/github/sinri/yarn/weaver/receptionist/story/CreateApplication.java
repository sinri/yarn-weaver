package io.github.sinri.yarn.weaver.receptionist.story;

import io.github.sinri.keel.web.http.ApiMeta;
import io.github.sinri.yarn.weaver.YarnWeaver;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.ClusterNewApplicationRequest;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.ClusterSubmitApplicationRequest;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Objects;

@ApiMeta(routePath = "/story/create-application")
public class CreateApplication extends StoryReceptionist {
    public CreateApplication(RoutingContext routingContext) {
        super(routingContext);
    }

    @Override
    protected Future<Object> handleForFuture() {
        // ApplicationDataToSubmit except application_id
        JsonObject application = this.getRoutingContext().body().asJsonObject().getJsonObject("application");
        Objects.requireNonNull(application, "application is null");

        return new ClusterNewApplicationRequest(YarnWeaver.yarnRMAddress())
                .request()
                .compose(meta -> {
                    getLogger().info(eventLog -> eventLog
                            .message("applied an application")
                            .put("application_id", meta.applicationId())
                            .put("meta.maximumResourceCapabilities", meta.maximumResourceCapabilities().toJsonObject())
                    );

                    return new ClusterSubmitApplicationRequest(YarnWeaver.yarnRMAddress())
                            .setEditor(form -> {
                                form.reloadDataFromJsonObject(application);
                                form.setApplicationId(meta.applicationId());
                            })
                            .request()
                            .compose(done -> {
                                return Future.succeededFuture(meta.toJsonObject());
                            });
                });
    }


}
