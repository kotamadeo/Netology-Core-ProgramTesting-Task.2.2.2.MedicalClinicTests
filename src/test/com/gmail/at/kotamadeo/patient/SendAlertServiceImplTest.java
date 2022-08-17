package com.gmail.at.kotamadeo.patient;

import com.gmail.at.kotamadeo.patient.service.alert.SendAlertServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SendAlertServiceImplTest {
    private static SendAlertServiceImpl sendAlertServiceImpl;

    @BeforeAll
    public static void initSuite() {
        sendAlertServiceImpl = Mockito.spy(SendAlertServiceImpl.class);
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("All tests complete!");
    }

    @Test
    @DisplayName("Test for method 'send()' in 'SendAlertServiceImpl' class")
     void sendTest(TestInfo sendTestInfo) {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class); // перехват аргументов
        sendAlertServiceImpl.send("Test"); // запускаем метод со значением "Test"
        Mockito.verify(sendAlertServiceImpl).send(argumentCaptor.capture()); // перехват
        assertEquals("Test", argumentCaptor.getValue(),
                sendTestInfo.getDisplayName() + " is NO complete!"); // сравниваем ожидаемое значение и перехват
        System.out.println(sendTestInfo.getDisplayName() + " complete!");
    }
}
