package main.game;

import main.game.zwierzeta.*;
import main.game.rosliny.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements KeyListener {
    JFrame gameFrame;
    Swiat swiat;
    public GUI(int m, int n){
        this.swiat = new Swiat(m,n);
        this.gameFrame = new JFrame();
        this.gameFrame.setTitle("Wirtualny Swiat    by Tomasz Sankowski");
        this.gameFrame.setSize(1000, 500);
        this.gameFrame.setLocationRelativeTo(null);

        rysujSwiat();
        gameFrame.addKeyListener(this);
        gameFrame.setFocusable(true);
        gameFrame.requestFocusInWindow();
    }

    public void rysujSwiat(){
        gameFrame.getContentPane().removeAll();

        JPanel gamePanel = new JPanel(new GridLayout(this.swiat.getM(), this.swiat.getN(), 0, 0));
        gamePanel.setOpaque(false);
        gamePanel.setBorder(BorderFactory.createEmptyBorder());

        for (int i = 0; i < this.swiat.getM(); i++) {
            for(int j = 0; j < this.swiat.getN(); j++){
                final int row = i;
                final int column = j;
                String filename = this.swiat.zwrocPlik(i,j);
                JLabel label;
                if(!filename.equals("noanimal")){
                    ImageIcon originalIcon = new ImageIcon(filename);
                    Image originalImage = originalIcon.getImage();
                    Image scaledImage = originalImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    label = new JLabel(scaledIcon);

                }
                else {
                    label = new JLabel();
                    label.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            ButtonGroup buttonGroup = new ButtonGroup();

                            JRadioButton antylopaButton = new JRadioButton("Antylopa");
                            JRadioButton lisButton = new JRadioButton("Lis");
                            JRadioButton owcaButton = new JRadioButton("Owca");
                            JRadioButton wilkButton = new JRadioButton("Wilk");
                            JRadioButton zlowButton = new JRadioButton("Zlow");
                            JRadioButton barszczButton = new JRadioButton("Barszcz sosnowskiego");
                            JRadioButton guaranaButton = new JRadioButton("Guarana");
                            JRadioButton mleczButton = new JRadioButton("Mlecz");
                            JRadioButton trawaButton = new JRadioButton("Trawa");
                            JRadioButton jagodyButton = new JRadioButton("Wilecz jagody");

                            buttonGroup.add(antylopaButton);
                            buttonGroup.add(lisButton);
                            buttonGroup.add(owcaButton);
                            buttonGroup.add(wilkButton);
                            buttonGroup.add(zlowButton);
                            buttonGroup.add(barszczButton);
                            buttonGroup.add(guaranaButton);
                            buttonGroup.add(mleczButton);
                            buttonGroup.add(trawaButton);
                            buttonGroup.add(jagodyButton);

                            JPanel panel = new JPanel();
                            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                            panel.add(antylopaButton);
                            panel.add(lisButton);
                            panel.add(owcaButton);
                            panel.add(wilkButton);
                            panel.add(zlowButton);
                            panel.add(barszczButton);
                            panel.add(guaranaButton);
                            panel.add(mleczButton);
                            panel.add(trawaButton);
                            panel.add(jagodyButton);

                            int result = JOptionPane.showOptionDialog(gameFrame, panel, "Wybierz organizm, który chcesz postawić na kliknięte pole:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                            if (result == JOptionPane.OK_OPTION) {
                                if (antylopaButton.isSelected()) {
                                    Antylopa nowyOrganizm = new Antylopa(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                } else if (lisButton.isSelected()) {
                                    Lis nowyOrganizm = new Lis(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                } else if (owcaButton.isSelected()) {
                                    Owca nowyOrganizm = new Owca(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                } else if (wilkButton.isSelected()) {
                                    Wilk nowyOrganizm = new Wilk(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                } else if (zlowButton.isSelected()) {
                                    Zlow nowyOrganizm = new Zlow(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                } else if (barszczButton.isSelected()) {
                                    BarszczSosnowskiego nowyOrganizm = new BarszczSosnowskiego(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                } else if (guaranaButton.isSelected()) {
                                    Guarana nowyOrganizm = new Guarana(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                } else if (mleczButton.isSelected()) {
                                    Mlecz nowyOrganizm = new Mlecz(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                } else if (trawaButton.isSelected()) {
                                    Trawa nowyOrganizm = new Trawa(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                } else if (jagodyButton.isSelected()) {
                                    WilczeJagody nowyOrganizm = new WilczeJagody(row, column, swiat);
                                    swiat.dodajOrganizm(nowyOrganizm);
                                }}
                            rysujSwiat();
                        }
                    });
                }
                label.setPreferredSize(new Dimension(30, 30));
                label.setMaximumSize(new Dimension(30, 30)); //
                label.setMinimumSize(new Dimension(30, 30));
                label.setSize(new Dimension(30, 30));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setOpaque(false);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setOpaque(true);
                gamePanel.add(label);
            }
        }

        this.gameFrame.add(gamePanel);

        JTextArea logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);
        logTextArea.setPreferredSize(new Dimension(200, gamePanel.getHeight()));


        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(logTextArea, BorderLayout.CENTER);
        this.gameFrame.add(leftPanel, BorderLayout.WEST);


        for (int ii = 0; ii < this.swiat.iloscLogow(); ii++) {
            logTextArea.append(this.swiat.getlog(ii) + "\n");
        }
        this.swiat.wyczyscLogi();


        this.gameFrame.pack();

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        JButton endTurnButton = new JButton("Zakończ turę");
        endTurnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameFrame.dispose();
                System.exit(0);
            }
        });
        bottomPanel.add(endTurnButton, BorderLayout.EAST);
        this.gameFrame.add(bottomPanel, BorderLayout.SOUTH);

        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        gameFrame.revalidate();
        gameFrame.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='q')
            this.gameFrame.dispose();
        else if(e.getKeyChar()=='l') {
            this.swiat.wczytajSwiat();
            rysujSwiat();
        }
        else if(e.getKeyChar()=='s'){
            this.swiat.zapiszSwiat();
            rysujSwiat();
        }
        else if (e.getKeyChar() == 'p') {//aktywuj supermoc
            this.swiat.aktywujTarczeAlzura();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
            if(swiat.wykonajTure(e)){
                this.gameFrame.dispose();
                JOptionPane.showMessageDialog(null, "KONIEC GRY!");
                System.exit(0);
            }
            rysujSwiat();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    public void delete(){
        this.gameFrame.dispose();
    }
}