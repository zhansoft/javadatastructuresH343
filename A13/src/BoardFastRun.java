import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;

public class BoardFastRun {
    public static void main (String[] args) throws IOException {
        Scanner scanner = new Scanner(Paths.get("commonwords.txt"));
        Trie trie = new Trie();
        while (scanner.hasNext()) trie.insert(scanner.next());

        int size = 5 ;
        Tile[][] tiles = new Tile[size][size];
        for (int r=0; r<size; r++)
            for (int c=0; c<size; c++)
                tiles[r][c] = new Tile(r,c);
        Board board = new Board(tiles,trie);

        JFrame jf = new JFrame("Board");
        JScrollPane panel = new JScrollPane(new BoardPanel(board));
        jf.setLayout(new BorderLayout());
        jf.add(BorderLayout.CENTER, panel);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

        long t0 = System.currentTimeMillis();
        int i = 0;
        for (String w : board.findWords()) {
            i++;
            System.out.println(w);
        }
        long t1 = System.currentTimeMillis();
        System.out.printf("fast --- Found %d words in %d ms!%n", i, t1-t0);
    }
}
