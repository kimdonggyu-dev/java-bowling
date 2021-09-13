package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.exception.FinishGameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

    private List<Frame> frames;
    private boolean isFinish;

    public Frames() {
        this(init(), false);
    }

    public Frames(final boolean isFinish) {
        this.isFinish = isFinish;
    }

    public Frames(final List<Frame> frames, final boolean isFinish) {
        this.frames = frames;
        this.isFinish = isFinish;
    }

    private static List<Frame> init() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            frames.add(new NormalFrame());
        }
        frames.add(new FinalFrame());
        return frames;
    }

    public void roll(final Score score) {
        checkFinishGame();

        Frame currentFrame = frames.stream()
                .filter(frame -> !frame.isFinish())
                .findFirst()
                .orElseThrow(FinishGameException::new);

        currentFrame.roll(score);

        this.isFinish = this.frames.stream().allMatch(Frame::isFinish);
    }

    private void checkFinishGame() {
        if (this.isFinish) {
            throw new FinishGameException();
        }
    }

    public boolean isFinish() {
        return this.isFinish;
    }

    public List<Frame> elements() {
        return Collections.unmodifiableList(this.frames);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                ", isFinish=" + isFinish +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return isFinish == frames1.isFinish && Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames, isFinish);
    }
}