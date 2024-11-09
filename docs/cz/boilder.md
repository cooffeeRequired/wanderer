
# Celotýdenní projekt: Wanderer - RPG hra

Vytvořte hru, kde hrdina chodí po dlaždicích a zabíjí monstra. Hrdina je ovládán v bludišti pomocí klávesnice. Hrdina i monstra mají úrovně a statistiky, které závisí na jejich úrovni. Cílem je dosáhnout nejvyšší úrovně tím, že porazíte monstra, která mají klíče k dalším úrovním.

## Workshop: Naplánujte si práci

### 0. Forkněte si tento repozitář (pod vaším uživatelským účtem)

### 1. Naklonujte repozitář do svého počítače

### 2. Projděte si technické detaily

#### Jak spustit program

- Spuštění hry znamená spuštění metody `main()` třídy `Board`.

- Při čtení specifikace a příběhů na to mějte na paměti.

- Zde je příklad, který obsahuje:

    - velké kreslící plátno s jedním obrázkem vykresleným na něm
    - a obsluhu kláves, které pohybují vaším hrdinou
    - mějte na paměti, že toto jsou pouze všechny potřebné koncepty na jednom místě
    - můžete cokoli oddělit, jak chcete

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

    // nastavte velikost kreslícího plátna
    setPreferredSize(new Dimension(720, 720));
    setVisible(true);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    graphics.fillRect(testBoxX, testBoxY, 100, 100);
    // zde máte plátno o velikosti 720x720
    // můžete vytvořit a nakreslit obrázek pomocí níže uvedené třídy, např.
    PositionedImage image = new PositionedImage("yourimage.png", 300, 300);
    image.draw(graphics);
  }

  public static void main(String[] args) {
    // Zde nastavíte nové okno a přidáte do něj naši tabuli
    JFrame frame = new JFrame("RPG Game");
    Board board = new Board();
    frame.add(board);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.pack();
    // Zde přidáte posluchač klávesových událostí
    // Objekt "board" bude informován při stisknutí libovolné klávesy
    // systém zavolá jednu z níže uvedených tří metod
    frame.addKeyListener(board);
    // Poznámka: To vše je možné
    // protože třída Board (typ objektu "board") je také KeyListener
  }

  // Aby třída mohla být KeyListener, musí obsahovat tyto tři metody
  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  // Ve skutečnosti můžeme pro naše účely použít pouze tuto metodu
  @Override
  public void keyReleased(KeyEvent e) {
    // Když je stisknuta klávesa nahoru nebo dolů, změníme pozici boxu
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      testBoxY -= 100;
    } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
      testBoxY += 100;
    }
    // a překreslíme, abychom získali nový obrázek s novými souřadnicemi
    repaint();
  
  }

}

```

- Můžete použít tuto třídu obrázku jako základ:

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

### 3. Vytvořte GitHub projekt

- vytvořte ho pod vaším repozitářem pro svou práci a přidejte [příběhy projektu](https://github.com/green-fox-academy/teaching-materials/blob/master/project/wanderer/stories.md)

### 4. Utvořte skupiny a naplánujte si aplikaci společně

Naplánujte svou architekturu. Ve vaší architektuře byste měli zvážit následující
komponenty:

- Modely

- Herní objekty:

    - Postava

        - Monstrum

        - Hrdina

            - typy

    - Oblast

    - Dlaždice

        - PrázdnáDlaždice
        - NeprazdnáDlaždice

- Herní Logika

    - aktuální hrdina
    - aktuální oblast

- Main

    - obsluha událostí
    - aktuální hra

#### 5. Přemýšlejte o rozdělení úkolů v Kanbanu

Nyní, když máte přehled, **projďete si příběhy** a přemýšlejte
o tom, jak je rozdělit na úkoly:

- Na třídy
- Na metody
- Na data a akce
- Rozšiřte karty příběhů o některé z těchto bodů jako připomenutí

#### 6. Začněte pracovat na prvním úkolu!
