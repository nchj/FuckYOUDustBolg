import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Release {

    public static void main(String[] args) {
        String directoryPath = ".";
        Path outputPath = Paths.get("./all_in_one.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
                paths
                        .filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".txt"))
                        .forEach(path -> {
                            try (Stream<String> lines = Files.lines(path)) {
                                lines.forEach(line -> {
                                    try {
                                        writer.write(line);
                                        writer.newLine();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
