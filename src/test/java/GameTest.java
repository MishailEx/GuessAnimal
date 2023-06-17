import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class GameTest {

    @Test
    public void testValidAnswer() {
        Game game = new Game();
        game.setScanner(new Scanner("да"));
        String answer = game.validAnswer();
        Assert.assertEquals("да", answer);

        game.setScanner(new Scanner("нет"));
        answer = game.validAnswer();
        Assert.assertEquals("нет", answer);

        game.setScanner(new Scanner("ДА"));
        answer = game.validAnswer();
        Assert.assertEquals("да", answer);

        game.setScanner(new Scanner("НЕТ"));
        answer = game.validAnswer();
        Assert.assertEquals("нет", answer);
    }

    @Test
    public void testValidAnswerFalse() {
        Game game = new Game();
        game.setScanner(new Scanner("fsdf\nда"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String answer = game.validAnswer();
        System.setOut(System.out);

        String notValidMessage = outputStream.toString().trim();
        Assert.assertEquals("Ответ должен быть \"да \" или \"нет\".", notValidMessage);
        Assert.assertEquals("да", answer);
    }


    @Test
    public void testLetsPlayAgain() {
        Game game = new Game();
        game.setScanner(new Scanner("да"));
        boolean answer = game.letsPlayAgain();
        Assert.assertTrue(answer);

        game.setScanner(new Scanner("нет"));
        answer = game.letsPlayAgain();
        Assert.assertFalse(answer);
    }

    @Test
    public void testNegativeResponseIf() {
        Game game = new Game();
        int sizeBefore = game.getAnimals().size();
        game.setScanner(new Scanner("птица\nлетает\nнет"));

        game.negativeResponse(0);

        Assert.assertEquals(sizeBefore + 1, game.getAnimals().size());
        Assert.assertEquals("птица", game.animals.get(2).getName());
        Assert.assertEquals("летает", game.animals.get(2).getDesc());
        Assert.assertTrue(game.isEndGame());
    }

    @Test
    public void testNegativeResponseElse() {
        Game game = new Game();
        int sizeBefore = game.getAnimals().size();
        game.setScanner(new Scanner("птица\nлетает\nда"));

        game.negativeResponse(0);

        Assert.assertEquals(sizeBefore + 1, game.getAnimals().size());
        Assert.assertEquals("птица", game.animals.get(2).getName());
        Assert.assertEquals("летает", game.animals.get(2).getDesc());
        Assert.assertFalse(game.isEndGame());
    }

    @Test
    public void testPositiveResponseAnimalGuessedAndPlayAgain() {
        Game game = new Game();
        game.setScanner(new Scanner("да\nда"));
        game.positiveResponse(0);
        Assert.assertFalse(game.isEndGame());
    }

    @Test
    public void testPositiveResponseAnimalGuessedAndStopGame() {
        Game game = new Game();
        game.setScanner(new Scanner("да\nнет"));
        game.positiveResponse(0);
        Assert.assertTrue(game.isEndGame());
    }

    @Test
    public void testPositiveResponseAnimalNotGuessedAndStopGame() {
        Game game = new Game();
        int sizeBefore = game.getAnimals().size();
        game.setScanner(new Scanner("нет\nптица\nлетает\nда"));
        game.positiveResponse(0);
        Assert.assertEquals(sizeBefore + 1, game.getAnimals().size());
        Assert.assertEquals("птица", game.animals.get(2).getName());
        Assert.assertEquals("летает", game.animals.get(2).getDesc());
        Assert.assertFalse(game.isEndGame());
    }

}