package storyTeller.implementations;

import messages.TextEnglish;
import story.Prompt;
import story.chapterOne.sceneZero.PromptZero;
import storyTeller.iStoryTeller;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Default implementation.
 * Created by Mitchell on 9/23/2015.
 */
public class StoryTeller implements iStoryTeller {
    //Player gives name, gender
    //Player selects origin
    //Exposition given to player
    //First NPC appears, talks to player
    //Player responds, selects emotion as well
    //NPC notes their response/emotion, talks to them
    //Flow

    //worldState could be stored as part of some HashMap
    //a set of Constants or an Enum would be used to ask about the world state

    //TODO: store these somewhere special to them.
    String name = null;
    String gender = null;
    HashSet<Prompt> activePrompts = new HashSet<>();

    @Override
    public void initialize() {
        //Screw it. I need to get a functioning prototype to break my inertia. Then I'll scrap it into something that makes sense.
        Scanner scanner = new Scanner(System.in);

        System.out.println(TextEnglish.PROMPT_NAME);

        name = scanner.nextLine();

        System.out.println(TextEnglish.PROMPT_GENDER);

        gender = scanner.nextLine();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Prompt firstPrompt = new PromptZero();

        activePrompts.add(firstPrompt);

        firstPrompt.execute(scanner, executorService, activePrompts);

        while (!activePrompts.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //Weeeee
            }
        }

        scanner.close();
        executorService.shutdownNow();
    }
}