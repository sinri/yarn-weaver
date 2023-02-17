package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite;

import io.vertx.core.json.JsonObject;
import org.jetbrains.annotations.Nullable;

public class YarnSiteRemoteException extends Exception {
    protected int responseCode;

    private String exceptionJavaClassName = null;
    private String exceptionType = null;
    private String exceptionDetailedMessage = null;

    public YarnSiteRemoteException(int responseCode, @Nullable JsonObject responseAsJsonObject) {
        this.responseCode = responseCode;

        if (responseAsJsonObject != null) {
            JsonObject rawRemoteException = responseAsJsonObject.getJsonObject("RemoteException");
            if (rawRemoteException != null) {
                exceptionJavaClassName = rawRemoteException.getString("javaClassName");
                exceptionType = rawRemoteException.getString("exception");
                exceptionDetailedMessage = rawRemoteException.getString("message");
            }
        }
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getExceptionDetailedMessage() {
        return exceptionDetailedMessage;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getExceptionJavaClassName() {
        return exceptionJavaClassName;
    }
}
