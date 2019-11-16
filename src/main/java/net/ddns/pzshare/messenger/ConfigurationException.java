package net.ddns.pzshare.messenger;

import net.ddns.pzshare.KnownSenderException;

public class ConfigurationException extends KnownSenderException {
    public ConfigurationException (String text){
        super(text);
    }
}
