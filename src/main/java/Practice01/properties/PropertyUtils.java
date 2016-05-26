package Practice01.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
    private static Properties properties = null;

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream in = PropertyUtils.class.getClassLoader().getResourceAsStream("Practice01/EEdb.properties")) {
                properties.load(in);
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
        return properties;
    }
}
