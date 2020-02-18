package io.test.proj.testcase;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;


public abstract class TestTemplate {

    protected List<String> anagramList;
    protected String myAnagram = "binary";

    public TestTemplate() {

    }

    protected void myDelay(final long d) {

        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateAnagramList() {

        boolean pass = true;
        if (null != anagramList) {
            for (String s : anagramList) {
                if (!isAnagram(myAnagram, s)) {
                    pass = false;
                    System.out.println(s + " not an anagram: " + myAnagram);
                }
            }
        } else {
            pass = false;
        }
        assertTrue(pass, "failed to validate the anagram from ...");
    }

    protected void saveAnagrams(final List<String> anagrams) {

        boolean saving = false;
        for (String anagram : anagrams) {
            if (anagram.endsWith("found. Displaying all:")) {
                System.out.println(anagram);
                saving = true;
                anagramList = new ArrayList<>();
                continue;
            }
            if ( anagram.equals("What's New")) {
				break;
			}
			if ( saving ) {
				System.out.println(anagram);
				anagramList.add(anagram);
			}
        }
    }

    protected boolean isAnagram(final String origial, final String anagram) {
        char[] oriChars = origial.replaceAll("\\s+", "").toLowerCase().toCharArray();
        char[] anaChars = anagram.replaceAll("\\s+", "").toLowerCase().toCharArray();

        if (oriChars.length != anaChars.length) {
            return false;
        }
        Arrays.sort(oriChars);
        Arrays.sort(anaChars);
        if (!Arrays.equals(oriChars, anaChars)) {
            System.out.println(anagram + " not an anagram: " + myAnagram);
            return false;
        }
        return true;
    }

}