package com.example.library;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryUnitTests {

  // *** This is our system under test (SUT) ***
  class Calculator {
    public int add(int a, int b) {
      return a + b;
    }
  }

  @Test
  public void assertCanAddNumbers() {
    // arrange
    var calc = new Calculator();
    var expected = 8;
    // act
    var result = calc.add(3, 5);
    // assert
    assertEquals(expected, result);
  }
}
