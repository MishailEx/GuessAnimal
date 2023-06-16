import java.util.*;

public class Game {
    private List<Animal> animals = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Set<Integer> usedKeys = new HashSet<>();

    Random random = new Random();

    public void start() {
        animals.add(new Animal("Кит", "Живет в воде"));
        animals.add(new Animal("Кот", "Живет на суше"));
        System.out.println("Загадай животное, а я попробую угадать...");
        while (true) {
            if (animals.size() == usedKeys.size()) {
                usedKeys.clear();
            }
            int randomNumber = random.nextInt(animals.size());

            while (!usedKeys.contains(randomNumber)) {
                System.out.println(animals.get(randomNumber).getDesc() + "?");
                usedKeys.add(randomNumber);
                String response = validAnswer();
                if (response.equalsIgnoreCase(Response.ДА.name())) {
                    while (true) {
                        System.out.printf("это - %s?\n", animals.get(randomNumber).getName());
                        response = validAnswer();
                        if (response.equalsIgnoreCase(Response.ДА.name())) {
                            if (letsPlayAgain()) {
                                break;
                            }
                            System.exit(0);
                        }
                        else {
                            System.out.println("Какое животное ты загадал?");
                            String name = scanner.nextLine().trim();
                            System.out.printf("Чем %s отличается от %s?\n", name, animals.get(randomNumber).getName());
                            String desc = scanner.nextLine().trim();
                            animals.add(new Animal(name, desc));
                            if (letsPlayAgain()) {
                                break;
                            }
                            System.exit(0);
                        }
                    }
                }
                break;
            }
        }
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
        return answer;
    }

    public boolean letsPlayAgain() {
        System.out.println("Сыграем ещё раз?");
        String response = scanner.nextLine().trim();
        return response.equalsIgnoreCase(Response.ДА.name());
    }



}
