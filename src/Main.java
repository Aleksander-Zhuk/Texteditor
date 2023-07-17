import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}
class TextEditor extends JFrame {
    TextEditor (){
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        Mypanel mypanel =new Mypanel();
        add(mypanel);

        setVisible(true);
    }
}
class Mypanel extends JPanel {
    private JTextArea textArea = new JTextArea();
    private JLabel label2 = new JLabel();
    File selectedFile;
    Mypanel () {
        setSize(800, 800);
        setLayout(null);

        JButton button1 = new JButton();
        button1.setBounds(20, 40, 120, 40);
        button1.setText("Открыть файл");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    label2.setText(fileName);
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            textArea.append(line + "\n");
                        }
                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        add(button1);

        JButton button2 = new JButton();
        button2.setBounds(510, 40, 120, 40);
        button2.setText("Сохранить");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                        writer.write(textArea.getText());
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        add(button2);

        JButton button3 = new JButton();
        button3.setBounds(650, 40, 120, 40);
        button3.setText("Сохранить как");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(textArea.getText());
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        add(button3);

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(20, 120, 750, 620);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scroll);

        JLabel label1 = new JLabel();
        label1.setBounds(160,40,120,40);
        label1.setText("Открытый файл : ");
        add(label1);

        label2.setBounds(280,40,230,40);
        add(label2);
    }
}