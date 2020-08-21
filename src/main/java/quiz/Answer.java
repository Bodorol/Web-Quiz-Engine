package quiz;

public class Answer {

    private Quiz quiz;
    private boolean success;
    private String feedback;

    public Answer(Quiz quiz, int answer) {
        this.quiz = quiz;
        this.success = answer == 3;
        if (success) {
            this.feedback = "You won, yay";
        } else {
            this.feedback = "You lost, oh no";
        }
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }


}
