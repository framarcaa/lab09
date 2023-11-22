package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    void setNextStringToPrint(String string);

    String getNextStringToPrint();

    List<String> getHistoryOfPrintedStrings();

    void printCurrentString();
}
