import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Model {

    static JFrame jFrame = getjFrame();
    static JPanel jPanel = new JPanel();
    public String name;
    public String comment;
    public String number;
    public String tech;
    public String insp;
    public JTextField nameText;
    public JTextField countText;
    public JTextField numberText;
    public JTextField technicText;
    public JTextField inspireText;
    public JTextField impressionText;
    public JTextArea commentText;
    public JButton buttonComment;
    public JButton next;



    public Model() throws IOException {
    }

    static JFrame getjFrame() {
        JFrame jFrame = new JFrame("Grading") {
        };
        jFrame.setVisible(true);
        jFrame.setBounds(750, 250, 360, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jFrame;
    }

    public void init() throws IOException {

        jFrame.add(jPanel);

        JLabel numberLabel = new JLabel("  Порядковый номер участника  ");
        jPanel.add(numberLabel);

        numberText = new JTextField(12);
        numberText.setFont(new Font("Lucida Console", Font.PLAIN, 17));
        jPanel.add(numberText);

        JLabel nameLabel = new JLabel("  Имя  ");

        jPanel.add(nameLabel);

        nameText = new JTextField(25);
        nameText.setFont(new Font("Lucida Console", Font.PLAIN, 17));

        jPanel.add(nameText);

        JLabel technic = new JLabel("   Техника   ");
        jPanel.add(technic);
        technicText = new JTextField(3);
        technicText.setFont(new Font("Lucida Console", Font.PLAIN, 17));
        jPanel.add(technicText);

        JLabel inspire = new JLabel("Раскрытие");
        jPanel.add(inspire);
        inspireText = new JTextField(3);
        inspireText.setFont(new Font("Lucida Console", Font.PLAIN, 17));
        jPanel.add(inspireText);

        JLabel commentLabel = new JLabel("     Комментарий   (по желанию)    ");
        jPanel.add(commentLabel);

        commentText = new JTextArea(10,30);
        jPanel.add(commentText);

        buttonComment = new JButton("Записать данные");
        buttonComment.addActionListener(new ButtonActionListener());
        jPanel.add(buttonComment);

        next = new JButton("Перейти к следующей записи");
        next.addActionListener(new  ButtonNextActionListener());

        jPanel.add(next);
        jPanel.revalidate();

    }


    public class ButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            number = numberText.getText();
            name = nameText.getText();
            tech = technicText.getText();
            insp = inspireText.getText();
            comment = commentText.getText();

            try {
                File file = new File("result.txt");
                FileWriter writer = new FileWriter(file,true);

                writer.write("*********************************************************" + "\n");
                writer.write(number + ".  " + name + "\n");
                writer.write("\n");

                writer.write("Техника:  " + tech + "  Раскрытие:  " + insp);
                writer.write("\n");

                double result = 0;
                if (!tech.isEmpty()){
                     result += Double.parseDouble(tech);
                }
                if (!insp.isEmpty()){
                    result += Double.parseDouble(insp);
                }

                writer.write("Итоговая оценка :  " + result);
                writer.write("\n");
                writer.write("Комментарий:  " + comment + "\n");
                writer.write("\n");
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
    public class ButtonNextActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            number = String.valueOf(Integer.parseInt(number) + 1);
            name = "";
            comment = "";
            tech = "";
            insp = "";
            inspireText.setText(insp);
            technicText.setText(tech);
            numberText.setText(number);
            nameText.setText("");
            commentText.setText("");
        }
    }

}
