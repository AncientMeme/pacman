package pacman.display;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import pacman.board.PacmanBoard;
import pacman.game.PacmanGame;
import pacman.ghost.Ghost;
import pacman.ghost.Phase;
import pacman.util.*;

/**
 * BoardViewModel is the intermediary between BoardView and the PacmanGame.
 * */

public class BoardViewModel {
    // The game the model is using
    private PacmanGame game;

    /**
     * Constructs a new BoardViewModel to model the given PacmanGame.
     *
     * @param model The game to be played
     *
     * @requires model != null
     * */
    public BoardViewModel(PacmanGame model){
        this.game = model;
    }

    /**
     * Returns the number of lives left for the player in the game.
     *
     * @return The number of lives
     * */
    public int getLives() {
        return game.getLives();
    }

    /**
     * Returns the current level that the player is on.
     *
     * @return The current level of the game
     * */
    public int getLevel() {
        return game.getLevel();
    }

    /**
     * Returns a colour string to represent how the hunter should be displayed.
     * If game's hunter special is active it should return "#CDC3FF",
     * otherwise return "#FFE709".
     *
     * @return The colour associated with the game's hunter
     * */
    public String getPacmanColour() {
        // Check if hunter's special is active
        if(game.getHunter().isSpecialActive()) {
            return "#CDC3FF";
        }

        return "#FFE709";
    }

    /**
     * Returns the starting angle of the mouth arc of
     * the pacman depending on its direction.
     * If the hunter's direction is RIGHT, return 30.
     * If the hunter's direction if UP, return 120.
     * If the hunter's direction is LEFT, return 210.
     * If the hunter's direction is DOWN, return 300.
     *
     * @return The angle based on the direction of the game's hunter
     * */
    public int getPacmanMouthAngle() {
        Direction direction = game.getHunter().getDirection();
        switch (direction) {
            case RIGHT:
                return 30;
            case UP:
                return 120;
            case LEFT:
                return 210;
            case DOWN:
                return 300;
        }

        // A dummy return
        return 30;
    }

    /**
     * Returns the current position of the game's hunter.
     *
     * @return The position of the hunter
     * */
    public Position getPacmanPosition() {
        return game.getHunter().getPosition();
    }

    /**
     * Returns the board associated with the game.
     *
     * @return The current game board
     **/
    public PacmanBoard getBoard() {
        return game.getBoard();
    }

    /**
     * Returns the positions and colours of the ghosts in the game.
     * Each ghost should be represented as a Pair(position, colour),
     * where position is their current position on the board,
     * and colour is their colour. The ghost's colour should be given
     * be by Ghost.getColour(). However, if the ghost's phase is FRIGHTENED,
     * this colour should instead be "#0000FF".
     *
     * @return A list of Pair<position, colour > representing the ghosts in
     *         the game, in no particular order
     * */
    public List<Pair<Position, String>> getGhosts() {
        List<Ghost> ghosts = game.getGhosts();
        ObservableList<Pair<Position, String>> pairedGhosts =
                FXCollections.observableArrayList();

        // Iterate over each ghost and check for position and phase
        // Then put them in the pairedGhosts
        for (int i = 0; i < ghosts.size(); i++){
            Ghost ghost = ghosts.get(i);
            Position position = ghost.getPosition();
            String colour = ghost.getColour();
            if(ghost.getPhase() == Phase.FRIGHTENED){
                colour = "#0000FF";
            };
            pairedGhosts.add(new Pair<>(position, colour));
        }

        return pairedGhosts;
    }
}
