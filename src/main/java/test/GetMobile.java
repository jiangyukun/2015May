package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jiangyukun on 2015/5/15.
 */
public class GetMobile {
    private static final String REGEX = "1\\d{10}";
    private static final String FILE = "D:/mobile.txt";

    public static void main(String[] args) throws Exception {
        File file = new File(FILE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;
        Pattern p = Pattern.compile(REGEX);
        while ((line = reader.readLine()) != null) {
            Matcher m = p.matcher(line);

            while (m.find()) {
                String mobile = m.group(0);
                System.out.println(mobile);
            }
        }
    }
}
