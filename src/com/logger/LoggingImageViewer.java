package com.logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.*;
import javax.swing.*;

public class LoggingImageViewer {
    public static void main(String[] args) {
        if (System.getProperty("java.util.logging.config.class") == null
        && System.getProperty("java.util.logging.config.file") == null) {
            try {
                Logger.getLogger("com.logger.log").setLevel(Level.ALL);
                final int LOG_ROTATION_CONT = 10;
                var handler = new FileHandler("%/LoggingImageViewer.log", 0, LOG_ROTATION_CONT);
                Logger.getLogger("com.logger.log").addHandler(handler);
            } catch (IOException e) {
                Logger.getLogger("com.logger.log").log(Level.SEVERE, "Can't create log file handler", e);
            }
        }
        EventQueue.invokeLater(()->{



            var windowHandler = new WindowHandler();
            windowHandler.setLevel(Level.ALL);
            Logger.getLogger("com.logger.log").addHandler(windowHandler);

            var frame = new ImageViewerFrame();

        });
    }
}
class ImageViewerFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    private static JLabel label;
    private static Logger logger = Logger.getLogger("com.logger.log");

    public ImageViewerFrame(){
        logger.entering("ImageViewerFrame", "<init>");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //SET UP MENU BAR
        var menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        var menu = new JMenu("File");
        menu.add(menu);

        var openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        var exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.fine("Exiting:...");
                System.exit(0);
            }
        });
    }
    private class FileOpenListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", e);

            //set up file chooser
            var chooser = new JFileChooser();
            chooser.setFileFilter(new javax.swing.filechooser.FileFilter(){

                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "GIF Images";
                }
            });
            int r = chooser.showOpenDialog(ImageViewerFrame.this);
            if (r == JFileChooser.APPROVE_OPTION){
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            }else logger.fine("File open dialog canceled.");
            logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
        }
    }
}

class WindowHandler extends StreamHandler{
    private JFrame jFrame;
    public WindowHandler(){
        jFrame = new JFrame();
        var output = new JTextArea();
        output.setEditable(false);
        jFrame.setSize(200, 200);
        jFrame.add(new JScrollPane(output));
        jFrame.setFocusableWindowState(false);
        jFrame.setVisible(true);
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }//not called
            @Override
            public void write(byte[] b, int off, int len){
                output.append(new String(b, off, len));
            }
        });
    }
    public void publish(LogRecord record){
        if (!jFrame.isVisible())return;
        super.publish(record);
        flush();
    }
}
