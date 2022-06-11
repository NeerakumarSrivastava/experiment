package com.home.java.letcode.dp;

import org.jboss.netty.channel.UpstreamMessageEvent;

import java.util.Arrays;

public class PathConverter {

    public static void main(String[] args) {
        String a = "/a/b/../../c";
        System.out.println(new PathConverter().simplifyPath(a));
    }

    public String simplifyPath(String path) {

        path = path.replaceAll("//", "/");
        path = path.replaceAll("/\\./", "/");
        path = path.charAt(path.length() - 1) == '/' ? path.substring(0, path.length() - 1) : path;

        //  /..  -->> one step back
        //  //   -->> /

        System.out.println(path);
        String arr[] = path.split("\\.\\.");
        String previoiusPath = "/";
        for (String paths : arr) {
            if (paths.startsWith("/") && (!previoiusPath.equals("/"))) {
                previoiusPath=previoiusPath.charAt(previoiusPath.length()-1)=='/'?previoiusPath.substring(0,previoiusPath.length()-1):previoiusPath;
                previoiusPath = previoiusPath.substring(0, previoiusPath.lastIndexOf("/"))+paths;
            } else {
                if (!previoiusPath.equals("/"))
                    previoiusPath += paths;
                else
                    previoiusPath=paths;
            }
        }

        return previoiusPath;

    }


}
