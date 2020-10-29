package com.checkers.board;

import java.net.URL;

public class LinkToResources {

    public static String getPath(String pictureName) {
        ClassLoader classLoader = LinkToResources.class.getClassLoader();

        URL resource = classLoader.getResource(pictureName);

        if (resource == null) throw new AssertionError();
        return resource.getProtocol() + ":" + resource.getPath();
    }
}
