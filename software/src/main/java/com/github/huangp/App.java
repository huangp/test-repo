package com.github.huangp;

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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("oops");
        }
        System.out.println(messages.getString("farewell"));
    }
}
