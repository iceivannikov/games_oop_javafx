package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {

    @Test
    void whenCreatePositionA2ThanNotChange() {
        Cell actual = Cell.A2;
        BishopBlack bishopBlack = new BishopBlack(actual);
        Cell expected = bishopBlack.position();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenCopyFigureThanCellNotChange() {
        Cell actual = Cell.A5;
        BishopBlack bishopBlack = new BishopBlack(actual);
        Figure figure = bishopBlack.copy(actual);
        Cell expected = figure.position();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenMovingDiagonallyToDestination() {
        Cell c1 = Cell.C1;
        BishopBlack bishopBlack = new BishopBlack(c1);
        Cell[] actual = bishopBlack.way(Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(actual).containsExactly(expected);
    }

    @Test
    void whenMovingNotDiagonallyThenException() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell g6 = Cell.G6;
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class, () -> bishopBlack.way(g6)
        );
        assertThat(exception.getMessage()).isEqualTo(String.format("Could not move by diagonal from %s to %s",
                bishopBlack.position(), g6));
    }
}