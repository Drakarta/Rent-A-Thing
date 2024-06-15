package ant.rentathing.util;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

public class SubClassFinder {
    public static Set<Class<?>> findSubclasses(String packageName, Class<?> parentClass) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(packageName))
                .setScanners(new SubTypesScanner(false)));

        return reflections.getSubTypesOf((Class<Object>) parentClass);
    }
}
