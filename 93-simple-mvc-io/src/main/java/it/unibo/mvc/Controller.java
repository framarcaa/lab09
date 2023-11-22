package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String PATH = System.getProperty("user.home");
    private static final String DEFAULT_FILE = "output.txt";

    private File file = new File(PATH + File.separator + DEFAULT_FILE);

    public File getCurrentFile() {
        return file;
    }

    public String getCurrentFilePath() {
        return file.getPath();
    }

    public void saveContent(final String input) throws IOException {
        try (PrintStream out = new PrintStream(file, StandardCharsets.UTF_8)) {
            out.println(input);
        }
    }

    public void setDestination(File file) {
        final File parent = file.getParentFile();
        if (parent.exists()) {
            this.file = file;
        } else {
            throw new IllegalArgumentException("Cannot save in a non-existing folder.");
        }
    }

    public void setDestination(final String file) {
        setDestination(new File(file));
    }
}
