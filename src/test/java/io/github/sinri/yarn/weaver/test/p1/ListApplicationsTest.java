package io.github.sinri.yarn.weaver.test.p1;

import io.github.sinri.yarn.weaver.YarnWeaver;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps.ClusterApplicationsRequest;
import io.vertx.core.Future;

public class ListApplicationsTest extends P1Test {
    public static void main(String[] args) {
        new ListApplicationsTest().mainBody();
    }

    @Override
    protected Future<Void> run() {
        return new ClusterApplicationsRequest(YarnWeaver.yarnRMAddress())
                .request()
                .compose(applications -> {
                    getLogger().info("applications", applications.toJsonObject());
                    return Future.succeededFuture();
                });
    }
}
