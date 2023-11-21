package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberImplView;
import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws SecurityException
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        for (final var viewType : List.of("Impl", "Swing")) {
            final var clas = Class.forName("it.unibo.mvc.view.DrawNumber" + viewType + "View");
            for (int i = 0; i < 3; i++) {
                final var newView = clas.getConstructor().newInstance();
                if (DrawNumberView.class.isAssignableFrom(newView.getClass())) {
                    app.addView((DrawNumberView)newView);
                } else {
                    throw new IllegalStateException(newView.getClass() + "is not acceptable");
                }
            }
        }
    }
}
