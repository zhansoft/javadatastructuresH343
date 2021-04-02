import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    public void trie () {
        Trie t = new Trie();
        t.insert("to");
        t.insert("tea");
        t.insert("ted");
        t.insert("ten");
        t.insert("in");
        t.insert("inn");
        t.insert("A");

        assertTrue(t.contains("ten"));
        assertTrue(t.contains("in"));
        assertTrue(t.contains("inn"));
        assertFalse(t.contains("tenn"));
        assertFalse(t.contains("te"));
    }

    @Test
    public void dict () throws IOException {
        Trie trie = new Trie();
        Scanner scanner = new Scanner(Paths.get("commonwords.txt"));
        while (scanner.hasNext()) {
            trie.insert(scanner.next());
        }

        assertTrue(trie.contains("abandon"));
        assertTrue(trie.contains("abandoned"));
        assertTrue(trie.contains("abandonment"));
        assertFalse(trie.contains("abandonmenth"));
        assertFalse(trie.contains("abando"));
        assertFalse(trie.contains("aband"));
        assertFalse(trie.contains("aban"));
        assertFalse(trie.contains("aba"));
        assertFalse(trie.contains("ab"));
        assertFalse(trie.contains("a"));
        assertTrue(trie.possiblePrefix("abando"));
        assertTrue(trie.possiblePrefix("aband"));
        assertTrue(trie.possiblePrefix("aban"));
        assertTrue(trie.possiblePrefix("aba"));
        assertTrue(trie.possiblePrefix("ab"));
        assertTrue(trie.possiblePrefix("a"));
    }

    @Test
    void trieEmpty(){
        Trie t = new Trie();
        t.insert("");
        assertTrue(t.contains(""));
        assertFalse(t.possiblePrefix("t"));
    }

    @Test
    void trieReg(){
        Trie t = new Trie();
        t.insert("akali");
        t.insert("that");
        t.insert("girl");
        t.insert("akali");
        t.insert("go");
        t.insert("grrr");
        assertFalse(t.contains(""));
        assertTrue(t.contains("akali"));
        assertTrue(t.contains("that"));
        assertTrue(t.contains("girl"));
        assertFalse(t.possiblePrefix("akawi"));
    }

    // tests for the bAORD HERE
    @Test
    void board2by2() throws IOException{
        Trie trie = new Trie();
        Scanner scanner = new Scanner(Paths.get("commonwords.txt"));
        while (scanner.hasNext()) {
            trie.insert(scanner.next());
        }

        int size = 2;
        Tile[][] tiles = new Tile[size][size];
        tiles[0][0] = new Tile('a', 0,0);
        tiles[0][1] = new Tile('t', 0,1);
        tiles[1][0] = new Tile('c', 1,0);
        tiles[1][1] = new Tile('s', 1,1);
        Board board = new Board(tiles,trie);


        long t0 = System.currentTimeMillis();
        int i = 0;
        for (String w : board.findWords()) {
            i++;
            System.out.println(w);
        }
        long t1 = System.currentTimeMillis();
        System.out.printf("fast --- Found %d words in %d ms!%n", i, t1-t0);
        assertEquals(i, 10);
    }

    @Test
    void boardreg(){
        Trie trie = new Trie();
        trie.insert("cit");
        trie.insert("chi");
        trie.insert("chirt");
        trie.insert("chit");
        trie.insert("crit");
        trie.insert("hic");
        trie.insert("itch");
        trie.insert("ich");
        trie.insert("hit");
        trie.insert("rich");
        trie.insert("rit");
        trie.insert("tic");
        trie.insert("tich");

        int size = 3 ;
        Tile[][] tiles = new Tile[size][size];
        tiles[0][0] = new Tile('h', 0,0);
        tiles[0][1] = new Tile('j', 0,1);
        tiles[0][2] = new Tile('l', 0,2);
        tiles[1][0] = new Tile('i', 1,0);
        tiles[1][1] = new Tile('c', 1,1);
        tiles[1][2] = new Tile('v', 1,2);
        tiles[2][0] = new Tile('r', 2,0);
        tiles[2][1] = new Tile('t', 2,1);
        tiles[2][2] = new Tile('r', 2,2);
        Board board = new Board(tiles,trie);


        long t0 = System.currentTimeMillis();
        int i = 0;
        for (String w : board.findWords()) {
            i++;
            System.out.println(w);
        }
        long t1 = System.currentTimeMillis();
        System.out.printf("fast --- Found %d words in %d ms!%n", i, t1-t0);
        assertEquals(i, 13);

    }

    @Test
    void boardempty() throws IOException{
        Trie trie = new Trie();

        int size = 3 ;
        Tile[][] tiles = new Tile[size][size];
        for (int r=0; r<size; r++)
            for (int c=0; c<size; c++)
                tiles[r][c] = new Tile(' ',r,c);
        Board board = new Board(tiles,trie);


        long t0 = System.currentTimeMillis();
        int i = 0;
        for (String w : board.findWords()) {
            i++;
            System.out.println(w);
        }
        long t1 = System.currentTimeMillis();
        System.out.printf("fast --- Found %d words in %d ms!%n", i, t1-t0);
        assertEquals(i, 0);

    }
}