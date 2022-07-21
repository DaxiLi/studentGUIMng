package com.studentGUI;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class MyTopPanel {
    JSplitPane MAIN_PANEL;
    String INSERT_VALUE;
    String CLEAN_VALUE;


    // 全局对象 交换信息用
    private StudentList STUDENTLIST;
    private Screen screen;
    private MyTopPanel topPanel;
    private MyTable     TABLE;
    private MyScreenPanel screenPanel;


    Student         student;
    JButton button;
    JButton buttonC;
    JSplitPane      jSplitPane; //fresh 对象
    JLabel          nameLabel;
    JLabel          genderLabel ;
    JLabel          birthLabel ;
    JLabel          yearLabel;
    JLabel          monthLabel;
    JLabel          dayLabel;
    JLabel          idLabel;
    JLabel          classLabel;
    JLabel          schoolLabel;
    JLabel          otherLabel;
    JComboBox       genderBox;
    JComboBox       ageYearBox;
    JComboBox       monthBox;
    JComboBox       dayBox;
    JTextField      nameTextField;
    JTextField      idTextField ;
    JTextField      classTextField ;
    JTextField      schoolTextField ;
    JTextArea       otherTextField ;
    JPanel          birthPanel;


    MyTopPanel(){
        MAIN_PANEL  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        student             = new Student();
        jSplitPane          = new JSplitPane();
        nameLabel           = new JLabel("姓名:");
        nameTextField       = new JTextField(8);
        genderLabel         = new JLabel("性别:");
        genderBox           = new JComboBox();    //创建JComboBox
        birthLabel          = new JLabel("年龄:");
        ageYearBox          = new JComboBox();    //创建JComboBox
        yearLabel           = new JLabel("年");
        monthBox            = new JComboBox();    //创建JComboBox
        monthLabel          = new JLabel("月");
        dayBox              = new JComboBox();    //创建JComboBox
        dayLabel            = new JLabel("");
        idTextField         = new JTextField(10);
        idLabel             = new JLabel("学号：");
        classTextField      = new JTextField(10);
        classLabel          = new JLabel("班级：");
        schoolTextField     = new JTextField(15);
        schoolLabel         = new JLabel("学校：");
        otherTextField      = new JTextArea();
        otherLabel          = new JLabel("备注：");
        birthPanel          = new JPanel();
        INSERT_VALUE        = "插入";
        CLEAN_VALUE         = "清除";

    }

    public void set(MyScreenPanel screenPanel, MyTable myTable, MyTopPanel myTopPanel, StudentList list){
        this.screenPanel = screenPanel;
        this.topPanel    = myTopPanel;
        this.STUDENTLIST = list;
        this.screenPanel = screenPanel;
        this.TABLE       = myTable;
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void refresh(){
        setPanel();
        MAIN_PANEL.setRightComponent(screenPanel.getScreenPanel());
    }

    public JSplitPane getTopPanel() {
        MAIN_PANEL = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        setPanel();
        MAIN_PANEL.setRightComponent(screenPanel.getScreenPanel());
        return MAIN_PANEL;
    }
    public JSplitPane setPanel() {
        JPanel p = new JPanel();
        GridBagLayout bagLayout = new GridBagLayout();    //创建GridBagLayout布局管理器
        GridBagConstraints constraints = new GridBagConstraints();
        p.setLayout(bagLayout);    //使用GridBagLayout布局管理器
        constraints.fill = GridBagConstraints.BOTH;    //组件填充显示区域
        constraints.weightx = 0.0;    //

        birthPanel.setLayout(bagLayout);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        p.add(nameLabel, constraints);

        constraints.gridx = 6;
        constraints.gridwidth = 9;
        p.add(nameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 4;
        birthPanel.add(birthLabel, constraints);

        ageYearBox.removeAllItems();
        for (int i = Calendar.getInstance().get(Calendar.YEAR); i > 1900; i--) {
            ageYearBox.addItem(i);
        }
        ageYearBox.setSelectedItem(2000);
        constraints.gridx = 5;
        constraints.gridwidth = 4;
        birthPanel.add(ageYearBox, constraints);

        constraints.gridx = 10;
        constraints.gridwidth = 4;
        birthPanel.add(yearLabel, constraints);

        monthBox.removeAllItems();
        for (int i = 1; i < 13; i++) {
            monthBox.addItem(i);
        }
        monthBox.setSelectedItem(1);
        constraints.gridx = 15;
        constraints.gridwidth = 2;
        birthPanel.add(monthBox, constraints);


        constraints.gridx = 18;
        constraints.gridwidth = 4;
        birthPanel.add(monthLabel, constraints);

        for (int i = 1; i < 32; i++) {
            dayBox.addItem(i);
        }
        dayBox.setSelectedItem(1);
        constraints.gridx = 23;
        constraints.gridwidth = 2;
        if (student.getBirthDate() == null) {
            student.setBirth(2000, 1, 1);
        }
        birthPanel.add(dayBox, constraints);

        constraints.gridx = 26;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        birthPanel.add(dayLabel, constraints);


        constraints.gridx = 15;
        constraints.gridy = 0;
        constraints.gridwidth = 29;
        p.add(birthPanel, constraints);

        constraints.gridx = 45;
        constraints.gridwidth = 1;
        p.add(new JPanel(), constraints);

        constraints.gridx = 46;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        p.add(genderLabel, constraints);


        genderBox.removeAllItems();
        genderBox.addItem(Gender.MAN);
        genderBox.addItem(Gender.FEMALE);
        genderBox.addItem(Gender.OTHER);
        constraints.gridx = 50;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        p.add(genderBox, constraints);


        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        p.add(schoolLabel, constraints);


        constraints.gridx = 6;
        constraints.gridwidth = 15;
        p.add(schoolTextField, constraints);

        constraints.gridx = 22;
        constraints.gridwidth = 3;
        p.add(classLabel, constraints);

        constraints.gridx = 26;
        constraints.gridwidth = 15;
        p.add(classTextField, constraints);

        constraints.gridx = 42;
        p.add(idLabel, constraints);

        constraints.gridx = 50;
        p.add(idTextField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.gridheight = 4;
        p.add(otherLabel, constraints);

        constraints.gridy = 1;
        constraints.gridheight = 1;
        p.add(new JPanel(), constraints);
        constraints.gridy = 3;
        p.add(new JPanel(), constraints);


        constraints.gridx = 6;
        constraints.gridy = 4;
        constraints.gridwidth = 0;
        constraints.gridheight = 4;
        otherTextField.setRows(4);
        otherTextField.setColumns(30);
        otherTextField.setLineWrap(true);
        otherTextField.setAutoscrolls(true);
        p.add(otherTextField, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 8, 0, 0));
        JButton button = new JButton(INSERT_VALUE);
        button.setBackground(Color.white);
        buttonC = new JButton(CLEAN_VALUE);
        buttonC.setBackground(Color.white);
        buttonPanel.add(button);
        buttonPanel.add(buttonC);


        constraints.gridx = 6;
        constraints.gridy = 9;
        constraints.gridheight = 1;
        p.add(new JPanel(), constraints);
        constraints.gridy = 10;
        p.add(buttonPanel, constraints);

        //初始化
        nameTextField.setText(student.getName());
        genderBox.setSelectedItem(student.getGender());
        schoolTextField.setText(student.getSchool());
        classTextField.setText(student.getStudentClass());
        idTextField.setText(student.getStudentID());
        otherTextField.setText(student.getOthers());
        ageYearBox.setSelectedItem(student.getBirthDate().getY());
        monthBox.setSelectedItem(student.getBirthDate().getM());
        dayBox.setSelectedItem(student.getBirthDate().getD());


        MAIN_PANEL.setLeftComponent(p);
        MAIN_PANEL.getLeftComponent().setMinimumSize(new Dimension(500, 600));


       
        ageYearBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ageYearBox.getSelectedItem() == null ||
                        monthBox.getSelectedItem() == null){
                    return;
                }
                int y = (int) ageYearBox.getSelectedItem();
                int m = (int) monthBox.getSelectedItem();
                Calendar calendar = Calendar.getInstance();
                calendar.set(y - 1900, m - 1, 1);
                calendar.roll(Calendar.DATE, -1);
                int max = calendar.get(Calendar.DATE);
                dayBox.removeAllItems();
                for (int i = 1; i <= max; i++) {
                    dayBox.addItem(i);
                }
            }
        });

        monthBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ageYearBox.getSelectedItem() == null ||
                        monthBox.getSelectedItem() == null){
                    return;
                }
                int m = (int) monthBox.getSelectedItem();
                int y = (int) ageYearBox.getSelectedItem();
                Calendar calendar = Calendar.getInstance();
                calendar.set(y - 1900, m - 1, 1);
                calendar.roll(Calendar.DATE, -1);
                int max = calendar.get(Calendar.DATE);
                dayBox.removeAllItems();
                for (int i = 1; i <= max; i++) {
                    dayBox.addItem(i);
                }
            }
        });


        /**
         * 清空按钮
         */
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("you Click INSERT!");
//                if (buttonC.getText().equals("删除")){
//                    STUDENTLIST.deleteById(student.getStudentID());
//                    JOptionPane.showConfirmDialog(null, "成功！", "提示",
//                            JOptionPane.PLAIN_MESSAGE);
                    INSERT_VALUE = "插入";
