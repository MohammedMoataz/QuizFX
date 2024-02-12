package sample;

public class Data {
    private String answer,
            correct;

    public Data(String answer, String correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getCorrect() {
        return this.correct;
    }
}
