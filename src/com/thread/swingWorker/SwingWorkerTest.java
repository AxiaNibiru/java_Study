package com.thread.swingWorker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class SwingWorkerTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            var frame = new SwingWorkerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }


}
class SwingWorkerFrame extends JFrame{
    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 60;
    private JFileChooser chooser;
    private JTextArea textArea;
    private JLabel statuesLine;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private SwingWorker<StringBuilder, ProgressData> textReader;

    public SwingWorkerFrame(){
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./file/"));
        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane(textArea));

        statuesLine = new JLabel(" ");
        add(statuesLine, BorderLayout.SOUTH);

        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        var menu = new JMenu("File");
        menuBar.add(menu);

        openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(event -> {
            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION){
                textArea.setText("");
                openItem.setEnabled(false);
                textReader = new TextReader(chooser.getSelectedFile());
                textReader.execute();
                cancelItem.setEnabled(true);
            }
        });
        cancelItem = new JMenuItem("cancel");
        menu.add(cancelItem);
        cancelItem.setEnabled(false);
        cancelItem.addActionListener(e -> textReader.cancel(true));
        pack();
    }
    private class ProgressData{
        public int number;
        public String line;
    }

    private class TextReader extends SwingWorker<StringBuilder, ProgressData>{
        private File file;
        private StringBuilder text = new StringBuilder();

        public TextReader(File file){
            this.file = file;
        }

        public StringBuilder doInBackground() throws IOException, InterruptedException{
            int lineNumber = 0;
            try (var in = new Scanner(new FileInputStream(file), StandardCharsets.UTF_8)){
                while (in.hasNext()){
                    String line = in.nextLine();
                    lineNumber++;
                    text.append(line).append("\n");
                    var data = new ProgressData();
                    data.number = lineNumber;
                    data.line = line;
                    publish(data);
                    Thread.sleep(1);
                }
            }
            return text;
        }
        public void process(List<ProgressData> data){
            if (isCancelled()) return;
            var builder = new StringBuilder();
            statuesLine.setText("" + data.get(data.size() - 1).number);
            for (ProgressData data1 : data){
                builder.append(data1.line).append("\n");
                textArea.append(builder.toString());
            }
        }
        public void done(){
            try {
                StringBuilder result = get();
                textArea.setText(result.toString());
                statuesLine.setText("Done");
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }catch (CancellationException ex){
                textArea.setText("");
                statuesLine.setText("Cancelled");
            }catch (ExecutionException ex){
                statuesLine.setText("" + ex.getCause());
            }
            cancelItem.setEnabled(false);
            openItem.setEnabled(true);
        }
    }
}



