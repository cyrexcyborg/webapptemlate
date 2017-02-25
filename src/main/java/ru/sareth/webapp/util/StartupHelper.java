package ru.sareth.webapp.util;

import org.slf4j.Logger;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.CodeSource;

/**
 * Created by DZorin on 03.12.2016.
 * OATTeam
 */
public class StartupHelper {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StartupHelper.class);

    public static String getJarContainingFolder(Class aclass) {
        CodeSource codeSource = aclass.getProtectionDomain().getCodeSource();

        File jarFile;
        try {
            if (codeSource.getLocation() != null) {
                jarFile = new File(codeSource.getLocation().toURI());
            } else {
                String path = aclass.getResource(aclass.getSimpleName() + ".class").getPath();
                String jarFilePath = path.substring(path.indexOf(":") + 1, path.indexOf("!"));
                jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
                jarFile = new File(jarFilePath);
            }
            LOGGER.info("Jar path: " + jarFile.getParentFile().getAbsolutePath());
            return jarFile.getParentFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
