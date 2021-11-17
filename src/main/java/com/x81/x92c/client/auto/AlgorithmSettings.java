package com.x81.x92c.client.auto;

public class AlgorithmSettings {
    private static AlgorithmSettings single_instance = null;
    private AlgorithmSettings () {}

    public static AlgorithmSettings getInstance() {
        if (single_instance == null)
            single_instance = new AlgorithmSettings();

        return single_instance;
    }

    public boolean ignoreFovLimiter = false;
    public boolean ignoreReachLimiter = false;
    public boolean debugOverrideLock = false;

    public void hypermode () {
        ignoreFovLimiter = true;
        ignoreReachLimiter = true;
    }

    public void panic () {
        ignoreFovLimiter = false;
        ignoreReachLimiter = false;
    }

    public boolean isHypermode () {
        return ignoreReachLimiter &&
                ignoreFovLimiter;
    }
}
