package app;

import java.util.LinkedList;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

public class TextWriter {
    private RenderWindow window;
    private Vector2f origin;
    private LinkedList<Text> buffer;
    private LinkedList<Text> displayed;
    private LinkedList<Long> time;
    private LinkedList<Text> remover = new LinkedList<Text>();
    private Vector2f startPos;

    public TextWriter(RenderWindow window) {
        this.window = window;
        this.origin = new Vector2f(window.getSize().x - window.getSize().x, window.getSize().y - window.getSize().y);
        // window.getView().getCenter().x + (window.getSize().x / 2) -
        // (window.getSize().x /5), window.getView().getCenter().y + (window.getSize().y
        // / 2) - (window.getSize().y / 5)
        startPos = new Vector2f(origin.x + 1100, origin.y + 950);
        buffer = new LinkedList<>();
        time = new LinkedList<>();
        displayed = new LinkedList<>();
    }

    public void run() {
        for (Text text : buffer) {
            displayed.add(text);
            time.add(System.currentTimeMillis());
        }
        buffer.clear();
        int count = 0;
        //System.out.println("1");
        
        for (Text text : displayed) {
            if (displayed.size() > 3) {
                remover.add(displayed.getFirst());
            } else {
                // startPos.x, startPos.y - (50 * (displayed.size() - count))
                text.setPosition(startPos.x, startPos.y - (50 * (displayed.size() - count)));
                window.draw(text);
            }
            //System.out.println("2");
            count++;
        }
        for (Text text : remover) {
            //System.out.println("3. Now removing: "+ text.toString() + "  from  " + remover + " with index " + displayed.indexOf(text));
            displayed.remove(text);
        }
        remover.clear();
    }

    public void add(String text) {
        Text add = new Text(text, UserInterface.getFont());
        add.setCharacterSize(20);
        add.setColor(Color.WHITE);
        buffer.add(add);
    }

    public void move(float x, float y) {
        Vector2f move = new Vector2f(x, y);
        startPos = Vector2f.add(startPos, move);
    }
}