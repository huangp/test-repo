package com.github.huangp;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Locale locale;
        if (args == null || args.length == 0) {
            locale = Locale.ENGLISH;
        } else {
            locale = new Locale(args[0]);
        }
        ResourceBundle messages =
                ResourceBundle.getBundle("messages", locale);



        System.out.println(messages.getString("greeting"));
        Enumeration<String> keys = messages.getKeys();
        while (keys.hasMoreElements()) {
            String nextKey = keys.nextElement();
            if (!nextKey.equals("greeting") || !nextKey.equals("farewell")) {
                System.out.println(messages.getString(nextKey));
            }
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("oops");
        }
        System.out.println(messages.getString("farewell"));
    }
}
