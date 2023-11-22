package it.unibo.mvc;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class SimpleController implements Controller {

    private List<String> history = new LinkedList<>();
    private String nextString;

    @Override
    public void setNextStringToPrint(String string) {
        if (string != null) {
            this.nextString = string;
        } else {
            throw new IllegalArgumentException(string + " is not a valid sting");
        }
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public List<String> getHistoryOfPrintedStrings() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void printCurrentString() {
        if (this.nextString != null) {
            history.add(nextString);
            System.out.println(nextString);
        } else {
            throw new IllegalStateException(nextString + " is not a valid sting");
        }
    }

}
