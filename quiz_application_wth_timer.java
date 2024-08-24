//Create the Question Class
class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctAnswerIndex;
    }
}



//Create the Quiz Class
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private Question[] questions;
    private int score;
    private int currentQuestionIndex;
    private boolean timeUp;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.timeUp = false;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (currentQuestionIndex = 0; currentQuestionIndex < questions.length; currentQuestionIndex++) {
            Question question = questions[currentQuestionIndex];
            System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    timeUp = true;
                    System.out.println("\nTime's up!");
                    timer.cancel();
                }
            }, 10000); // 10 seconds to answer each question

            int userAnswer = 0;

            if (!timeUp) {
                System.out.print("Your answer (1-" + options.length + "): ");
                userAnswer = scanner.nextInt() - 1;
            }

            timer.cancel();

            if (timeUp) {
                System.out.println("You didn't answer in time!");
                timeUp = false; // reset for the next question
            } else if (question.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was " + (question.getCorrectAnswerIndex() + 1));
            }
        }

        System.out.println("\nQuiz finished!");
        System.out.println("Your score: " + score + "/" + questions.length);
        scanner.close();
    }
}


//Create the Main Class

public class Main {
    public static void main(String[] args) {
        Question[] questions = new Question[]{
            new Question("What is the capital of France?", new String[]{"Berlin", "Paris", "Madrid", "Rome"}, 1),
            new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1),
            new Question("Who wrote 'Hamlet'?", new String[]{"Charles Dickens", "William Shakespeare", "Leo Tolstoy", "Mark Twain"}, 1),
            new Question("What is the largest ocean on Earth?", new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3),
            new Question("What is the chemical symbol for water?", new String[]{"H2O", "O2", "CO2", "HO2"}, 0)
        };

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
