package net.ddns.pzshare.messanger;

import net.ddns.pzshare.KnownSenderException;

public class ConfigurationException extends KnownSenderException {
    public ConfigurationException (String text){
        super(text);
    }
}
