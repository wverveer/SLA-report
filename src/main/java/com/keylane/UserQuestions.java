package com.keylane;

import java.util.ArrayList;
import java.util.List;

/* This class is responsible for asking questions to the user that are necessary for maintaining the program's workflow.
 * Currently, the questions that are supposed to be asked are the following questions:
 * * What is the destination of your output?
 */

public class UserQuestions {
    public List<String> outputQuestions() {
        List<String> answers = new ArrayList<>();

        String language = languageChoices();

        //Output file type question
        if (language.equals("English")){
            fileTypeQuestionEnglish();
        }else if (language.equals("Dutch")){
            fileTypeQuestionDutch();
        }else{
            fileTypeQuestionGreek();
        }
        System.out.println("1. CSV");
        System.out.println("2. HTML");
        answers.add(stringInputChoices(List.of("CSV", "HTML")));

        //Output file path
        if (language.equals("English")){
            fileTypeQuestionEnglish();
        }else if (language.equals("Dutch")){
            fileTypeQuestionDutch();
        }else{
            fileTypeQuestionGreek();
        }
        return answers;
    }

    public void fileTypeQuestionEnglish() {
        System.out.println("We would like you to answer the following questions:");
        System.out.println("Where would you like to save the result?");
    }

    public void fileTypeQuestionDutch() {
        System.out.println("We willen graag dat je de volgende vragen beantwoordt:");
        System.out.println("Waar wilt je het resultaat opslaan?");
    }

    public void fileTypeQuestionGreek() {
        System.out.println("Θα θέλαμε να απαντήσετε μερικές ερωτήσεις:");
        System.out.println("Που θα θέλατε να αποθηκεύσετε το αποτέλεσμα;");
    }

    public String languageChoices(){
        System.out.println("What language would you like your questions to be asked:");
        System.out.println("1. English");
        System.out.println("2. Dutch");
        System.out.println("3. Greek");

        return stringInputChoices(List.of("English", "Dutch", "Greek"));
    }

    public String stringInputChoices(List<String> choices) {
        String input = System.console().readLine();

        String response = "";
        while (response == null) {
            try {
                int in = Integer.parseInt(input);
                if (in<=choices.size() && in>0){
                    response=choices.get(in-1);
                }else {
                    wrongInput();
                }
            } catch (NumberFormatException nfe) {
                wrongInput();
            }
        }
        return response;
    }

    public void wrongInput(){
        System.out.println("Wrong input, please try again:");
    }
}
