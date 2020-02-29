package com.redhat.developers;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@ConfigurationProperties("enmasse")
public class EnmasseProperties {

    private static final String AMQPS_STRING_FORMAT = "amqps://%s:%d?jms.username=%s&jms.password=%s&transport.verifyHost=false&transport.trustAll=true&amqp.saslMechanisms=PLAIN";

    private String externalMessagingHost;

    private int externalMessagingPort;

    private String messagingHost;

    private int messagingAmqpsPort;

    private String username;

    private String password;

    public String toJmsRemoteURI() {
        if (isRunningOnKubernetes()) {
            return String.format(AMQPS_STRING_FORMAT, messagingHost, messagingAmqpsPort, username, password);
        } else {
            return String.format(AMQPS_STRING_FORMAT, externalMessagingHost, externalMessagingPort, username, password);
        }
    }

    static boolean isRunningOnKubernetes() {
        return new File("/var/run/secrets/kubernetes.io/serviceaccount").exists();
    }

    public String getExternalMessagingHost() {
        return externalMessagingHost;
    }

    public void setExternalMessagingHost(String externalMessagingHost) {
        this.externalMessagingHost = externalMessagingHost;
    }

    public int getExternalMessagingPort() {
        return externalMessagingPort;
    }

    public void setExternalMessagingPort(int externalMessagingPort) {
        this.externalMessagingPort = externalMessagingPort;
    }

    public String getMessagingHost() {
        return messagingHost;
    }

    public void setMessagingHost(String messagingHost) {
        this.messagingHost = messagingHost;
    }

    public int getMessagingAmqpsPort() {
        return messagingAmqpsPort;
    }

    public void setMessagingAmqpsPort(int messagingAmqpsPort) {
        this.messagingAmqpsPort = messagingAmqpsPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
