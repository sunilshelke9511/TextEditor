package com.company;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class simpleTextEditor implements ActionListener {
    JFrame frame = new JFrame("Simple Text Editor ");
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File;
    JMenu Edit;
    JMenu Close;
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;
    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;
    JMenuItem CloseEditor;

    simpleTextEditor() {
        this.frame.setBounds(0, 0, 800, 1000);
        this.jTextArea = new JTextArea("Welcome to Text Editor");
        this.frame.add(this.jTextArea);
        this.jMenuBar = new JMenuBar();
        this.Close = new JMenu("Close");
        this.Edit = new JMenu("Edit");
        this.File = new JMenu("File");
        this.jMenuBar.add(this.File);
        this.jMenuBar.add(this.Edit);
        this.jMenuBar.add(this.Close);
        this.frame.setJMenuBar(this.jMenuBar);
        this.OpenFile = new JMenuItem("Open");
        this.OpenFile.addActionListener(this);
        this.SaveFile = new JMenuItem("Save");
        this.SaveFile.addActionListener(this);
        this.NewFile = new JMenuItem("New");
        this.NewFile.addActionListener(this);
        this.PrintFile = new JMenuItem("Print");
        this.PrintFile.addActionListener(this);
        this.Copy = new JMenuItem("Copy");
        this.Copy.addActionListener(this);
        this.Cut = new JMenuItem("Cut");
        this.Cut.addActionListener(this);
        this.Paste = new JMenuItem("Paste");
        this.Paste.addActionListener(this);
        this.CloseEditor = new JMenuItem("Close");
        this.CloseEditor.addActionListener(this);
        this.Edit.add(this.Cut);
        this.Edit.add(this.Copy);
        this.Edit.add(this.Paste);
        this.File.add(this.NewFile);
        this.File.add(this.OpenFile);
        this.File.add(this.SaveFile);
        this.File.add(this.PrintFile);
        this.Close.add(this.CloseEditor);
        this.frame.setDefaultCloseOperation(3);
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        new simpleTextEditor();
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Copy")) {
            this.jTextArea.copy();
        } else if (s.equals("Cut")) {
            this.jTextArea.cut();
        } else if (s.equals("Paste")) {
            this.jTextArea.paste();
        } else if (s.equals("Print")) {
            try {
                this.jTextArea.print();
            } catch (PrinterException var10) {
                throw new RuntimeException(var10);
            }
        } else if (s.equals("Close")) {
            this.frame.setVisible(false);
        } else {
            JFileChooser jFileChooser;
            int ans;
            File file;
            String s1;
            if (s.equals("Open")) {
                jFileChooser = new JFileChooser("C:");
                ans = jFileChooser.showOpenDialog((Component)null);
                if (ans == 0) {
                    file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                    s1 = "";
                    String s2 = "";

                    try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                        for(s2 = bufferedReader.readLine(); (s1 = bufferedReader.readLine()) != null; s2 = s2 + s1 + "\n") {
                        }

                        this.jTextArea.setText(s2);
                    } catch (FileNotFoundException var11) {
                        throw new RuntimeException(var11);
                    } catch (IOException var12) {
                        throw new RuntimeException(var12);
                    }
                }
            } else if (s.equals("Save")) {
                jFileChooser = new JFileChooser("C:");
                ans = jFileChooser.showOpenDialog((Component)null);
                if (ans == 0) {
                    file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                    s1 = null;

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
                        writer.write(this.jTextArea.getText());
                        writer.flush();
                        writer.close();
                    } catch (IOException var9) {
                        throw new RuntimeException(var9);
                    }
                }
            }
        }

    }
}
