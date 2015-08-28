/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fallingpokemon;

/**
 *
 * @author johngee
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.PatternSyntaxException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import sun.audio.*;

public class Fallingpokemon extends JApplet
{
        private Painting panel;
        public Fallingpokemon()
        {       
                panel = new Painting();
                getContentPane().add(panel);
                panel.setLayout(null);
                
                setSize(800,800);
                //setTitle("Falling Pokemon");
                //setResizable(false);
                //setDefaultCloseOperation(EXIT_ON_CLOSE);        
        }
        public static void main(String args[])
        {
                Fallingpokemon cal=new Fallingpokemon();
                cal.setVisible(true);
        }
        public class Painting extends JPanel
        {
            public String str;
            private JTextField query;
            private String poke;
            private int score;
            private int lives;
            private int level;
            private int restart;
            private int combo;
            private int oldcombo;
            private int inarow;
            private int combodisplay;
            private int frequency;
            private double wordfrequency;
            HashMap<word, Timer> wt;
            Timer timer;
            Timer timer1;
            Timer timer2;
            private final String[] pokemon = new String[]{"Bulbasaur","Ivysaur","Venusaur","Charmander","Charmeleon","Charizard","Squirtle","Wartortle","Blastoise","Caterpie","Metapod","Butterfree","Weedle","Kakuna","Beedrill","Pidgey","Pidgeotto","Pidgeot","Rattata","Raticate","Spearow","Fearow","Ekans","Arbok","Pikachu","Raichu","Sandshrew","Sandslash","Nidoran","Nidorina","Nidoqueen","Nidoran","Nidorino","Nidoking","Clefairy","Clefable","Vulpix","Ninetales","Jigglypuff","Wigglytuff","Zubat","Golbat","Oddish","Gloom","Vileplume","Paras","Parasect","Venonat","Venomoth","Diglett","Dugtrio","Meowth","Persian","Psyduck","Golduck","Mankey","Primeape","Growlithe","Arcanine","Poliwag","Poliwhirl","Poliwrath","Abra","Kadabra","Alakazam","Machop","Machoke","Machamp","Bellsprout","Weepinbell","Victreebel","Tentacool","Tentacruel","Geodude","Graveler","Golem","Ponyta","Rapidash","Slowpoke","Slowbro","Magnemite","Magneton","Farfetch'd","Doduo","Dodrio","Seel","Dewgong","Grimer","Muk","Shellder","Cloyster","Gastly","Haunter","Gengar","Onix","Drowzee","Hypno","Krabby","Kingler","Voltorb","Electrode","Exeggcute","Exeggutor","Cubone","Marowak","Hitmonlee","Hitmonchan","Lickitung","Koffing","Weezing","Rhyhorn","Rhydon","Chansey","Tangela","Kangaskhan","Horsea","Seadra","Goldeen","Seaking","Staryu","Starmie","Mr. Mime","Scyther","Jynx","Electabuzz","Magmar","Pinsir","Tauros","Magikarp","Gyarados","Lapras","Ditto","Eevee","Vaporeon","Jolteon","Flareon","Porygon","Omanyte","Omastar","Kabuto","Kabutops","Aerodactyl","Snorlax","Articuno","Zapdos","Moltres","Dratini","Dragonair","Dragonite","Mewtwo","Mew"};
            private final String[] pokemon1 = new String[]{"Weedle", "Kakuna", "Pidgey", "Fearow", "Ekans", "Arbok", "Raichu", "Vulpix", "Zubat", "Golbat", "Oddish", "Gloom", "Paras", "Meowth", "Mankey", "Abra", "Machop", "Golem", "Ponyta", "Doduo", "Dodrio", "Seel", "Grimer", "Muk", "Gastly", "Gengar", "Onix", "Hypno", "Krabby", "Cubone", "Rhydon", "Horsea", "Seadra", "Staryu", "Jynx", "Magmar", "Pinsir", "Tauros", "Lapras", "Ditto", "Eevee", "Kabuto", "Zapdos", "Mewtwo", "Mew"};
            private final String[] pokemon2 = new String[]{"Ivysaur", "Metapod", "Weedle", "Kakuna", "Pidgey", "Pidgeot", "Rattata", "Spearow", "Fearow", "Ekans", "Arbok", "Pikachu", "Raichu", "Nidoran", "Nidoran", "Vulpix", "Zubat", "Golbat", "Oddish", "Gloom", "Paras", "Venonat", "Diglett", "Dugtrio", "Meowth", "Persian", "Psyduck", "Golduck", "Mankey", "Poliwag", "Abra", "Kadabra", "Machop", "Machoke", "Machamp", "Geodude", "Golem", "Ponyta", "Slowbro", "Doduo", "Dodrio", "Seel", "Dewgong", "Grimer", "Muk", "Gastly", "Haunter", "Gengar", "Onix", "Drowzee", "Hypno", "Krabby", "Kingler", "Voltorb", "Cubone", "Marowak", "Koffing", "Weezing", "Rhyhorn", "Rhydon", "Chansey", "Tangela", "Horsea", "Seadra", "Goldeen", "Seaking", "Staryu", "Starmie", "Scyther", "Jynx", "Magmar", "Pinsir", "Tauros", "Lapras", "Ditto", "Eevee", "Jolteon", "Flareon", "Porygon", "Omanyte", "Omastar", "Kabuto", "Snorlax", "Zapdos", "Moltres", "Dratini", "Mewtwo", "Mew"};
            private final String[] pokemon3 = new String[]{"Ivysaur", "Venusaur", "Squirtle", "Caterpie", "Metapod", "Weedle", "Kakuna", "Beedrill", "Pidgey", "Pidgeot", "Rattata", "Raticate", "Spearow", "Fearow", "Ekans", "Arbok", "Pikachu", "Raichu", "Nidoran", "Nidorina", "Nidoran", "Nidorino", "Nidoking", "Clefairy", "Clefable", "Vulpix", "Zubat", "Golbat", "Oddish", "Gloom", "Paras", "Parasect", "Venonat", "Venomoth", "Diglett", "Dugtrio", "Meowth", "Persian", "Psyduck", "Golduck", "Mankey", "Primeape", "Arcanine", "Poliwag", "Abra", "Kadabra", "Alakazam", "Machop", "Machoke", "Machamp", "Geodude", "Graveler", "Golem", "Ponyta", "Rapidash", "Slowpoke", "Slowbro", "Magneton", "Doduo", "Dodrio", "Seel", "Dewgong", "Grimer", "Muk", "Shellder", "Cloyster", "Gastly", "Haunter", "Gengar", "Onix", "Drowzee", "Hypno", "Krabby", "Kingler", "Voltorb", "Cubone", "Marowak", "Koffing", "Weezing", "Rhyhorn", "Rhydon", "Chansey", "Tangela", "Horsea", "Seadra", "Goldeen", "Seaking", "Staryu", "Starmie", "Mr. Mime", "Scyther", "Jynx", "Magmar", "Pinsir", "Tauros", "Magikarp", "Gyarados", "Lapras", "Ditto", "Eevee", "Vaporeon", "Jolteon", "Flareon", "Porygon", "Omanyte", "Omastar", "Kabuto", "Kabutops", "Snorlax", "Articuno", "Zapdos", "Moltres", "Dratini", "Mewtwo", "Mew"};
            public class word {
                String word;
                int wordx;
                int wordy;
                public word(String word){
                    this.word = word;
                    wordx = (int)(Math.random()*720);
                    wordy = 0;
                }
                public void fall(){
                    wordy++;
                    if(wordy + 13 >= 800){
                        die();
                    }
                }
                public void die(){
                    lives--;
                    inarow = 0;
                    wt.get(this).stop();
                    wt.remove(wordlist.get(wordlist.indexOf(this)));
                    wordlist.remove(wordlist.indexOf(this));
                    if(lives < 1){
                        gameover();
                    }
                }
                public void gameover(){
                    for(int i = 0; i < wordlist.size(); i++){
                        wt.get(wordlist.get(i)).stop();
                    }
                    timer1.stop();
                    timer2.stop();
                    restart = 1;
                    repaint();
                    panel.requestFocus();
                    event1 j=new event1();
                    addKeyListener(j);
                }
                public class event1 extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_R)
			{
                            removeKeyListener(this);
                            panel.startGame();
			}
                }
        }
            }
            public LinkedList<word> wordlist;
            public Painting(){
                query = new JTextField();
                query.setBounds(350,700,100,30);     
                add(query); 
                /*
                try {  
                    File soundFile = new File("audioclip1.wav");  
                    AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);  

                    // load the sound into memory (a Clip)  
                    DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());  
                    Clip clip = (Clip) AudioSystem.getLine(info);  
                    clip.open(sound);  

                    // due to bug in Java Sound, explicitly exit the VM when  
                    // the sound has stopped.  
                    clip.addLineListener(new LineListener() {  
                      public void update(LineEvent event) {  
                        if (event.getType() == LineEvent.Type.STOP) {  
                          event.getLine().close();  
                        }  
                      }  
                    });  
                // play the sound clip  
                clip.start();  
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                }  
                catch (IOException e) {  
                }  
                catch (LineUnavailableException e) {  
                }  
                catch (UnsupportedAudioFileException e) {  
                }  */
                
                setBackground(Color.WHITE);
                setLayout(new FlowLayout());
                addMouseListener(new MouseAdapter(){
                        public void mouseClicked(MouseEvent me){
                                requestFocus();
                        }
                });
                startGame();
            }
            public void startGame()
            {
                    score = 0;
                    lives = 10;
                    level = 1;
                    combo = 1;
                    inarow = 0;
                    oldcombo = 1;
                    combodisplay = 0;
                    frequency = 1000;
                    wordfrequency = 50;
                    wordlist = new LinkedList<>();
                    wt = new HashMap<word, Timer>();
                    query.setText("");
                    query.requestFocus();
                    str = "";
                    restart = 0;
                    
                    TimeClass1 tc1 = new TimeClass1();
                    timer1 = new Timer(1000, tc1);
                    timer1.start();
                    
                    TimeLevel tc2 = new TimeLevel();
                    timer2 = new Timer(30000, tc2);
                    timer2.start();
                            
                    query.getDocument().addDocumentListener(new DocumentListener() {

                    protected void update(DocumentEvent e) {
                        Document doc = e.getDocument();
                        try {
                            str = doc.getText(0, doc.getLength());
                            repaint();
                            for(int i = 0; i < wordlist.size(); i++)
                            {
                                if(str.toLowerCase().compareTo(wordlist.get(i).word.toLowerCase()) == 0)
                                {
                                    wt.get(wordlist.get(i)).stop();
                                    wt.remove(wordlist.get(i));
                                    wordlist.remove(i);
                                    combo = (int)(inarow/25.0) + 1;
                                    if(combo != oldcombo){
                                        combodisplay = 1;
                                        /* 
                                        try {  
                                            File soundFile = new File("audioclip2.wav");  
                                            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);  

                                            // load the sound into memory (a Clip)  
                                            DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());  
                                            Clip clip = (Clip) AudioSystem.getLine(info);  
                                            clip.open(sound);  

                                            // due to bug in Java Sound, explicitly exit the VM when  
                                            // the sound has stopped.  
                                            clip.addLineListener(new LineListener() {  
                                              public void update(LineEvent event) {  
                                                if (event.getType() == LineEvent.Type.STOP) {  
                                                  event.getLine().close();  
                                                }  
                                              }  
                                            });  
                                        // play the sound clip  
                                        clip.start();  
                                        }  
                                        catch (IOException error) {  
                                        }  
                                        catch (LineUnavailableException error) {  
                                        }  
                                        catch (UnsupportedAudioFileException error) {  
                                        }  */
                                    }
                                    oldcombo = combo;
                                    score = score + combo;
                                    inarow = inarow + 1;
                                    if(frequency > 700)
                                        frequency = frequency - 2;
                                    timer1.setDelay(frequency);
                                    SwingUtilities.invokeLater(new Runnable()
                                    {
                                        public void run()
                                        {
                                            query.setText("");
                                        }
                                    });
                                }
                            }
                        } catch (BadLocationException ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        update(e);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        update(e);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        update(e);
                    }
                    });

            }
            public class TimeLevel implements ActionListener{
                public TimeLevel(){
                    
                }
                public void actionPerformed(ActionEvent tc){
                    if(level < 4){
                        level++;
                    }
                    else{
                        timer2.stop();
                    }
                }
            }
            public class TimeClass implements ActionListener{
                int counter;
                word str;
                public TimeClass(int counter, word str){
                    this.counter = counter;
                    this.str = str;
                }
                public void actionPerformed(ActionEvent tc){
                    if(counter >= 1){
                        str.fall();
                        repaint();
                        counter--;
                    }
                    else{
                        wt.get(this.str).stop();
                    }
                }
            }
            public class TimeClass1 implements ActionListener{
                
                public TimeClass1(){
                    
                }
                public String newstring(){
                    switch(level){
                        case 1:
                            return pokemon1[(int)(Math.random() * pokemon1.length)];
                        case 2:
                            return pokemon2[(int)(Math.random() * pokemon2.length)];
                        case 3:
                            return pokemon3[(int)(Math.random() * pokemon3.length)];
                        default:
                            return pokemon[(int)(Math.random() * pokemon.length)];
                    }
                }
                public void actionPerformed(ActionEvent tc){
                    poke = newstring();
                    while(!unique(poke))
                    {
                        poke = newstring();
                    }
                    wordlist.add(new word(poke));
                    
                    if(wordfrequency > 10){
                        wordfrequency = wordfrequency - 0.2;
                    }
                    
                    timer = new Timer((int)wordfrequency, new TimeClass(800, wordlist.get(wordlist.size()-1)));
                    wt.put(wordlist.get(wordlist.size()-1),timer);
                    wt.get(wordlist.get(wordlist.size()-1)).start();
                    
                    repaint();
                }
                public boolean unique(String poke)
                {
                    for(int i = 0; i < wordlist.size(); i++){
                        if(poke.compareTo(wordlist.get(i).word) == 0){
                            return false;
                        }
                    }
                    return true;
                }
            }
            public void paintComponent(Graphics g){
                    super.paintComponent(g);
                    
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setFont(new Font("default", Font.BOLD, 20));
                    g2d.setColor(Color.GREEN);
                    
                    FontMetrics fm = g2d.getFontMetrics();
                    int x1 = (getWidth() - fm.stringWidth("Score: " + score)) / 2;
                    int y1 = ((getHeight() - fm.getHeight()) + fm.getAscent()) / 2;
                    
                    g2d.drawString("Score: " + score, x1, y1);
                    
                    y1 = y1 + 25;
                    g2d.setColor(Color.RED);
                    
                    g2d.drawString("Lives: " + lives, x1, y1);
                    
                    g2d.setFont(new Font("default", Font.BOLD, 13));
                    g2d.setColor(Color.BLACK);
                    
                    try{
                        for(int i = 0; i < wordlist.size(); i++)
                        {
                            if(str.length() > 0 && wordlist.get(i).word.toLowerCase().matches(str.toLowerCase() + ".*")){
                                g2d.setFont(new Font("default", Font.BOLD, 13));
                                g2d.setColor(Color.RED);
                                g2d.drawString(wordlist.get(i).word, wordlist.get(i).wordx, wordlist.get(i).wordy);
                                g2d.setFont(new Font("default", Font.BOLD, 13));
                                g2d.setColor(Color.BLACK);
                            }
                            else{
                                g2d.drawString(wordlist.get(i).word, wordlist.get(i).wordx, wordlist.get(i).wordy);
                            }
                        }
                    }
                    catch(PatternSyntaxException err){
                        for(int i = 0; i < wordlist.size(); i++)
                        {
                            g2d.drawString(wordlist.get(i).word, wordlist.get(i).wordx, wordlist.get(i).wordy);
                        }
                    }
                    if(restart == 1){
                        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
                        g2d.setColor(Color.RED);
                        fm = g2d.getFontMetrics();
                        x1 = (getWidth() - fm.stringWidth("Press \'R\' to Restart")) / 2;
                        y1 = ((getHeight() - fm.getHeight()) + fm.getAscent()) / 2 - 30;
                        g2d.drawString("Press \'R\' to Restart", x1, y1);
                    }
                    if(combodisplay != 0 && combo > 1)
                    {
                        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 70));
                        fm = g2d.getFontMetrics();
                        x1 = (getWidth() - fm.stringWidth("Combo x" + combo + "!")) / 2;
                        y1 = ((getHeight() - fm.getHeight()) + fm.getAscent()) / 2;
                        combodisplay++;
                        if(combodisplay == 50)
                        {
                            combodisplay = 0;
                        }
                        if(combodisplay % 3 == 1){
                            g2d.setColor(Color.RED);
                        }
                        else if(combodisplay % 3 == 2){
                            g2d.setColor(Color.YELLOW);
                        }
                        else{
                            g2d.setColor(Color.ORANGE);
                        }
                        g2d.drawString("Combo x" + combo + "!", x1, y1);
                    }
                    g2d.dispose();
            }
        }
}
