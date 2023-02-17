The Delegation Tokens API can be used to create, renew and cancel YARN ResourceManager delegation tokens. All delegation
token requests must be carried out on a Kerberos authenticated connection(using SPNEGO). Carrying out operations on a
non-kerberos connection will result in a FORBIDDEN response. In case of renewing a token, only the renewer specified
when creating the token can renew the token. Other users(including the owner) are forbidden from renewing tokens. It
should be noted that when cancelling or renewing a token, the token to be cancelled or renewed is specified by setting a
header.

This feature is currently in the alpha stage and may change in the future.

