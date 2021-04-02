import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Board {
    private final Tile[][] tiles;
    private final int boardSize;
    private final Words dict;

    public Board(Tile[][] tiles, Words dict) {
        this.tiles = tiles;
        this.boardSize = tiles.length;
        this.dict = dict;
    }

    public int getBoardSize () { return boardSize; }

    public Tile get(int row, int col) {
        return tiles[row][col];
    }

    /**
     * The method returns a list of neighboring cells
     * that have not been visited.
     */
    public List<Tile> getFreshNeighbors (int r, int c) {
        // TODO
        // returns 8
        List<Tile> result = new ArrayList<>();
        Tile reuse;
        // edge case: corners
        for(int x = -1; x <= 1; x++){
            for(int y = -1; y <= 1; y++){
                // check if not out of bounds
                int newx = c + x, newy = r + y;
                if(newx >= 0 && newx < this.boardSize && newy >= 0 && newy < this.boardSize){
                    if(!(x == 0 && y == 0)){
                        if(tiles[newy][newx].isFresh()){
                            result.add(tiles[newy][newx]);
                        }
                    }
                }

            }
        }
        return result;
    }

    /**
     * Starting from the given position (r,c) and the string s
     * found so far, the method performs the following actions:
     *   - marks the tile (r,c) as visited
     *   - extends s with the character at (r,c); if that
     *     new string is a word, add it to the result to be
     *     returned
     *   - if the extended string is not a valid prefix of
     *     any word, mark the tile (r,c) is unvisited
     *     and return
     *   - otherwise visit all fresh neighbors recursively
     */
    public HashSet<String> findWordsFromPos(int r, int c, String s) {
        // TODO
        HashSet<String> found = new HashSet<>();
        // marks current tile (r,c) as visited
        this.get(r,c).setVisited();
        // extends s with the character at (r,c);
        String check = s + this.get(r,c).toString().toLowerCase();
        // if new string is a word, add it to the result to be returned
        if(this.dict.contains(check)){
            found.add(check);
        }
        // if new string not valid prefix, mark the tile (r,c) as unvisited and return
        if(!this.dict.possiblePrefix(check)){
            this.get(r,c).reset();
            return found;
        }
        // otherwise visit all fresh neighbors recursively
        else{
            List<Tile> freshneighbor = this.getFreshNeighbors(r,c);
            for(Tile t:freshneighbor){
                found.addAll(findWordsFromPos(t.getRow(), t.getCol(), check));
            }
        }
        this.get(r,c).reset();
        return found;
    }

    public HashSet<String> findWords() {
        HashSet<String> result = new HashSet<>();
        for (int r=0; r<boardSize; r++) {
            for (int c=0; c<boardSize; c++) {
                result.addAll(findWordsFromPos(r,c,""));
            }
        }
        return result;
    }

    public void paint(Graphics2D g2, int offset, Dimension dim) {
        Rectangle2D.Double tileBox;
        int tileSize = dim.width / boardSize;

        FontMetrics fm = g2.getFontMetrics();
        int scaleFactor = dim.width / (boardSize * 10 * fm.stringWidth("J"));

        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                tileBox = new Rectangle2D.Double(
                        offset + c * tileSize,
                        offset + r * tileSize,
                        tileSize,
                        tileSize);
                tiles[r][c].paint(g2, tileBox, scaleFactor);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                sb.append(get(r,c));
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}

