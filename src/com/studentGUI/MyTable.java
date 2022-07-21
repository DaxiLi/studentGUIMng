package com.studentGUI;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MyTable {
    JScrollPane MAIN_PANEL;
    JTable table;
    DefaultTableModel model;
    final String[] tableHeader = {"编号", "姓名", "性别", "年龄", "学号", "学校", "班级", "备注", "操作"};

    private StudentList STUDENTS;
    private Screen screen;
    private MyTopPanel topPanel;
    private MyTable    myTable;
    private MyScreenPanel screenPanel;

    MyTable(){
        MAIN_PANEL = new JScrollPane();
        model = new DefaultTableModel();
        table = new JTable(model);
        for (String val : tableHeader) {
            model.addColumn(val);
        }
    }

    public void set(MyScreenPanel screenPanel,MyTable myTable,MyTopPanel myTopPanel,StudentList list){
        this.screenPanel = screenPanel;
        this.myTable     = myTable;
        this.topPanel    = myTopPanel;
        this.STUDENTS    = list;
        this.screen      = screenPanel.getScreen();
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getPanel(){
        MAIN_PANEL = new JScrollPane();
        refresh();
        return MAIN_PANEL;
    }


    public void refresh(){

        model = new DefaultTableModel();
        table = new JTable(model);
        for (String val : tableHeader) {
            model.addColumn(val);
        }
        MAIN_PANEL.getViewport().add(getTable());
        for (int i = 0 ;i < table.getRowCount(); i++){
            System.out.println("正在移除 NO:" + i + "总行：" + model.getRowCount());
        }
        System.out.println("当前list:" + STUDENTS.getSTUDENTS().toString());
        List<Student> val = screenPanel.getScreen().screenStudents(STUDENTS.getSTUDENTS());
        int i = 0;
        for (Student student : val) {
            System.out.println("正在添加第"+ (i++) + "个学生：" + student.toString());
            model.addRow(new Object[]{
                    i + " /" +  val.size(),
                    student.getName(),
                    student.getGender(),
                    student.getAge(),
                    student.getStudentID(),
                    student.getSchool(),
                    student.getStudentClass(),
                    student.getOthers()
            });
            //实例化 删除按钮
            MyButton button0 = new MyButton("删除", student.getStudentID());
            MyButton button1 = new MyButton("删除", student.getStudentID());
            button0.getButton().addActionListener(new myActionListener(student.getStudentID()));
            button1.getButton().addActionListener(new myActionListener(student.getStudentID()));
            //设置删除按钮
            table.getColumnModel().getColumn(8).setCellEditor(button0);//设置编辑器
            table.getColumnModel().getColumn(8).setCellRenderer(button1);
        }
        //为表添加排序
        RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(rowSorter);

        //添加选择监听
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
        selectionModel.addListSelectionListener(new myListSelectionListener());

        //添加 编辑监听
        MyCellEditor cellEditor = new MyCellEditor(new JTextField());
        for (int ii = 1; ii < 7; ii++) {
            // 根据 列名 获取 表格列
           // TableColumn tableColumn = table.getColumn(tableHeader[ii]);
            // 设置 表格列 的 单元格编辑器
            //tableColumn.setCellEditor(cellEditor);
        }
    }
    class MyCellEditor extends DefaultCellEditor {

        public MyCellEditor(JTextField textField) {
            super(textField);
        }

        public MyCellEditor(JCheckBox checkBox) {
            super(checkBox);
        }

        public MyCellEditor(JComboBox comboBox) {
            super(comboBox);
        }

        @Override
        public boolean stopCellEditing() {
            Component comp = getComponent();
            // 获取当前单元格编辑器输入的
            Object obj = getCellEditorValue();
            if (obj.toString().length() == 0) {
                JOptionPane.showMessageDialog(null, "不能为空", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            System.out.println(table.getColumnName(table.getSelectedColumn()));
            return super.stopCellEditing();
        }

    }


    class myListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {

            System.out.println("选中！行： "+ table.getSelectedRow());
            System.out.println("选中！列： "+table.getSelectedColumn());
            int row =  table.getSelectedRow();
            String columnName = table.getColumnName(table.getSelectedColumn());
            System.out.println(columnName);
           // table.setBackground(Color.red);
            String id =  "aja168520_key";
            for (int j = 0;j < 7;j++){
                switch (table.getColumnName(j)){
                    case "编号":System.out.println("zhis is bianhao");
                    break;
                    case "学号":
                        id = (String)table.getValueAt(row,j);
                        topPanel.setStudent(STUDENTS.getById((String)table.getValueAt(row,j)));
                        topPanel.setINSERT_VALUE("保存");
                        topPanel.setCLEAN_VALUE("删除");
                        System.out.println("当前点击学生是：" + STUDENTS.getById((String)table.getValueAt(row,j)).toString());
                        topPanel.refresh();
                        return;
                    default:
                        System.out.println(table.getColumnName(table.getSelectedColumn()));
                 }
            }
            if (columnName.equals("操作")){
                System.out.println("开始操作");
                int flag = JOptionPane.showConfirmDialog(null, "确定删除该条记录？", "警告", JOptionPane.YES_NO_OPTION);
                if (flag == JOptionPane.YES_OPTION) {
                    STUDENTS.deleteById(id);
                    topPanel.setStudent(new Student());
                    JOptionPane.showMessageDialog(null, "删除成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                    topPanel.refresh();
                }
                return;
            }

            // 获取选中的第一行
            int selectedRow = table.getSelectedRow();

            // 获取选中的第一列
            int selectedRow0 = table.getSelectedColumn();

            // 获取选中的所有行
            int[] selectedRows = table.getSelectedRows();

            // 获取选中的所有列
            int[] selectedColumns = table.getSelectedColumns();
        }
    }

    class myActionListener implements ActionListener {
        String id ;
        myActionListener(String id){
            this.id = id;
        }
        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int flag = JOptionPane.showConfirmDialog(null, "确定删除该条记录？", "警告", JOptionPane.YES_NO_OPTION);
            if (flag == JOptionPane.YES_OPTION) {
                STUDENTS.deleteById(id);
                JOptionPane.showMessageDialog(null, "删除成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
                refresh();
            }
            return;
        }
    }

    public JTable getTable(){
        return table;
    }
}


