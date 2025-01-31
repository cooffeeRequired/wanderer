# Full Week Project: Wanderer - The RPG game

Build a hero based walking on tiles and killing monsters type of game. The hero
is controlled in a maze using the keyboard. Heroes and monsters have levels and
stats depending on their levels. The goal is reach the highest level by killing
the monsters holding the keys to the next level.

## Workshop: Plan your work

### 0. Fork this repository (under your user)

### 1. Clone the repository to your computer

### 2. Go through the technical details

#### How to launch the program

- Launching the game is running the `Board` class' `main()` method.

- When reading through the specification and the stories again keep this in
  mind.

- Here's an example, it contains

    - a big drawable canvas with one image painted on it
    - and handling pressing keys, for moving your hero around
    - be aware that these are just all the needed concepts put in one place
    - you can separate anything anyhow

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

  int testBoxX;
  int testBoxY;

  public Board() {
    testBoxX = 300;
    testBoxY = 300;

    // set the size of your draw board
    setPreferredSize(new Dimension(720, 720));
    setVisible(true);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    graphics.fillRect(testBoxX, testBoxY, 100, 100);
    // here you have a 720x720 canvas
    // you can create and draw an image using the class below e.g.
    PositionedImage image = new PositionedImage("yourimage.png", 300, 300);
    image.draw(graphics);
  }

  public static void main(String[] args) {
    // Here is how you set up a new window and adding our board to it
    JFrame frame = new JFrame("RPG Game");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    // Here is how you can add a key event listener
    // The board object will be notified when hitting any key
    // with the system calling one of the below 3 methods
    frame.addKeyListener(board);
    // Notice (at the top) that we can only do this
    // because this Board class (the type of the board object) is also a KeyListener
  }

  // To be a KeyListener the class needs to have these 3 methods in it
  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  // But actually we can use just this one for our goals here
  @Override
  public void keyReleased(KeyEvent e) {
    // When the up or down keys hit, we change the position of our box
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      testBoxY -= 100;
    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
      testBoxY += 100;
    }
    // and redraw to have a new picture with the new coordinates
    repaint();
  
  }

}

```

- You can use this image class as a base:

```java
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PositionedImage {

  BufferedImage image;
  int posX, posY;

  public PositionedImage(String filename, int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
    try {
      image = ImageIO.read(new File(filename));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void draw(Graphics graphics) {
    if (image != null) {
      graphics.drawImage(image, posX, posY, null);
    }

  }

}

```

### 3. Create a GitHub project

- create it under your repository for your work and add the [project stories](https://github.com/greenfox-academy/teaching-materials/blob/master/project/wanderer/stories.md)

### 4. Form groups and plan your application together

Plan your architecture. In your architecture you should consider the following
components:

- Models

- Game Objects:

    - Character

        - Monster

        - Hero

            - types

    - Area

    - Tile

        - EmptyTile
        - NotEmptyTile

- GameLogic

    - current hero
    - current area

- Main

    - handling events
    - current game

#### 5. Think about task breakdown in Kanban

Now that you see the big picture, **go through the stories** and think
about how to break them down into tasks:

- To classes
- To methods
- To data and actions
- Extend the story cards with some of these points as a reminder

#### 6. Start working on your first task!