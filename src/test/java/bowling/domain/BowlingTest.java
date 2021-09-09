package bowling.domain;

import bowling.domain.exception.FinishGameException;
import bowling.domain.exception.NameLengthExceededException;
import bowling.domain.frames.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingTest {

    @Test
    @DisplayName("생성")
    void create() {
        assertEquals(new Bowling("pjs"), new Bowling("pjs"));
    }

    @Test
    @DisplayName("생성 - 이름 5글자일시 에러")
    void create_name_exception() {
        assertThrows(NameLengthExceededException.class, () -> new Bowling("kimdonggyu"));
    }

    @Test
    @DisplayName("생성 - 이름 확인")
    void create_name() {
        Bowling bowling = new Bowling("pjs");
        assertEquals(bowling.getName(), new Name("pjs"));
    }

    @Test
    @DisplayName("생성 - finish false")
    void create_finish_false() {
        Bowling bowling = new Bowling("pjs");
        assertFalse(bowling.isFinish());
    }

    @Test
    @DisplayName("생성-  finish index 는 0")
    void create_finish_index() {
        Bowling bowling = new Bowling("pjs");
        assertEquals(bowling.lastFinishIndex(), 0);
    }

    @Test
    @DisplayName("투구 - 숫자가 아닐경우 에러")
    void roll_text_exception() {
        Bowling bowling = new Bowling("pjs");
        assertThrows(NumberFormatException.class, () -> bowling.roll("asdasd"));
    }

    @Test
    @DisplayName("투구 - 숫자 범위 초과할 경우 에러")
    void roll_number_exception() {
        Bowling bowling = new Bowling("pjs");
        assertThrows(IllegalArgumentException.class, () -> bowling.roll("11"));
    }

    @Test
    @DisplayName("프레임이 끝났으면 finish")
    void finish() {
        Bowling bowling = new Bowling(new Name("pjs"), new Frames(true));
        assertTrue(bowling.isFinish());
    }

    @Test
    @DisplayName("프레임이 끝났을때 굴리면 에러")
    void finish_roll_exception() {
        Bowling bowling = new Bowling(new Name("pjs"), new Frames(true));
        assertThrows(FinishGameException.class, () -> bowling.roll("5"));
    }
}
