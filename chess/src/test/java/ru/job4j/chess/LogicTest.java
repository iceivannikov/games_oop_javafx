package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(
                FigureNotFoundException.class,
                () -> logic.move(Cell.C1, Cell.H6));
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    void whenMoveValid()
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        Logic logic = new Logic();
        Figure figure = new BishopBlack(Cell.C1);
        logic.add(figure);
        Cell source = figure.position();
        Cell dest = Cell.E3;
        logic.move(source, dest);
        Cell actual = figure.copy(dest).position();
        Cell expected = dest;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenMoveThenFigureOccupiedCellException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishop1 = new BishopBlack(Cell.C1);
        BishopBlack bishop2 = new BishopBlack(Cell.E3);
        logic.add(bishop1);
        logic.add(bishop2);
        OccupiedCellException exception = assertThrows(
                OccupiedCellException.class,
                () -> logic.move(Cell.C1, Cell.H6));
        assertThat(exception.getMessage()).isEqualTo(
                "The dest cell or on the path of the figure is occupied"
        );
    }

    @Test
    public void whenMoveThenFigureImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishop1 = new BishopBlack(Cell.C1);
        logic.add(bishop1);
        Cell position = bishop1.position();
        Cell dest = Cell.E2;
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> logic.move(position, dest));
        assertThat(exception.getMessage()).isEqualTo(String.format(
                "Could not move by diagonal from %s to %s", position, dest
        ));
    }
}