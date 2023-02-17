package io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.ResourceManager.cluster.apps;

import io.github.sinri.keel.core.json.JsonifiableEntity;
import io.github.sinri.keel.core.json.SimpleJsonifiableEntity;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.YarnEntity;
import io.github.sinri.yarn.weaver.sdk.hadoop.YarnSite.v1.YarnSiteV1Request;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The Submit Applications API can be used to submit applications.
 * In case of submitting applications, you must first obtain an application-id using the Cluster New Application API.
 * The application-id must be part of the request body.
 * The response contains a URL to the application page which can be used to track the state and progress of your application.
 */
public class ClusterSubmitApplicationRequest extends YarnSiteV1Request<ClusterSubmitApplicationRequest.SubmitDone> {
    private Consumer<ApplicationDataToSubmit> editor;

    /**
     * The version of the APIs is `v1`.
     *
     * @param serviceAddress The http address of the service to get information about.
     *                       Currently supported are the ResourceManager, NodeManager,
     *                       MapReduce application master, and history server.
     */
    public ClusterSubmitApplicationRequest(String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * @return A path that defines a singleton resource or a collection of resources.
     */
    @Override
    protected String resourcePath() {
        return "cluster/apps";
    }

    public Consumer<ApplicationDataToSubmit> getEditor() {
        return editor;
    }

    public ClusterSubmitApplicationRequest setEditor(@NotNull Consumer<ApplicationDataToSubmit> editor) {
        this.editor = editor;
        return this;
    }

    /**
     * POST requests can be used to submit apps to the ResourceManager.
     * As mentioned above, an application-id must be obtained first.
     * Successful submissions result in a 202 response code and a Location header specifying where to get information about the app.
     * Please note that in order to submit an app, you must have an authentication filter setup for the HTTP interface.
     * The functionality requires that a username is set in the HttpServletRequest.
     * If no filter is setup, the response will be an “UNAUTHORIZED” response.
     * Please note that this feature is currently in the alpha stage and may change in the future.
     *
     * @return always future of null for success.
     */
    @Override
    public Future<SubmitDone> request() {
        ApplicationDataToSubmit applicationDataToSubmit = new ApplicationDataToSubmit();
        editor.accept(applicationDataToSubmit);

        return requestWithPost(applicationDataToSubmit.toJsonObject(), 202)
                .compose(jsonObject -> {
                    return Future.succeededFuture(new SubmitDone(new JsonObject()));
                });
    }

    public static class SubmitDone extends YarnEntity {

        public SubmitDone(JsonObject jsonObject) {
            super(jsonObject);
        }
    }

    public static class ApplicationDataToSubmit extends SimpleJsonifiableEntity {

        public ApplicationDataToSubmit setApplicationId(String applicationId) {
            this.jsonObject.put("application-id", applicationId);
            return this;
        }

        public ApplicationDataToSubmit setApplicationName(String applicationName) {
            this.jsonObject.put("application-name", applicationName);
            return this;
        }

        /**
         * @param queue The name of the queue to which the application should be submitted
         */
        public ApplicationDataToSubmit setQueue(String queue) {
            this.jsonObject.put("queue", queue);
            return this;
        }

        /**
         * @param priority The priority of the application
         */
        public ApplicationDataToSubmit setPriority(Integer priority) {
            this.jsonObject.put("priority", priority);
            return this;
        }

        /**
         * @param amContainerSpec The application master container launch context, described below
         */
        public ApplicationDataToSubmit setAMContainerSpec(AMContainerSpec amContainerSpec) {
            this.jsonObject.put("am-container-spec", amContainerSpec.toJsonObject());
            return this;
        }

        /**
         * @param unmanagedAM Is the application using an unmanaged application master
         */
        public ApplicationDataToSubmit setUnmanagedAM(Boolean unmanagedAM) {
            this.jsonObject.put("unmanaged-AM", unmanagedAM);
            return this;
        }

        /**
         * @param maxAppAttempts The max number of attempts for this application
         */
        public ApplicationDataToSubmit setMaxAppAttempts(Integer maxAppAttempts) {
            this.jsonObject.put("max-app-attempts", maxAppAttempts);
            return this;
        }

        /**
         * @param resource The resources the application master requires, described below
         */
        public ApplicationDataToSubmit setResource(Resource resource) {
            this.jsonObject.put("resource", resource.toJsonObject());
            return this;
        }

        /**
         * @param applicationType The application type(MapReduce, Pig, Hive, etc)
         */
        public ApplicationDataToSubmit setApplicationType(String applicationType) {
            this.jsonObject.put("application-type", applicationType);
            return this;
        }

        /**
         * @param keepContainersAcrossApplicationAttempts Should YARN keep the containers used by this application instead of destroying them
         */
        public ApplicationDataToSubmit setKeepContainersAcrossApplicationAttempts(Boolean keepContainersAcrossApplicationAttempts) {
            this.jsonObject.put("keep-containers-across-application-attempts", keepContainersAcrossApplicationAttempts);
            return this;
        }

        /**
         * @param applicationTags List of application tags, please see the request examples on how to specify the tags
         *                        But there is not anything about it in sample.
         */
        public ApplicationDataToSubmit setApplicationTags(JsonifiableEntity<?> applicationTags) {
            this.jsonObject.put("application-tags", applicationTags.toJsonObject());
            return this;
        }

        /**
         * @param logAggregationContext Represents all of the information needed by the NodeManager to handle the logs for this application
         */
        public ApplicationDataToSubmit setLogAggregationContext(LogAggregationContext logAggregationContext) {
            this.jsonObject.put("log-aggregation-context", logAggregationContext.toJsonObject());
            return this;
        }

        /**
         * @param attemptFailuresValidityInterval The failure number will no take attempt failures which happen out of the validityInterval into failure count
         */
        public ApplicationDataToSubmit setAttemptFailuresValidityInterval(Long attemptFailuresValidityInterval) {
            this.jsonObject.put("attempt-failures-validity-interval", attemptFailuresValidityInterval);
            return this;
        }

        /**
         * @param reservationId Represent the unique id of the corresponding reserved resource allocation in the scheduler
         */
        public ApplicationDataToSubmit setReservationId(String reservationId) {
            this.jsonObject.put("reservation-id", reservationId);
            return this;
        }

        /**
         * @param amBlackListingRequests Contains blacklisting information such as “enable/disable AM blacklisting” and “disable failure threshold”
         */
        public ApplicationDataToSubmit setAMBlackListingRequests(AMBlackListingRequests amBlackListingRequests) {
            this.jsonObject.put("am-black-listing-requests", amBlackListingRequests.toJsonObject());
            return this;
        }

        public static class AMContainerSpec extends SimpleJsonifiableEntity {
            /**
             * Object describing the resources that need to be localized, described below
             */
            public AMContainerSpec setLocalResources(@Nullable List<LocalResourcesEntry> entries) {
                JsonArray array = new JsonArray();

                if (entries != null) {
                    entries.forEach(entry -> {
                        array.add(entry.toJsonObject());
                    });
                }

                this.jsonObject.put("local-resources", new JsonObject()
                        .put("entry", array));

                return this;
            }

            /**
             * Environment variables for your containers, specified as key value pairs
             */
            public AMContainerSpec setEnvironment(@Nullable Map<String, String> map) {
                JsonArray array = new JsonArray();
                if (map != null) {
                    map.forEach((k, v) -> {
                        array.add(new JsonObject().put("key", k).put("value", v));
                    });
                }
                this.jsonObject.put("environment", new JsonObject()
                        .put("entry", array));
                return this;
            }

            /**
             * The commands for launching your container, in the order in which they should be executed
             *
             * @param command in sample, for a single command
             */
            public AMContainerSpec setCommand(@NotNull String command) {
                this.jsonObject.put("commands", new JsonObject()
                        .put("command", command));
                return this;
            }

            /**
             * The commands for launching your container, in the order in which they should be executed
             */
            public AMContainerSpec setCommand(@Nullable Collection<String> commands) {
                JsonArray array = new JsonArray();
                if (commands != null) {
                    commands.forEach(array::add);
                }
                this.jsonObject.put("commands", new JsonObject()
                        .put("command", array));
                return this;
            }

            /**
             * Application specific service data;
             * key is the name of the auxiliary service,
             * value is base-64 encoding of the data you wish to pass
             */
            public AMContainerSpec setServiceData(@Nullable Map<String, String> map) {
                JsonArray array = new JsonArray();
                if (map != null) {
                    map.forEach((k, v) -> {
                        array.add(new JsonObject().put("key", k).put("value", v));
                    });
                }
                this.jsonObject.put("service-data", new JsonObject()
                        .put("entry", array));
                return this;
            }

            /**
             * The credentials required for your application to run, described below
             * todo: this comes without any sample given
             */
            public AMContainerSpec setCredentials(@Nullable Collection<Credential> credentials) {
                JsonArray array = new JsonArray();
                if (credentials != null) {
                    credentials.forEach(credential -> {
                        array.add(credential.toJsonObject());
                    });
                }
                this.jsonObject.put("credentials", new JsonObject()
                        .put("entry", array));
                return this;
            }

            /**
             * ACLs for your application;
             * the key can be “VIEW_APP” or “MODIFY_APP”,
             * the value is the list of users with the permissions
             */
            public AMContainerSpec setApplicationACLs(@Nullable Collection<ApplicationACL> applicationACLs) {
                JsonArray array = new JsonArray();
                if (applicationACLs != null) {
                    applicationACLs.forEach(x -> {
                        array.add(x.toJsonObject());
                    });
                }
                this.jsonObject.put("application-acls", new JsonObject()
                        .put("entry", array));
                return this;
            }

            public static class LocalResourcesEntry extends SimpleJsonifiableEntity {
                public LocalResourcesEntry(String key) {
                    super();
                    this.jsonObject.put("key", key).put("value", new JsonObject());
                }

                /**
                 * @param resource Location of the resource to be localized
                 */
                public LocalResourcesEntry setResource(String resource) {
                    this.jsonObject.getJsonObject("value").put("resource", resource);
                    return this;
                }

                /**
                 * @param type Type of the resource; options are “ARCHIVE”, “FILE”, and “PATTERN”
                 */
                public LocalResourcesEntry setType(String type) {
                    this.jsonObject.getJsonObject("value").put("type", type);
                    return this;
                }

                /**
                 * @param visibility Visibility the resource to be localized; options are “PUBLIC”, “PRIVATE”, and “APPLICATION”
                 */
                public LocalResourcesEntry setVisibility(String visibility) {
                    this.jsonObject.getJsonObject("value").put("visibility", visibility);
                    return this;
                }

                /**
                 * @param size Size of the resource to be localized
                 */
                public LocalResourcesEntry setSize(Long size) {
                    this.jsonObject.getJsonObject("value").put("size", size);
                    return this;
                }

                /**
                 * @param timestamp Timestamp of the resource to be localized
                 */
                public LocalResourcesEntry setTimestamp(Long timestamp) {
                    this.jsonObject.getJsonObject("value").put("timestamp", timestamp);
                    return this;
                }

            }

            public static class Credential extends SimpleJsonifiableEntity {
                // todo without sample
                // tokens	object	Tokens that you wish to pass to your application, specified as key-value pairs. The key is an identifier for the token and the value is the token(which should be obtained using the respective web-services)
                // secrets	object	Secrets that you wish to use in your application, specified as key-value pairs. They key is an identifier and the value is the base-64 encoding of the secret
            }

            public static class ApplicationACL extends SimpleJsonifiableEntity {
                public static final String KEY_VIEW_APP = "VIEW_APP";
                public static final String KEY_MODIFY_APP = "MODIFY_APP";

                public ApplicationACL(String key, Collection<String> permissionHolders) {
                    super();
                    // todo without sample
                }

            }
        }

        public static class Resource extends SimpleJsonifiableEntity {
            /**
             * @param memory Memory required for each container
             * @param vCores Virtual cores required for each container
             */
            public Resource(int memory, int vCores) {
                super();
                this.toJsonObject().put("memory", memory).put("vCores", vCores);
            }
        }

        public static class LogAggregationContext extends SimpleJsonifiableEntity {

            /**
             * @param LogIncludePattern String
             */
            public LogAggregationContext setLogIncludePattern(String LogIncludePattern) {
                this.jsonObject.put("log-include-pattern", LogIncludePattern);
                return this;
            }

            /**
             * @param LogExcludePattern String
             */
            public LogAggregationContext setLogExcludePattern(String LogExcludePattern) {
                this.jsonObject.put("log-exclude-pattern", LogExcludePattern);
                return this;
            }

            /**
             * @param RolledLogIncludePattern String
             */
            public LogAggregationContext setRolledLogIncludePattern(String RolledLogIncludePattern) {
                this.jsonObject.put("rolled-log-include-pattern", RolledLogIncludePattern);
                return this;
            }

            /**
             * @param RolledLogExcludePattern String
             */
            public LogAggregationContext setRolledLogExcludePattern(String RolledLogExcludePattern) {
                this.jsonObject.put("rolled-log-exclude-pattern", RolledLogExcludePattern);
                return this;
            }

            /**
             * @param LogAggregationPolicyClassName String
             */
            public LogAggregationContext setLogAggregationPolicyClassName(String LogAggregationPolicyClassName) {
                this.jsonObject.put("log-aggregation-policy-class-name", LogAggregationPolicyClassName);
                return this;
            }

            /**
             * @param LogAggregationPolicyParameters String
             */
            public LogAggregationContext setLogAggregationPolicyParameters(String LogAggregationPolicyParameters) {
                this.jsonObject.put("log-aggregation-policy-parameters", LogAggregationPolicyParameters);
                return this;
            }
        }

        public static class AMBlackListingRequests extends SimpleJsonifiableEntity {
            /**
             * @param amBlackListingEnabled   Whether AM Blacklisting is enabled
             * @param disableFailureThreshold AM Blacklisting disable failure threshold
             */
            public AMBlackListingRequests(boolean amBlackListingEnabled, float disableFailureThreshold) {
                super();
                this.toJsonObject()
                        .put("am-black-listing-enabled", amBlackListingEnabled)
                        .put("disable-failure-threshold", disableFailureThreshold);
            }
        }
    }
}
