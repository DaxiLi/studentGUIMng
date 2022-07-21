package com.studentGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class MyScreenPanel {
    JPanel  MAIN_PANEL;
    // 全局对象 交换信息用
    private StudentList STUDENTLIST;
    private Screen screen; //筛选器
    private MyTopPanel topPanel;
    private MyTable     TABLE;
    private MyScreenPanel screenPanel;
    
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
    JPanel          leftDatePanel;
    JLabel          startDateLabel;
    JLabel          leftYearLabel;
    JLabel          leftMonthLabel;
    JLabel          leftDayLabel;
    JComboBox       leftYearBox;    //创建JComboBox
    JComboBox       leftMnthBox;    //创建JComboBox
    JComboBox       leftDayBox;    //创建JComboBox
    JPanel          rightDatePanel;
    JLabel          endDateLabel;
    JLabel          rightYearLabel;
    JLabel          rightMonthLabel;
    JLabel          rightDayLabel;
    JComboBox       rightYearBox;    //创建JComboBox
    JComboBox       rightMnthBox;    //创建JComboBox
    JComboBox       rightDayBox;    //创建JComboBox

    public MyScreenPanel() {
        MAIN_PANEL = new JPanel();
        screen      = new Screen(); //新建筛选器
        
        nameLabel = new JLabel("姓名:");
        genderLabel = new JLabel("性别:");
        idLabel = new JLabel("学号：");
        classLabel = new JLabel("班级：");
        schoolLabel = new JLabel("学校：");
        nameTextField = new JTextField(8);
        idTextField = new JTextField(10);
        classTextField = new JTextField(10);
        schoolTextField = new JTextField(15);
        genderBox = new JComboBox();    //创建JComboBox

        nameTextField.setText(screen.getName());
        idTextField.setText(screen.getStudentID());
        classTextField.setText(screen.getStudentClass());
        schoolTextField.setText(screen.getSchool());

        leftDatePanel = new JPanel();
       // leftDatePanel.setLayout(bagLayout);
        startDateLabel = new JLabel("生日从:");
        leftYearLabel = new JLabel("年");
        leftMonthLabel = new JLabel("月");
        leftDayLabel = new JLabel("");
        leftYearBox = new JComboBox();    //创建JComboBox
        leftMnthBox = new JComboBox();    //创建JComboBox
        leftDayBox = new JComboBox();    //创建JComboBox

        rightDatePanel = new JPanel();
        //rightDatePanel.setLayout(bagLayout);
        endDateLabel = new JLabel("       到:");
        rightYearLabel = new JLabel("年");
        rightMonthLabel = new JLabel("月");
        rightDayLabel = new JLabel("");
        rightYearBox = new JComboBox();    //创建JComboBox
        rightMnthBox = new JComboBox();    //创建JComboBox
        rightDayBox = new JComboBox();    //创建JComboBox
    }

    public void set(MyScreenPanel screenPanel, MyTable myTable, MyTopPanel myTopPanel, StudentList list){
        this.screenPanel = screenPanel;
        this.topPanel    = myTopPanel;
        this.STUDENTLIST = list;
        this.screenPanel = screenPanel;
        this.TABLE       = myTable;
    }

    public void refresh(){
        topPanel.refresh();
        /**
         * TODO LiSt  添加 bottomPAnel refresh
         * 因为过滤器刷新，总伴随表格刷新
         *
         */
    }

    public JPanel getScreenPanel(){
        MAIN_PANEL = new JPanel();
        setPanel(MAIN_PANEL);
        return MAIN_PANEL;
    }

    public JPanel setPanel(JPanel p) {
        GridBagLayout bagLayout = new GridBagLayout();    //创建GridBagLayout布局管理器
        GridBagConstraints constraints = new GridBagConstraints();
        p.setLayout(bagLayout);    //使用GridBagLayout布局管理器
        constraints.fill = GridBagConstraints.BOTH;    //组件填充显示区域
        constraints.weightx = 0.0;    //

        //姓名
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 0;
        JPanel tempPanel = new JPanel();
        tempPanel.add(nameLabel);
        tempPanel.add(nameTextField);
        p.add(tempPanel, constraints);

        //性别
        constraints.gridy = 1;
        genderBox.removeAllItems();
        genderBox.addItem(Gender.MAN);
        genderBox.addItem(Gender.FEMALE);
        genderBox.addItem(Gender.OTHER);
        genderBox.addItem("");
        System.out.println("筛选器性别："  + screen.getGender());
        constraints.gridx = 0;
        constraints.gridwidth = 0;
        tempPanel = new JPanel();
        tempPanel.add(genderLabel);
        tempPanel.add(genderBox);
        p.add(tempPanel, constraints);

        //id
        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 0;
        tempPanel = new JPanel();
        tempPanel.add(idLabel);
        tempPanel.add(idTextField);
        p.add(tempPanel, constraints);

        //学校
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.gridwidth = 0;
        tempPanel = new JPanel();
        tempPanel.add(schoolLabel);
        tempPanel.add(schoolTextField);
        p.add(tempPanel, constraints);

        // 班级

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 0;
        tempPanel = new JPanel();
        tempPanel.add(classLabel);
        tempPanel.add(classTextField);
        p.add(tempPanel, constraints);


        //leftDate
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        leftDatePanel.add(startDateLabel, constraints);
        leftYearBox.removeAllItems();
        for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= MyDate.MIN_YEAR; i--) {
            leftYearBox.addItem(i);
        }
        leftYearBox.setSelectedItem(screen.getLeftDate().getY());
        constraints.gridx = 5;
        constraints.gridwidth = 4;
        leftDatePanel.add(leftYearBox, constraints);
        constraints.gridx = 10;
        constraints.gridwidth = 4;
        leftDatePanel.add(leftYearLabel, constraints);

        leftMnthBox.removeAllItems();
        for (int i = 1; i < 13; i++) {
            leftMnthBox.addItem(i);
        }
        leftMnthBox.setSelectedItem(screen.getLeftDate().getM());
        constraints.gridx = 15;
        constraints.gridwidth = 2;
        leftDatePanel.add(leftMnthBox, constraints);
        constraints.gridx = 18;
        constraints.gridwidth = 4;
        leftDatePanel.add(leftMonthLabel, constraints);

        for (int i = 1; i < 32; i++) {
            leftDayBox.addItem(i);
        }
        leftDayBox.setSelectedItem(screen.getLeftDate().getD());
        constraints.gridx = 23;
        constraints.gridwidth = 2;
        leftDatePanel.add(leftDayBox, constraints);
        constraints.gridx = 26;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        leftDatePanel.add(leftDayLabel, constraints);

        //rightDate
        rightYearBox.removeAllItems();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        rightDatePanel.add(endDateLabel, constraints);
        for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= MyDate.MIN_YEAR; i--) {
            rightYearBox.addItem(i);
        }
        rightYearBox.setSelectedItem(screen.getRightDate().getY());
        constraints.gridx = 5;
        constraints.gridwidth = 4;
        rightDatePanel.add(rightYearBox, constraints);
        constraints.gridx = 10;
        constraints.gridwidth = 4;
        rightDatePanel.add(rightYearLabel, constraints);

        rightMnthBox.removeAllItems();
        for (int i = 1; i < 13; i++) {
            rightMnthBox.addItem(i);
        }
        rightMnthBox.setSelectedItem(screen.getRightDate().getM());
        constraints.gridx = 15;
        constraints.gridwidth = 2;
        rightDatePanel.add(rightMnthBox, constraints);
        constraints.gridx = 18;
        constraints.gridwidth = 4;
        rightDatePanel.add(rightMonthLabel, constraints);

        for (int i = 1; i < 32; i++) {
            rightDayBox.addItem(i);
        }
        rightDayBox.setSelectedItem(screen.getRightDate().getD());
        constraints.gridx = 23;
        constraints.gridwidth = 2;
        rightDatePanel.add(rightDayBox, constraints);
        constraints.gridx = 26;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        rightDatePanel.add(rightDayLabel, constraints);

        //end

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 29;
        p.add(leftDatePanel, constraints);

        constraints.gridy = 7;
        constraints.gridwidth = 29;
        p.add(rightDatePanel, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 9;
        p.add(new JPanel(), constraints);

        JButton buttonS = new JButton("筛选");
        buttonS.setBackground(Color.white);

        JButton buttonB = new JButton("清空筛选条件");
        buttonB.setBackground(Color.white);


        JPanel buPanel = new JPanel();
        buPanel.add(buttonS);
        buPanel.add(buttonB);

        constraints.gridy = 12;
        p.add(buPanel, constraints);

        //帅选七初始化
        nameTextField.setText(screen.getName());
        genderBox.setSelectedItem(screen.getGender());
        idTextField.setText(screen.getStudentID());
        classTextField.setText(screen.getStudentClass());
        schoolTextField.setText(screen.getSchool());
        leftYearBox.setSelectedItem(screen.getLeftDate().getY());
        leftMnthBox.setSelectedItem(screen.getLeftDate().getM());
        leftDayBox.setSelectedItem(screen.getLeftDate().getD());

        rightYearBox.setSelectedItem(screen.getRightDate().getY());
        rightMnthBox.setSelectedItem(screen.getRightDate().getM());
        rightDayBox.setSelectedItem(screen.getRightDate().getD());
        System.out.println("筛选刷新成功！");


        leftYearBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( leftYearBox.getSelectedItem() == null ||
                        leftMnthBox.getSelectedItem() == null){
                    return;
                }
                int y = (int) leftYearBox.getSelectedItem();
                int m = (int) leftMnthBox.getSelectedItem();
                Calendar calendar = Calendar.getInstance();
                calendar.set(y - 1900, m - 1, 1);
                calendar.roll(Calendar.DATE, -1);
                int max = calendar.get(Calendar.DATE);
                leftDayBox.removeAllItems();
                for (int i = 1; i <= max; i++) {
                    leftDayBox.addItem(i);
                }
            }
        });

        leftMnthBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( leftYearBox.getSelectedItem() == null ||
                        leftMnthBox.getSelectedItem() == null){
                    return;
                }
                int m = (int) leftMnthBox.getSelectedItem();
                int y = (int) leftYearBox.getSelectedItem();
                Calendar calendar = Calendar.getInstance();
                calendar.set(y - 1900, m - 1, 1);
                calendar.roll(Calendar.DATE, -1);
                int max = calendar.get(Calendar.DATE);
                leftDayBox.removeAllItems();
                for (int i = 1; i <= max; i++) {
                    leftDayBox.addItem(i);
                }
            }
        });

        rightYearBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( rightYearBox.getSelectedItem() == null ||
                        rightMnthBox.getSelectedItem() == null){
                    return;
                }
                int y = (int) rightYearBox.getSelectedItem();
                int m = (int) rightMnthBox.getSelectedItem();
                Calendar calendar = Calendar.getInstance();
                calendar.set(y - 1900, m - 1, 1);
                calendar.roll(Calendar.DATE, -1);
                int max = calendar.get(Calendar.DATE);
                rightDayBox.removeAllItems();
                for (int i = 1; i <= max; i++) {
                    rightDayBox.addItem(i);
                }
            }
        });

        rightMnthBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( rightYearBox.getSelectedItem() == null ||
                        rightMnthBox.getSelectedItem() == null){
                    return;
                }
                int m = (int) rightMnthBox.getSelectedItem();
                int y = (int) rightYearBox.getSelectedItem();
                Calendar calendar = Calendar.getInstance();
                calendar.set(y - 1900, m - 1, 1);
                calendar.roll(Calendar.DATE, -1);
                int max = calendar.get(Calendar.DATE);
                rightDayBox.removeAllItems();
                for (int i = 1; i <= max; i++) {
                    rightDayBox.addItem(i);
                }
            }
        });



        /**
         * 这是 过滤按钮
         */
        buttonS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("SCREEN :date start:" + screen.getLeftDate().toString());
                System.out.print("SCREEN :date end:" + screen.getRightDate().toString());
                screen.setName(nameTextField.getText());
                screen.setLeftDate(new MyDate((int) leftYearBox.getSelectedItem(), (int) leftMnthBox.getSelectedItem(), (int) leftDayBox.getSelectedItem()));
                screen.setRightDate(new MyDate((int) rightYearBox.getSelectedItem(), (int) rightMnthBox.getSelectedItem(), (int) rightDayBox.getSelectedItem()));
                screen.setStudentID(idTextField.getText());
                screen.setGender((String) genderBox.getSelectedItem());
                screen.setSchool(schoolTextField.getText());
                screen.setStudentClass(classTextField.getText());
                if (!dateIsRight()) {
                    JOptionPane.showMessageDialog(null, "开始日期不能大于结束日期！", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("\n筛选器设置为：" + idTextField.getText()+ screen.toString());
                TABLE.refresh();
            }

            private Boolean dateIsRight() {
                int flag = screen.getLeftDate().myEquals(screen.getRightDate());
                return flag > 0 ? false : true;
            }

        });
        //清空过滤
        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen = new Screen();
                System.out.println("\n新建筛选器：" + screen.toString());
                refresh();
                TABLE.refresh();
            }
        });
        return p;
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

    public JLabel getStartDateLabel() {
        return startDateLabel;
    }

    public void setStartDateLabel(JLabel startDateLabel) {
        this.startDateLabel = startDateLabel;
    }

    public JLabel getLeftYearLabel() {
        return leftYearLabel;
    }

    public void setLeftYearLabel(JLabel leftYearLabel) {
        this.leftYearLabel = leftYearLabel;
    }

    public JLabel getLeftMonthLabel() {
        return leftMonthLabel;
    }

    public void setLeftMonthLabel(JLabel leftMonthLabel) {
        this.leftMonthLabel = leftMonthLabel;
    }

    public JLabel getLeftDayLabel() {
        return leftDayLabel;
    }

    public void setLeftDayLabel(JLabel leftDayLabel) {
        this.leftDayLabel = leftDayLabel;
    }

    public JComboBox getLeftYearBox() {
        return leftYearBox;
    }

    public void setLeftYearBox(JComboBox leftYearBox) {
        this.leftYearBox = leftYearBox;
    }

    public JComboBox getLeftMnthBox() {
        return leftMnthBox;
    }

    public void setLeftMnthBox(JComboBox leftMnthBox) {
        this.leftMnthBox = leftMnthBox;
    }

    public JComboBox getLeftDayBox() {
        return leftDayBox;
    }

    public void setLeftDayBox(JComboBox leftDayBox) {
        this.leftDayBox = leftDayBox;
    }

    public JPanel getRightDatePanel() {
        return rightDatePanel;
    }

    public void setRightDatePanel(JPanel rightDatePanel) {
        this.rightDatePanel = rightDatePanel;
    }

    public JLabel getEndDateLabel() {
        return endDateLabel;
    }

    public void setEndDateLabel(JLabel endDateLabel) {
        this.endDateLabel = endDateLabel;
    }

    public JLabel getRightYearLabel() {
        return rightYearLabel;
    }

    public void setRightYearLabel(JLabel rightYearLabel) {
        this.rightYearLabel = rightYearLabel;
    }

    public JLabel getRightMonthLabel() {
        return rightMonthLabel;
    }

    public void setRightMonthLabel(JLabel rightMonthLabel) {
        this.rightMonthLabel = rightMonthLabel;
    }

    public JLabel getRightDayLabel() {
        return rightDayLabel;
    }

    public void setRightDayLabel(JLabel rightDayLabel) {
        this.rightDayLabel = rightDayLabel;
    }

    public JComboBox getRightYearBox() {
        return rightYearBox;
    }

    public void setRightYearBox(JComboBox rightYearBox) {
        this.rightYearBox = rightYearBox;
    }

    public JComboBox getRightMnthBox() {
        return rightMnthBox;
    }

    public void setRightMnthBox(JComboBox rightMnthBox) {
        this.rightMnthBox = rightMnthBox;
    }

    public JComboBox getRightDayBox() {
        return rightDayBox;
    }

    public void setRightDayBox(JComboBox rightDayBox) {
        this.rightDayBox = rightDayBox;
    }

    public JPanel getMAIN_PANEL() {
        return MAIN_PANEL;
    }

    public void setMAIN_PANEL(JPanel MAIN_PANEL) {
        this.MAIN_PANEL = MAIN_PANEL;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public JPanel getLeftDatePanel() {
        return leftDatePanel;
    }

    public void setLeftDatePanel(JPanel leftDatePanel) {
        this.leftDatePanel = leftDatePanel;
    }

}
