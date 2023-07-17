package pacman.display;

import javafx.beans.property.StringProperty;
import org.junit.*;
import pacman.board.PacmanBoard;
import pacman.game.PacmanGame;
import pacman.hunter.Phil;
import pacman.score.ScoreBoard;

import java.util.*;

public class ScoreViewModelTest {
    private PacmanBoard board;
    private PacmanGame game;
    private ScoreBoard scores;
    private ScoreViewModel scoreVM;

    @Before
    public void setUp() {
        // Setup the game
        board = new PacmanBoard(4, 4);
        game = new PacmanGame("lol", "Meme", new Phil(), board);
        scores = game.getScores();

        // Setup score entries
        String[] names = {"Alex", "Meme", "Yeet"};
        int[] values = {100, 1000, 69420};
        for (int i = 0; i < names.length; i++) {
            scores.setScore(names[i], values[i]);
        }

        // Setup the view model
        scoreVM = new ScoreViewModel(game);
    }

    @Test
    public void getCurrentScoreTest() {
        // Set the score and check for output
        scores.increaseScore(500);
        Assert.assertEquals(500,scoreVM.getCurrentScore());

        // Check again to see if it functions well enough
        scores.increaseScore(350);
        Assert.assertEquals(850,scoreVM.getCurrentScore());
    }

    @Test
    public void getCurrentScoreProperty() {
        // Change the score but no update
        scores.increaseScore(300);
        Assert.assertEquals("Score : 0",
                scoreVM.getCurrentScoreProperty().getValue());

        // Updates the score after it is changed
        scores.increaseScore(200);
        scoreVM.update();
        Assert.assertEquals("Score : 500",
                scoreVM.getCurrentScoreProperty().getValue());
    }

    @Test
    public void getScoresTest() {
        // Check for "Sorted by Name" mode
        List<String> testEntries = scoreVM.getScores();
        List<String> expectedEntries = scores.getEntriesByName();
        for(int i = 0; i < expectedEntries.size(); i++){
            Assert.assertEquals(expectedEntries.get(i),
                    testEntries.get(i));
        }
        Assert.assertEquals(true,
                testEntries.containsAll(expectedEntries));

        // Check for "Sorted by Score" mode
        scoreVM.switchScoreOrder();
        scoreVM.update();
        testEntries = scoreVM.getScores();
        expectedEntries = scores.getEntriesByScore();
        for(int i = 0; i < expectedEntries.size(); i++){
            Assert.assertEquals(expectedEntries.get(i),
                    testEntries.get(i));
        }
        Assert.assertEquals(true,
                testEntries.containsAll(expectedEntries));
    }

    @Test
    public void getSortedBy() {
        // Check if default property is "Sorted by Name"
        StringProperty sortMode = scoreVM.getSortedBy();
        Assert.assertEquals("Sorted by Name", sortMode.getValue());

        // Check if property is still the same without update
        scoreVM.switchScoreOrder();
        Assert.assertEquals("Sorted by Name", sortMode.getValue());

        // Check if property is "Sorted by Score" after change
        scoreVM.update();
        sortMode = scoreVM.getSortedBy();
        Assert.assertEquals("Sorted by Score", sortMode.getValue());
    }

    @Test
    public void setPlayerScore() {
        // Set the player's score
        scoreVM.setPlayerScore("Meme", 1337);

        // Check if the entries contains the player's new score
        List<String> entries = scores.getEntriesByName();
        Assert.assertEquals(true, entries.contains("Meme : 1337"));
    }

    @Test
    public void updateTest() {
        // Test if update works by changing score, changing sort order
        // and add score entries
        scoreVM.switchScoreOrder();
        scores.increaseScore(300);

        // Check for values before update
        Assert.assertEquals("Sorted by Name",
                scoreVM.getSortedBy().getValue());
        Assert.assertEquals("Score : 0",
                scoreVM.getCurrentScoreProperty().getValue());

        // Test for out of dated list in ScoreViewModel
        List<String> originalEntries = scores.getEntriesByName();
        List<String> testEntries = scoreVM.getScores();
        scores.setScore("lol", 420);
        for(int i = 0; i < originalEntries.size(); i++){
            Assert.assertEquals(originalEntries.get(i),
                    testEntries.get(i));
        }

        // Check value after update
        scoreVM.update();
        Assert.assertEquals("Sorted by Score",
                scoreVM.getSortedBy().getValue());
        Assert.assertEquals("Score : 300",
                scoreVM.getCurrentScoreProperty().getValue());

        // Test if the stored entries are updated
        originalEntries = scores.getEntriesByScore();
        testEntries = scoreVM.getScores();
        for(int i = 0; i < originalEntries.size(); i++){
            Assert.assertEquals(originalEntries.get(i),
                    testEntries.get(i));
        }
    }
}
