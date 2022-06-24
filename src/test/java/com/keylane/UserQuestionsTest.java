package com.keylane;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserQuestionsTest {
    private InputHandler inputHandler;
    @Test
    void outputQuestions() {
        inputHandler = mock(InputHandlerImpl.class);
        when(inputHandler.getInputChoice()).thenReturn("1");
        when(inputHandler.getInput()).thenReturn("C:\\Git\\SLA-report");

        UserQuestions uq = new UserQuestions(inputHandler);
        uq.outputQuestions();
    }
}