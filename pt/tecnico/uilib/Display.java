package pt.tecnico.uilib;

import static pt.tecnico.uilib.Dialog.UI;

import java.util.Collection;

/** Display lines of text. */
public class Display {

  /** The graphical driver used to interact with the user */
  private Dialog _ui;

  /** The title (optional) */
  private String _title = "";

  /** Text to display */
  private StringBuilder _text = new StringBuilder();

  /** Build a Display without a title and use the static IO */
  public Display() {
    this("");
  }

  /**
   * @param title the title
   */
  public Display(String title) {
    this(UI, title);
  }

  /**
   * @param ui    the dialog driver
   * @param title the title
   */
  public Display(Dialog ui, String title) {
    _ui = ui;
    _title = title;
  }

  /**
   * @param text text to be added
   * @return the display
   */
  public Display add(Object toAdd) {
    _text.append(toAdd.toString());
    return this;
  }

  public Display addAll(Collection<?> items) {
    items.forEach(item -> addLine(item));
    return this;
  }

  /**
   * Add a line of text in a new-line. No new-line is added if there is no
   * previous text.
   * 
   * @param text text to be added
   * @return the display
   */
  public Display addLine(Object toAdd) {
    return addNewLine(toAdd, false);
  }

  /**
   * Add a line of text in a new-line. A new line is previously added if force is
   * true or if there is already some text.
   * 
   * @param text  text to be added
   * @param force add a new-line even if no text is already present.
   * @return the display
   */
  public Display addNewLine(Object toAdd, boolean force) {
    if (force || !_text.isEmpty())
      add("\n");
    add(toAdd);
    return this;
  }

  /**
   * Display the text held by this display, even if the text to show is the empty string.
   * Clear the text after the text was shown to the user.
   */
  public final void display() {
    _ui.render(_title, _text.toString());
    clear();
  }

  /**
   * Display the text held by this display only if there is something to show (text is not the
   * empty string). Otherwise, it does nothing.
   * In the textual interaction this method and the display() method are similar. With a visual
   * interaction, the behabior of both methods is distinct when the text to show is the empty string.
   * Clear the text after the text was shown to the user.
   */
  public final void displayText() {
    if (!_text.isEmpty()) {
      _ui.render(_title, _text.toString());
      clear();
    }
  }

  /**
   * Easy display method.
   * 
   * @param toPop
   */
  public void popup(Object toPop) {
    add(toPop);
    display();
  }

  /**
   * Easy display method.
   * 
   * @param toPop
   */
  public void popup(Collection<?> toPop) {
    if (toPop != null) {
      addAll(toPop);
      display();
    }
  }

  /**
   * Clear display. Empties the text to show.
   */
  public void clear() {
    _text.setLength(0);
  }
}
