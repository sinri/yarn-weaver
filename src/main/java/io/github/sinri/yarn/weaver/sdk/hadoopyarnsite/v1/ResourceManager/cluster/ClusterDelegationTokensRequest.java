package io.github.sinri.yarn.weaver.sdk.hadoopyarnsite.v1.ResourceManager.cluster;

/**
 * The Delegation Tokens API can be used to create, renew and cancel YARN ResourceManager delegation tokens.
 * All delegation token requests must be carried out on a Kerberos authenticated connection(using SPNEGO).
 * Carrying out operations on a non-kerberos connection will result in a FORBIDDEN response.
 * In case of renewing a token, only the renewer specified when creating the token can renew the token.
 * Other users(including the owner) are forbidden from renewing tokens.
 * It should be noted that when cancelling or renewing a token, the token to be cancelled or renewed is specified by setting a header.
 * <p>
 * This feature is currently in the alpha stage and may change in the future.
 * <p>
 * LATER TO USE TOKEN AS HEADER `X-Hadoop-Delegation-Token: MgASY2xpZW50QEVYQU1QTEUuQ09NDHRlc3QtcmVuZXdlcgCKAUbjqcHHigFHB7ZFxwQCFKWD3znCkDSy6SQIjRCLDydxbxvgE1JNX0RFTEVHQVRJT05fVE9LRU4A`
 */
public class ClusterDelegationTokensRequest {
    // todo next wave
}
