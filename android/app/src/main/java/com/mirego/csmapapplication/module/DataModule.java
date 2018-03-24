package com.mirego.csmapapplication.module;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by jean-luc on 18-03-24.
 */

public class DataModule {

    public String readFrom(String url)
    {
        try
        {
            URL urlSource = new URL(url);
            Scanner s = new Scanner(urlSource.openStream());
            StringBuilder sb = new StringBuilder();
            while(s.hasNext())
            {
                sb.append(s.nextLine());
            }
            return sb.toString();
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }


}
