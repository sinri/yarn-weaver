package io.github.sinri.yarn.weaver.test.p1;

import io.github.sinri.keel.facade.Keel;
import io.github.sinri.keel.helper.KeelHelpers;
import io.github.sinri.keel.logger.event.KeelEventLog;
import io.github.sinri.keel.logger.event.KeelEventLogger;
import io.github.sinri.keel.logger.event.center.KeelOutputEventLogCenter;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnSiteRemoteException;
import io.vertx.core.Future;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.Arrays;
import java.util.Set;

abstract public class P1Test {
    private final KeelEventLogger logger;

    public P1Test() {
        logger = KeelOutputEventLogCenter.getInstance(KeelEventLog::render)
                .createLogger(this.getClass().getSimpleName(), (eventLog) -> {
                    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                    if (stackTrace.length <= 5) {
                        eventLog.put("stack", null);
                    } else {
                        JsonArray array = new JsonArray();
                        StackTraceElement[] slice = Arrays.copyOfRange(stackTrace, 5, stackTrace.length);
                        KeelHelpers.jsonHelper().filterStackTrace(slice, mixedStackPrefixSet, (currentPrefix, ps) -> {
                            array.add(currentPrefix + " × " + ps);
                        }, (stackTraceElement) -> {
                            array.add(stackTraceElement.toString());
                        });
                        eventLog.put("stack", array);
                    }

                });
    }

    public static void init() {
        Keel.getConfiguration().loadPropertiesFile("config.properties");
        Keel.initializeVertxStandalone(new VertxOptions());
    }

    private static final Set<String> mixedStackPrefixSet = Set.of("io.vertx.", "java.", "io.netty.");

    protected KeelEventLogger getLogger() {
        return logger;
    }

    abstract protected Future<Void> run();

    protected void mainBody() {
        init();
        run()
                .onSuccess(done -> {
                    getLogger().info("test done");
                })
                .onFailure(throwable -> {
                    if (throwable instanceof YarnSiteRemoteException) {
                        YarnSiteRemoteException e = (YarnSiteRemoteException) throwable;
                        getLogger().exception(e, "YarnSiteRemoteException", new JsonObject()
                                .put("code", e.getResponseCode())
                                .put("message", e.getExceptionDetailedMessage())
                                .put("class", e.getExceptionJavaClassName())
                                .put("type", e.getExceptionType())
                        );
                    } else {
                        getLogger().exception(throwable, "");
                    }
                    getLogger().fatal("test failed");
                })
                .eventually(v -> {
                    return Keel.getVertx().close();
                });
    }
}
