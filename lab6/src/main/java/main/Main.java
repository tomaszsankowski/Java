package main;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String inPath = args[0];
        String outPath = args[1];
        List<Path> files;
        Path source = Path.of(inPath);

        try (Stream<Path> paths = Files.list(source)) {
            files = paths.toList();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        for(int it = 1; it <= 10; it++)
        {
            long time = System.currentTimeMillis();

            try (ForkJoinPool threadPool = new ForkJoinPool(it)) {
                threadPool.submit(() -> files.parallelStream()
                        .map(path -> {
                            try {
                                BufferedImage image = ImageIO.read(path.toFile());
                                String name = path.getFileName().toString();
                                return Pair.of(name, image);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                        .map(entry -> {
                            if (entry != null) {
                                BufferedImage original = entry.getRight();
                                BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

                                for (int i = 0; i < original.getWidth(); i++) {
                                    for (int j = 0; j < original.getHeight(); j++) {
                                        int rgb = original.getRGB(i, j);
                                        Color color = new Color(rgb);
                                        Color outColor = new Color(color.getRed(), color.getBlue(), color.getGreen());
                                        int outRgb = outColor.getRGB();
                                        image.setRGB(i, j, outRgb);
                                    }
                                }

                                return Pair.of(entry.getLeft(), image);
                            } else {
                                return null;
                            }
                        })
                        .forEach(entry -> {
                            if (entry != null) {
                                try {
                                    File outputFile = new File(STR."\{outPath}/\{entry.getLeft()}");
                                    ImageIO.write(entry.getRight(), "jpg", outputFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })).join();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(STR."Time: \{System.currentTimeMillis() - time} for \{it} threads");
        }
    }
}