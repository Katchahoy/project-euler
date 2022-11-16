package utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilitiesTest {

  @Test
  void testIsPermutation() {
    assertTrue(Utilities.isPermutation("", ""));
    assertFalse(Utilities.isPermutation("", "a"));
    assertTrue(Utilities.isPermutation("a", "a"));
    assertTrue(Utilities.isPermutation("aa", "aa"));
    assertTrue(Utilities.isPermutation("abcde", "edcba"));
    assertTrue(Utilities.isPermutation("abcdd", "dcdab"));
    assertTrue(Utilities.isPermutation("444444", "444444"));
    assertFalse(Utilities.isPermutation("123445", "123455"));
    assertTrue(Utilities.isPermutation("000250", "000052"));
    assertTrue(Utilities.isPermutation("984", "849"));
  }
}