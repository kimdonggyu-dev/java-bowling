package bowling;

import bowling.domain.controller.BowlingGameController;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Main {
    public static void main(String[] args) {
        BowlingGameController.start(new InputView(), new OutputView());
    }
}