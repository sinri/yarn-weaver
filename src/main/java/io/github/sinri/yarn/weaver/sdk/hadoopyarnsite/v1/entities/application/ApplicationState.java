package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.entities.application;

/**
 * valid values are members of the YarnApplicationState enum
 */
public enum ApplicationState {
    NEW, NEW_SAVING, SUBMITTED, ACCEPTED, RUNNING, FINISHED, FAILED, KILLED
}
