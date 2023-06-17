import java.util.*;

public class Game {
    List<Animal> animals = new ArrayList<>(Arrays.asList(
            new Animal("Кит", "Живет в воде"),
            new Animal("Кот", "Живет на суше")
    ));
    private Scanner scanner = new Scanner(System.in);
    private Set<Integer> usedKeys = new HashSet<>();
    private Random random = new Random();

    private boolean endGame = false;

    public void start() {
        System.out.println("Загадай животное, а я попробую угадать...");
        while (!endGame) {
            if (animals.size() == usedKeys.size()) {
                usedKeys.clear();
            }
            int randomNumber = random.nextInt(animals.size());

            while (!usedKeys.contains(randomNumber)) {
                System.out.println(animals.get(randomNumber).getDesc() + "?");
                usedKeys.add(randomNumber);
                String response = validAnswer();
                if (response.equalsIgnoreCase(Response.ДА.name())) {
                    positiveResponse(randomNumber);
                    break;
                }
                break;
            }
        }
    }

    public void positiveResponse(int randomNumber) {
        while (true) {
            System.out.printf("это - %s?\n", animals.get(randomNumber).getName());
            String response = validAnswer();
            if (response.equalsIgnoreCase(Response.ДА.name())) {
                if (letsPlayAgain()) {
                    return;
                }
                endGame = true;
            } else {
                negativeResponse(randomNumber);
                break;
            }
            break;
        }
    }

    public void negativeResponse(int randomNumber) {
        System.out.println("Какое животное ты загадал?");
        String name = scanner.nextLine().trim();
        System.out.printf("Чем %s отличается от %s?\n", name, animals.get(randomNumber).getName());
        String desc = scanner.nextLine().trim();
        animals.add(new Animal(name, desc));
        if (letsPlayAgain()) {
            return;
        }
        endGame = true;
    }

    public String validAnswer() {
        String answer;
        while (true) {
            answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase(Response.ДА.name()) || answer.equalsIgnoreCase(Response.НЕТ.name())) {
                break;
            } else {
                System.out.println("Ответ должен быть \"да \" или \"нет\".");
            }
        }
        return answer.toLowerCase();
    }

    public boolean letsPlayAgain() {
        System.out.println("Сыграем ещё раз?");
        String response = validAnswer();
        return response.equalsIgnoreCase(Response.ДА.name());
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }


}
