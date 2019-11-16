package net.ddns.pzshare.messenger;

public interface WorkerCfg {
    default boolean validateEmpty(String value){
        return (value == null || value.isEmpty());
    }
}
