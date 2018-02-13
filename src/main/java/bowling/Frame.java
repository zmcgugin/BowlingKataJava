package bowling;

class Frame {

    private String first;
    private String second;
    private String bonus;

    private Frame(String first, String second) {
        this.first = first;
        this.second = second;
        this.bonus = null;
    }

    public Frame(String first, String second, String bonus) {
        this.first = first;
        this.second = second;
        this.bonus = bonus;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public String getBonus() {
        return bonus;
    }

    public static Frame frame(String first, String second, String bonus) {
        return new Frame(first, second, bonus);
    }

    public static Frame frame(int first, String second, int bonus) {
        return new Frame(String.valueOf(first), second, String.valueOf(bonus));
    }

    public static Frame frame(int first, int second, int bonus) {
        return new Frame(String.valueOf(first), String.valueOf(second), String.valueOf(bonus));
    }

    public static Frame frame(String first, String second) {
        return new Frame(first, second);
    }

    public static Frame frame(int first, int second) {
        return new Frame(String.valueOf(first), String.valueOf(second));
    }

    public static Frame frame(int first, String second) {
        return new Frame(String.valueOf(first), second);
    }

    public static Frame frame(String first, int second) {
        return new Frame(first, String.valueOf(second));
    }

    public static Frame frame(String first) {
        return new Frame(first, "-");
    }

    public static Frame frame(int first) {
        return new Frame(String.valueOf(first), "-");
    }

    public boolean isStrike() {
        return first.equals("X");
    }

    public boolean isSpare() {
        return second.equals("/");
    }

    public boolean isBonusFrame() {
        return bonus != null;
    }

}
