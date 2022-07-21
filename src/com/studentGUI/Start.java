package com.studentGUI;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

import static java.lang.System.exit;
import static com.studentGUI.XMLUtils.XML2list;
import static com.studentGUI.XMLUtils.list2XML;

public class Start {
    private JFrame frame;
    private Student student;
    //private static List<Student> STUDENTS = new ArrayList<Student>();
    private static String FILEPATH = "";

    // 全局对象 交换信息用
    private StudentList stList;
    private Screen screen;
    private MyTopPanel topPanel;
    private MyTable    myTable;
    private MyScreenPanel screenPanel;

    public Start() {
        student     = new Student();
        frame       = new JFrame("STUDENT");
        stList      = new StudentList();


        //
        stList = new StudentList();
        topPanel    = new MyTopPanel();
        myTable     = new MyTable();
        screenPanel = new MyScreenPanel();
        screen      = screenPanel.getScreen();

        topPanel.set(screenPanel,myTable,topPanel,stList);
        myTable.set(screenPanel,myTable,topPanel,stList);
        screenPanel.set(screenPanel,myTable,topPanel,stList);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 1, 1, 1));    //为Frame窗口设置布局为BorderLayout
        frame.setJMenuBar(menuBar());
        frame.add(topPanel.getTopPanel());
        frame.add(myTable.getPanel(), BorderLayout.CENTER);
        frame.setBounds(300, 200, 600, 300);
        frame.setVisible(true);
    }

    public JMenuBar menuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        return menuBar;
    }


    private void refreshFrame() {
        System.out.println("fresh screen!");
        frame.getContentPane().removeAll();
        frame.setMinimumSize(new Dimension(600, 500));
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 1, 1, 1));    //为Frame窗口设置布局为BorderLayout
        // frame.setJMenuBar(menuBar());
        frame.add(topPanel.getTopPanel());
        frame.add(myTable.getPanel());
        frame.setVisible(true);
    }

    private JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        JMenuItem item = new JMenuItem("New(N)", KeyEvent.VK_N);//使用指定的文本和键盘助记符创建 JMenuItem 。
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        item.setName("createNew");
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        item = new JMenuItem("Open(O)", KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        item.setName("openFile");
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        item = new JMenuItem("Save(S)", KeyEvent.VK_S);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        item.setName("saveFile");
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Exit(E)", KeyEvent.VK_E);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        item.setName("exit");
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        return menu;
    }

    class fileMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "New(N)":
                    funcNew();
                    break;
                case "Open(O)":
                    funcOpen();
                    break;
                case "Save(S)":
                    funcSave();
                    break;
                case "Exit(E)":
                    funcExit();
            }
        }

        private void funcExit() {
            int n = JOptionPane.showConfirmDialog(null,"确认退出？","确认",
                    JOptionPane.YES_NO_OPTION );
            if (n == JOptionPane.YES_OPTION){
                exit(0);
            }
        }

        private void funcSave() {
            if (FILEPATH.length() == 0){
                FILEPATH = chooseFile("保存");
            }
            if (FILEPATH.length() == 0){
                return;
            }
            //list2XML(FILEPATH);
            File file = new File(FILEPATH);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "文件创建失败！", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            String writeString = list2XML(stList.getSTUDENTS());
            if ( file.canWrite()){
                OutputStream stream = null;
                try {
                    stream = new FileOutputStream(file);
                    stream.write(writeString.getBytes("UTF8"));
                    JOptionPane.showMessageDialog(null,"写入成功！","SUCCESS",JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"写入失败！","ERROR",JOptionPane.ERROR_MESSAGE);
                }finally {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            FILEPATH = "";
        }




        private String chooseFile(String tip) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML文件", "xml", "XML", "txt");
           // filter.a
            JFileChooser fc = new JFileChooser();
            fc.setToolTipText(tip);
            fc.setApproveButtonText(tip);
            fc.setDialogTitle(tip);
            fc.setApproveButtonToolTipText(tip);
            fc.setFileFilter(filter);                   //设置一个文件筛选器
            fc.setMultiSelectionEnabled(false);         //设置不允许多选
            /*使用showOpenDialog()方法，显示出打开选择文件的窗口，当选择了某个文件后，或者关闭此窗口那么都会返回一个
            整型数值，如果返回的是0，代表已经选择了某个文件。如果返回1代表选择了取消按钮或者直接关闭了窗口*/
            int result = fc.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                //获取当前选择的文件路径
                File file = fc.getSelectedFile();
                //返回文件路径
                return file.getPath();
            }
            JOptionPane.showMessageDialog(null,"取消！","失败",JOptionPane.ERROR_MESSAGE);
            return "";
        }

        private void funcOpen() {
            FILEPATH = chooseFile("打开");
            if (FILEPATH.length() == 0){
                return;
            }
            File file = new File(FILEPATH);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "文件不存在！", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            try {
                //stList.setSTUDENTS(new ArrayList<Student>());
                StringBuffer XMLBuffer = new StringBuffer();
                Scanner input = new Scanner(file);
                while (input.hasNextLine()){
                    XMLBuffer.append(input.nextLine());
                }
                System.out.println("***********************************\n"
                        +"FILE VALUE:\n"
                        +XMLBuffer.toString() +
                        "************************");
                input.close();
                stList.setSTUDENTS(XML2list(XMLBuffer.toString()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            refreshFrame();
        }

        private void funcNew() {
        }
    }

    private JMenu createEditMenu() {
        JMenu menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        JMenuItem item = new JMenuItem("暂无功能，就是想加上", KeyEvent.VK_N);//使用指定的文本和键盘助记符创建 JMenuItem 。
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        return menu;
    }


    public JFrame getFrame() {
        return frame;
    }

//    public static void main(String[] args) {
//        Start s = new Start();
//    }
}
