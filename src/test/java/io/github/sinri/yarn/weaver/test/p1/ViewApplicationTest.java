package io.github.sinri.yarn.weaver.test.p1;

import io.github.sinri.keel.facade.Keel;
import io.github.sinri.yarn.weaver.YarnWeaver;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.app.ClusterApplicationStateRequest;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.entities.application.ApplicationState;
import io.vertx.core.Future;

public class ViewApplicationTest extends P1Test {
    private final String testApplicationId;

    public ViewApplicationTest() {
        this.testApplicationId = Keel.config("test.application_id");

    }

    public static void main(String[] args) {
        new ViewApplicationTest().mainBody();
    }

    @Override
    protected Future<Void> run() {
        ClusterApplicationStateRequest clusterApplicationStateRequest = new ClusterApplicationStateRequest(YarnWeaver.yarnRMAddress(), this.testApplicationId);
        return clusterApplicationStateRequest.request()
                .compose(appStateResult -> {
                    ApplicationState state = appStateResult.getState();
                    getLogger().info("STATE OF " + this.testApplicationId + " IS " + state.name());
                    return Future.succeededFuture();
                });
    }
}
