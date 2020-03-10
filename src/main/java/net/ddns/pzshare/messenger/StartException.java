package net.ddns.pzshare.messenger;

import net.ddns.pzshare.KnownSenderException;

public class StartException extends KnownSenderException {
    public StartException(String text) {
        super(text);
    }
}
