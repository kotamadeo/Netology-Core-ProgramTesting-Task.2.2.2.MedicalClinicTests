package com.gmail.at.kotamadeo.patient;

import com.gmail.at.kotamadeo.patient.service.alert.SendAlertServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class SendAlertServiceImplTest {
    private static SendAlertServiceImpl sendAlertServiceImpl;

    @BeforeAll
    public static void initSuite() {
        sendAlertServiceImpl = spy(SendAlertServiceImpl.class);
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Все тесты завершены!");
    }

    @Test
    @DisplayName("Test for method 'send()' in 'SendAlertServiceImpl' class")
    void sendTest(TestInfo sendTestInfo) {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class); // перехват аргументов
        sendAlertServiceImpl.send("Test"); // запускаем метод со значением "Test"
        verify(sendAlertServiceImpl).send(argumentCaptor.capture()); // перехват
        assertEquals("Test", argumentCaptor.getValue(),
                sendTestInfo.getDisplayName() + " упал!"); // сравниваем ожидаемое значение и перехват
        System.out.println(sendTestInfo.getDisplayName() + " завершен!");
    }
}
