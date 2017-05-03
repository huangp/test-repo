package com.github.huangp.app;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Patrick Huang <a href="mailto:pahuang@redhat.com">pahuang@redhat.com</a>
 */
public class ResourceLoader {

    private final ResourceBundle messages;

    public ResourceLoader(String localeCode) {
        Locale locale;
        if (localeCode == null || localeCode.isEmpty()) {
            locale = Locale.ENGLISH;
        } else {
            locale = new Locale(localeCode);
        }
        messages = ResourceBundle.getBundle("messages", locale);
    }

    public String welcomeText() {
        return messages.getString("greeting");
    }

    public String titleText() {
        return messages.getString("title");
    }
}
