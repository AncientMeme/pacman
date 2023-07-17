package pacman.display;

import javafx.util.Pair;
import org.junit.*;
import pacman.board.*;
import pacman.game.PacmanGame;
import pacman.ghost.Ghost;
import pacman.hunter.Hunter;
import pacman.hunter.Speedy;
import pacman.util.Direction;
import pacman.util.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class BoardViewModelTest {
    private PacmanBoard board;
    private PacmanGame game;
    private BoardViewModel boardModel;
    private List<Ghost> ghosts;

    @Before
    public void setUp() {
        // Setup the board
        board = new PacmanBoard(5, 5);
        board.setEntry(new Position(1, 1), BoardItem.BIG_DOT);
        board.setEntry(new Position(2, 2), BoardItem.GHOST_SPAWN);
        board.setEntry(new Position(3, 2), BoardItem.PACMAN_SPAWN);
        board.setEntry(new Position(3, 3), BoardItem.BIG_DOT);
        // Setup the game
        game = new PacmanGame("Yeet", "Meme", new Speedy(), board);
        game.setLevel(3);
        game.setLives(69);
        game.getHunter().setPosition(new Position(3, 2));
        // Setup the ghosts
        ghosts = game.getGhosts();
        ghosts.get(2).setPosition(new Position(3, 1));
        ghosts.get(0).setPosition(new Position(2, 3));

        boardModel = new BoardViewModel(game);
    }

    @Test
    public void getBoardTest() {
        Assert.assertEquals(board, boardModel.getBoard());
    }

    @Test
    public void getGhostTest() {
        List<Pair<Position, String>> ghostsPairs = new ArrayList<>();
        List<Pair<Position, String>> testPairs = boardModel.getGhosts();
        for (int i = ghosts.size() - 1; i >= 0; i--) {
            ghostsPairs.add(new Pair<>(ghosts.get(i).getPosition(),
                    ghosts.get(i).getColour()));
        }

        for(int i = 0; i < ghostsPairs.size(); i++) {
            if (!ghostsPairs.contains(testPairs.get(i))) {
                fail();
            }
        }
    }

    @Test
    public void getLevelTest() {
        Assert.assertEquals(game.getLevel(), boardModel.getLevel());

        // Set the level and see if it changes
        game.setLevel(5);
        Assert.assertEquals(game.getLevel(), boardModel.getLevel());
    }

    @Test
    public void getLivesTest() {
        Assert.assertEquals(game.getLives(), boardModel.getLives());
    }

    @Test
    public void getPacmanColourTest() {
        // Get colour when hunter special is not active
        Assert.assertEquals("#FFE709", boardModel.getPacmanColour());

        // Get colour when hunter special is active
        game.getHunter().activateSpecial(30);
        Assert.assertEquals("#CDC3FF", boardModel.getPacmanColour());
    }

    @Test
    public void getPacmanMouthAngleTest() {
        // Check if method returns all 4 directions correctly
        Hunter hunter = game.getHunter();
        Direction[] directions = {Direction.RIGHT, Direction.UP,
                Direction.LEFT, Direction.DOWN};
        int[] values = {30, 120, 210, 300};

        for(int i = 0; i < directions.length; i++) {
            hunter.setDirection(directions[i]);
            Assert.assertEquals(values[i], boardModel.getPacmanMouthAngle());
        }
    }

    @Test
    public void getPacmanPositionTest() {
        Hunter hunter = game.getHunter();
        // Get the position of pacman
        Assert.assertEquals(hunter.getPosition(),
                boardModel.getPacmanPosition());

        // Move Pacman and check if it updates
        hunter.setPosition(new Position(1, 2));
        Assert.assertEquals(hunter.getPosition(),
                boardModel.getPacmanPosition());
    }
}
