package bowling;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.Frame.frame;
import static java.lang.Integer.*;

public class Bowling {

    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String NO_ACTION = "-";

    public static int bowl(Frame... frameArray) {
        List<Frame> frames = convertToTwoBowlFramesList(frameArray);

        return (int) IntStream.range(0, frames.size()).mapToLong(i -> {
            Frame frame = frames.get(i);
            boolean hasAnotherFrame = frames.size() - 1 > i;
            int total = toBowlingValue(frame);

            if(frame.isSpare() && hasAnotherFrame) {
                return total + calculatedSpare(frames, i);
            } else if(frame.isStrike() && hasAnotherFrame) {
                return total + calculateStrike(frames, i);
            } else {
                return total;
            }
        }).sum();
    }

    private static List<Frame> convertToTwoBowlFramesList(Frame[] frameArray) {
        List<Frame> frames = toList(frameArray);

        frames.stream().filter(Frame::isBonusFrame).findFirst().ifPresent(bonusFrame -> {
            if (bonusFrame.getFirst().equals(STRIKE)) {
                frames.add(frame(toBowlingValue(bonusFrame.getFirst())));

                if (!bonusFrame.getBonus().equals(SPARE)) {
                    frames.add(frame(toBowlingValue(bonusFrame.getSecond())));
                }
                frames.add(frame(toBowlingValue(bonusFrame.getBonus())));
            } else if (bonusFrame.getSecond().equals(SPARE)) {
                frames.add(frame(bonusFrame.getFirst()));
                frames.add(frame(10 - toBowlingValue(bonusFrame.getFirst())));
                frames.add(frame(bonusFrame.getBonus()));
            }
            frames.remove(bonusFrame);
        });
        return frames;
    }

    private static int calculateStrike(List<Frame> frames, int i) {
        Frame nextFrame = frames.get(i + 1);
        int total = toBowlingValue(nextFrame);
        if((nextFrame.isStrike() || nextFrame.getSecond().equals(NO_ACTION)) && frames.size() - 1 > i + 1) {
            total += toBowlingValue(frames.get(i + 2).getFirst());
        }
        return total;
    }

    private static int calculatedSpare(List<Frame> frames, int i) {
        return toBowlingValue(frames.get(i + 1).getFirst());
    }

    private static int toBowlingValue(Frame frame) {
        if(frame.getFirst().equals(STRIKE) || frame.getSecond().equals(SPARE)) {
            return 10;
        } else {
            return defaultDashToZero(frame.getFirst()) + defaultDashToZero(frame.getSecond());
        }
    }

    private static int defaultDashToZero(String symbol) {
        if(symbol.equals(NO_ACTION)) {
            return 0;
        } else {
            return valueOf(symbol);
        }
    }

    private static int toBowlingValue(String symbol) {
        switch (symbol) {
            case STRIKE:
            case SPARE:
                return 10;
            case NO_ACTION:
                return 0;
            default:
                return Integer.valueOf(symbol);
        }
    }

    private static List<Frame> toList(Frame[] frameArray) {
        return Arrays.stream(frameArray).collect(Collectors.toList());
    }

}