//                    CLEAN_VALUE = "清空";
//                }
                student = new Student();
                refresh();
            }
        });
        /**
         * 插入按钮
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("you press INSERT!");
                student.setName(nameTextField.getText());
                student.setBirth((int) ageYearBox.getSelectedItem(), (int) monthBox.getSelectedItem(), (int) dayBox.getSelectedItem());
                student.setStudentID(idTextField.getText());
                student.setGender((String) genderBox.getSelectedItem());
                student.setSchool(schoolTextField.getText());
                student.setStudentClass(classTextField.getText());
                student.setSchool(schoolTextField.getText());
                if (allFilled().length() != 0) {
                    JOptionPane.showMessageDialog(null, "请输入：" + allFilled() + "字段", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (button.getText().equals("保存")){
                    JOptionPane.showConfirmDialog(null, "成功！", "提示",
                            JOptionPane.PLAIN_MESSAGE);
                    INSERT_VALUE = "插入";
                    CLEAN_VALUE = "清空";
                    student = new Student();
                    refresh();
                    TABLE.refresh();
                    return;
                }
                if (STUDENTLIST.getById(student.getStudentID()) != null) {
                    int rs = JOptionPane.showConfirmDialog(null, "ID 已存在，继续将覆盖原来数据，继续？？", "警告",
                            JOptionPane.YES_NO_OPTION);
                    if (rs == JOptionPane.YES_OPTION) {
                        STUDENTLIST.updateStudent(student);
                    }
                    return;
                }
                System.out.println("\n插入学生为：" + student.toString());
                STUDENTLIST.add(student);
                student = new Student();
                refresh();
                TABLE.refresh();
            }


            public String allFilled() {
                if (student.getGender() == null || student.getGender().replace(" ", "").length() == 0) {
                    return "性别";
                } else if (student.getSchool() == null || student.getSchool().replace(" ", "").length() == 0) {
                    return "学校";
                } else if (student.getName() == null || student.getName().replace(" ", "").length() == 0) {
                    return "姓名";
                } else if (student.getStudentID() == null || student.getStudentID().replace(" ", "").length() == 0) {
                    return "学号";
                } else {
                    return "";
                }
            }
        });

        otherTextField.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                student.setOthers(otherTextField.getText());
            }
        });

        return MAIN_PANEL;
    }

    public String getINSERT_VALUE() {
        return INSERT_VALUE;
    }

    public void setINSERT_VALUE(String INSERT_VALUE) {
        this.INSERT_VALUE = INSERT_VALUE;
    }

    public String getCLEAN_VALUE() {
        return CLEAN_VALUE;
    }

    public void setCLEAN_VALUE(String CLEAN_VALUE) {
        this.CLEAN_VALUE = CLEAN_VALUE;
    }

    public JSplitPane getjSplitPane() {
        return jSplitPane;
    }

    public void setjSplitPane(JSplitPane jSplitPane) {
        this.jSplitPane = jSplitPane;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JLabel getGenderLabel() {
        return genderLabel;
    }

    public void setGenderLabel(JLabel genderLabel) {
        this.genderLabel = genderLabel;
    }

    public JLabel getBirthLabel() {
        return birthLabel;
    }

    public void setBirthLabel(JLabel birthLabel) {
        this.birthLabel = birthLabel;
    }

    public JLabel getYearLabel() {
        return yearLabel;
    }

    public void setYearLabel(JLabel yearLabel) {
        this.yearLabel = yearLabel;
    }

    public JLabel getMonthLabel() {
        return monthLabel;
    }

    public void setMonthLabel(JLabel monthLabel) {
        this.monthLabel = monthLabel;
    }

    public JLabel getDayLabel() {
        return dayLabel;
    }

    public void setDayLabel(JLabel dayLabel) {
        this.dayLabel = dayLabel;
    }

    public JLabel getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(JLabel idLabel) {
        this.idLabel = idLabel;
    }

    public JLabel getClassLabel() {
        return classLabel;
    }

    public void setClassLabel(JLabel classLabel) {
        this.classLabel = classLabel;
    }

    public JLabel getSchoolLabel() {
        return schoolLabel;
    }

    public void setSchoolLabel(JLabel schoolLabel) {
        this.schoolLabel = schoolLabel;
    }

    public JLabel getOtherLabel() {
        return otherLabel;
    }

    public void setOtherLabel(JLabel otherLabel) {
        this.otherLabel = otherLabel;
    }

    public JComboBox getGenderBox() {
        return genderBox;
    }

    public void setGenderBox(JComboBox genderBox) {
        this.genderBox = genderBox;
    }

    public JComboBox getAgeYearBox() {
        return ageYearBox;
    }

    public void setAgeYearBox(JComboBox ageYearBox) {
        this.ageYearBox = ageYearBox;
    }

    public JComboBox getMonthBox() {
        return monthBox;
    }

    public void setMonthBox(JComboBox monthBox) {
        this.monthBox = monthBox;
    }

    public JComboBox getDayBox() {
        return dayBox;
    }

    public void setDayBox(JComboBox dayBox) {
        this.dayBox = dayBox;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    public void setIdTextField(JTextField idTextField) {
        this.idTextField = idTextField;
    }

    public JTextField getClassTextField() {
        return classTextField;
    }

    public void setClassTextField(JTextField classTextField) {
        this.classTextField = classTextField;
    }

    public JTextField getSchoolTextField() {
        return schoolTextField;
    }

    public void setSchoolTextField(JTextField schoolTextField) {
        this.schoolTextField = schoolTextField;
    }

    public JTextArea getOtherTextField() {
        return otherTextField;
    }

    public void setOtherTextField(JTextArea otherTextField) {
        this.otherTextField = otherTextField;
    }

    public JPanel getBirthPanel() {
        return birthPanel;
    }

    public void setBirthPanel(JPanel birthPanel) {
        this.birthPanel = birthPanel;
    }

    public MyScreenPanel getScreenPanel() {
        return screenPanel;
    }

    public void setScreenPanel(MyScreenPanel screenPanel) {
        this.screenPanel = screenPanel;
    }
    public JSplitPane getMAIN_PANEL() {
        return MAIN_PANEL;
    }

    public void setMAIN_PANEL(JSplitPane MAIN_PANEL) {
        this.MAIN_PANEL = MAIN_PANEL;
    }

}
