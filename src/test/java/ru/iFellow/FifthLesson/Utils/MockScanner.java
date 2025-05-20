package utils;

import org.mockito.Mockito;

import java.util.Scanner;

public class MockScanner {
    public static Scanner createMockScanner(String input) {
        Scanner mockScanner = Mockito.mock(Scanner.class);
        Mockito.when(mockScanner.nextLine()).thenReturn(input);
        return mockScanner;
    }
}