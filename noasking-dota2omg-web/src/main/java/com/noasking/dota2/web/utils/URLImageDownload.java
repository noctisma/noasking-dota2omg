package com.noasking.dota2.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by MaJing on 2018/2/6.
 */
public class URLImageDownload {

    private static final Logger logger = LoggerFactory.getLogger(URLImageDownload.class);

    public static boolean download(String urlString, String localPathStr) {
        logger.info("Download:["+urlString+"];INTO:"+localPathStr);
        try (DataInputStream dataInputStream = new DataInputStream(new URL(urlString).openStream()); FileOutputStream
                fileOutputStream = new FileOutputStream(new File(localPathStr))) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }

            dataInputStream.close();
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
