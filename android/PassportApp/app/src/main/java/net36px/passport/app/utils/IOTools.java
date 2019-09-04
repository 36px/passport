package net36px.passport.app.utils;

import java.io.Closeable;

public final class IOTools {

    public static void close(Closeable obj) {
        if (obj == null) {
            return;
        }
        try {
            obj.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
