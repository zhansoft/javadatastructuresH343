import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Function;

public class SeamCarving {
    private int[] pixels;
    private int type, height, width;

    // Field getters

    int[] getPixels() {
        return pixels;
    }

    int getType() {
        return type;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    // Read and write images

    void readImage(String filename) throws IOException {
        BufferedImage image = ImageIO.read(new File(filename));
        type = image.getType();
        height = image.getHeight();
        width = image.getWidth();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    void writeImage(String filename) throws IOException {
        BufferedImage image = new BufferedImage(width, height, type);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        ImageIO.write(image, "jpg", new File(filename));
    }

    // Accessing pixels and their neighbors

    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }

    ArrayList<Position> getHVneighbors(int h, int w) {
        ArrayList<Position> neighbors = new ArrayList<>();

        if (w == 0) neighbors.add(new Position(h, w + 1));
        else if (w + 1 == width) neighbors.add(new Position(h, w - 1));
        else {
            neighbors.add(new Position(h, w - 1));
            neighbors.add(new Position(h, w + 1));
        }

        if (h == 0) neighbors.add(new Position(h + 1, w));
        else if (h + 1 == height) neighbors.add(new Position(h - 1, w));
        else {
            neighbors.add(new Position(h + 1, w));
            neighbors.add(new Position(h - 1, w));
        }
        return neighbors;
    }

    ArrayList<Position> getBelowNeighbors(int h, int w) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (h + 1 == height) return neighbors;
        neighbors.add(new Position(h + 1, w));
        if (w == 0) {
            neighbors.add(new Position(h + 1, w + 1));
            return neighbors;
        } else if (w + 1 == width) {
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        } else {
            neighbors.add(new Position(h + 1, w + 1));
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        }
    }

    // Computing energy at given pixel

    int computeEnergy(int h, int w) {
        Color c = getColor(h, w);
        Function<Integer, Integer> sq = n -> n * n;
        int energy = 0;
        for (Position p : getHVneighbors(h, w)) {
            Color nc = getColor(p.getFirst(), p.getSecond());
            energy += sq.apply(nc.getRed() - c.getRed());
            energy += sq.apply(nc.getGreen() - c.getGreen());
            energy += sq.apply(nc.getBlue() - c.getBlue());
        }
        return energy;
    }

    // Find seam starting from (h,w) going down and return list of positions and cost
    // and then pick best seam starting from some position on the first row

    Map<Position, Pair<List<Position>, Integer>> hash = new WeakHashMap<>();

<<<<<<< HEAD
    Pair<List<Position>, Integer> findSeam(int hp, int wp) throws EmptyListE {
        return hash.computeIfAbsent(new Position(hp, wp), pos -> {
            try{
                int ph = pos.getFirst();
                int pw = pos.getSecond();
                ArrayList<Position> neighbors = getBelowNeighbors(ph, pw);

                if(neighbors.isEmpty()){
                    return new Pair<List<Position>, Integer>(new Node(new Position(ph, pw), new Empty()), computeEnergy(ph, pw));
                }

                int min = Integer.MAX_VALUE;
                List<Position> path = new Empty();
                Pair<List<Position>, Integer> b;

                for(Position p : neighbors){
                    b = findSeam(p.getFirst(), p.getSecond());
                    if(b.getSecond() < min){
                        path = b.getFirst();
                        min = b.getSecond();
                    }
                }
                return new Pair<List<Position>, Integer>(new Node(new Position(ph, pw), path), computeEnergy(ph, pw) + min);
            }
            catch(EmptyListE e) {
                return new Pair<>(new Empty<>(), 0);
            }
        });
    }

    Pair<List<Position>, Integer> bestSeam() throws EmptyListE{
        hash.clear();
        Pair<List<Position>, Integer> best = new Pair<>(new Empty(), Integer.MAX_VALUE);
        Pair<List<Position>, Integer> temp = new Pair<>(new Empty(), 0);

        for(int col = 0; col < getWidth(); col++){
            temp = findSeam(0, col);
            if(temp.getSecond() < best.getSecond()){
                best = temp;
            }
        }

        return best;
=======
    Pair<List<Position>, Integer> findSeam(int hp, int wp) {
        return;
    }

    Pair<List<Position>, Integer> bestSeam() {
        return;
>>>>>>> 7950f845e30697da9cd28420e9404e70cada86d2
    }

    // Putting it all together; find best seam and copy pixels without that seam

    void cutSeam() {
        try {
            List<Position> seam = bestSeam().getFirst();
            int[] newPixels = new int[height * (width - 1)];
            for (int h = 0; h < height; h++) {
                int nw = 0;
                for (int w = 0; w < width; w++) {
                    if (seam.isEmpty()) {
                        newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                    }
                    else {
                        Position p = seam.getFirst();
                        if (p.getFirst() == h && p.getSecond() == w) {
                            seam = seam.getRest();
                            nw--;
                        } else {
                            newPixels[nw + h * (width - 1)] = pixels[w + h * width];
                        }
                    }
                    nw++;
                }
            }
            width = width - 1;
            pixels = newPixels;
        } catch (EmptyListE e) {
            throw new Error("Bug");
        }
    }
}


