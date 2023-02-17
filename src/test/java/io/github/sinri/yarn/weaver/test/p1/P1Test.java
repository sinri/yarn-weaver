package io.github.sinri.yarn.weaver.test.p1;

import io.github.sinri.keel.facade.Keel;
import io.github.sinri.keel.logger.event.KeelEventLogger;
import io.github.sinri.keel.logger.event.center.KeelOutputEventLogCenter;
import io.vertx.core.Future;
import io.vertx.core.VertxOptions;

abstract public class P1Test {
    public static void init() {
        Keel.getConfiguration().loadPropertiesFile("config.properties");
        Keel.initializeVertxStandalone(new VertxOptions());
    }

    protected KeelEventLogger getLogger() {
        return KeelOutputEventLogCenter.getInstance().createLogger(this.getClass().getSimpleName());
    }

    abstract protected Future<Void> run();

    protected void mainBody() {
        init();
        run()
                .onSuccess(done -> {
                    getLogger().info("test done");
                })
                .onFailure(throwable -> {
                    getLogger().exception(throwable, "test failed");
                });
    }
}
