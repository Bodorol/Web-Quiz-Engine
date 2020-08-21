package api.quiz;

public class Answer {

    private boolean success;
    private String feedback;

    public Answer(Quiz quiz, int answer) {
        this.success = answer == 3;
        if (success) {
            this.feedback = "You won, yay";
        } else {
            this.feedback = "You lost, oh no";
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }

}
