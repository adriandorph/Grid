package Saves;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class FilePath {

    public static String getDirPath(){
        String os = System.getProperty("os.name");
        os = os.toLowerCase(Locale.ROOT);
        String homedir = System.getProperty("user.home");
        StringBuilder filepath = new StringBuilder();
        filepath.append(homedir);

        if(os.contains("windows")){
            filepath.append("\\AppData\\Roaming\\");
        } else if(os.contains("mac") || os.contains("osx")) {
            filepath.append("/Library/Application Support/");
        }

        filepath.append("Snake");
        return filepath.toString();
    }

    public static Path getFilePath(String file) throws IOException {
        StringBuilder filepath = new StringBuilder();
        filepath.append(FilePath.getDirPath());

        if(!Files.exists(Path.of(filepath.toString()))){
            Files.createDirectory(Path.of(filepath.toString()));
        }

        if(System.getProperty("os.name").contains("Windows")) filepath.append("\\");
        else filepath.append("/");
        filepath.append(file);

        Path path = Path.of(filepath.toString());

        if(!Files.exists(path)){
            Files.createFile(path);
        }

        return path;
    }
}
