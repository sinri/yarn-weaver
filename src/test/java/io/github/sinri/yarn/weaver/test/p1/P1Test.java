package io.github.sinri.yarn.weaver.test.p1;

import io.github.sinri.keel.facade.Keel;
import io.github.sinri.keel.helper.KeelHelpers;
import io.github.sinri.keel.logger.event.KeelEventLog;
import io.github.sinri.keel.logger.event.KeelEventLogger;
import io.github.sinri.keel.logger.event.center.KeelOutputEventLogCenter;
import io.vertx.core.Future;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonArray;

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
                    getLogger().exception(throwable, "test failed");
                })
                .eventually(v -> {
                    return Keel.getVertx().close();
                });
    }
}
