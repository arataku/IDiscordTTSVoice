package dev.felnull.ttsvoice.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Pattern;

public class URLUtil {
    private static final Pattern urlPattern = Pattern.compile("((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._\\+~#?&//=]*)");

    public static String replaceURLToText(String text) {
        return urlPattern.matcher(text).replaceAll(n -> getURLText(n.group()));
    }

    public static String getURLText(String url) {
        try {
            var r = getURLTitle(url);
            if (!r.isEmpty())
                return r + "のURL";
        } catch (Exception ignored) {
        }
        try {
            String[] spls = url.split("/");
            return spls[2] + "のURL";
        } catch (Exception ignored) {
        }
        return "URL省略";
    }

    public static String getURLTitle(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        return document.title();
    }
}