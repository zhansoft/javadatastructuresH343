import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListWordsTest {

    @Test
    public void trie () {
        ListWords t = new ListWords(Arrays.asList(
                "to", "tea", "ted", "ten", "in", "inn", "A"));

        assertTrue(t.contains("ten"));
        assertTrue(t.contains("in"));
        assertTrue(t.contains("inn"));
        assertFalse(t.contains("tenn"));
        assertFalse(t.contains("te"));
    }

    @Test
    public void dict () throws IOException {
        List<String> list = Files.readAllLines(Paths.get("commonwords.txt"));
        ListWords words = new ListWords(list);

        assertTrue(words.contains("abandon"));
        assertTrue(words.contains("abandoned"));
        assertTrue(words.contains("abandonment"));
        assertFalse(words.contains("abandonmenth"));
        assertFalse(words.contains("abando"));
        assertFalse(words.contains("aband"));
        assertFalse(words.contains("aban"));
        assertFalse(words.contains("aba"));
        assertFalse(words.contains("ab"));
        assertFalse(words.contains("a"));
        assertTrue(words.possiblePrefix("abando"));
        assertTrue(words.possiblePrefix("aband"));
        assertTrue(words.possiblePrefix("aban"));
        assertTrue(words.possiblePrefix("aba"));
        assertTrue(words.possiblePrefix("ab"));
        assertTrue(words.possiblePrefix("a"));
    }

    @Test
    public void possiblePrefixWord() throws IOException{
        ListWords words = new ListWords(Collections.singletonList(
                "smexy"));
        assertTrue(words.contains("smexy"));
        assertTrue(words.possiblePrefix("smexy"));
        assertTrue(words.possiblePrefix("smex"));
        assertTrue(words.possiblePrefix("sme"));
        assertTrue(words.possiblePrefix("sm"));
        assertTrue(words.possiblePrefix("s"));
        assertFalse(words.possiblePrefix("sx"));
        assertFalse(words.possiblePrefix("sy"));
    }

    @Test
    public void possiblePrefixNone() throws IOException{
        ListWords words = new ListWords(Collections.singletonList(
                ""));
        assertTrue(words.contains(""));
        assertFalse(words.contains("wap"));
        assertFalse(words.possiblePrefix(""));
        assertFalse(words.possiblePrefix("w"));
        assertFalse(words.possiblePrefix("a"));
        assertFalse(words.possiblePrefix("p"));
    }
}