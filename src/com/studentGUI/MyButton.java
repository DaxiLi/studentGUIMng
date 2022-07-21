package com.studentGUI;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButton extends AbstractCellEditor implements TableCellRenderer, ActionListener, TableCellEditor {
    private static final long serialVersionUID = 1L;
    private JButton button = null;
    private String id = "";

    public MyButton() {
        button = new JButton();
        button.setBackground(Color.red);
    }
    public MyButton(String s,String id) {
        button = new JButton(s);
        this.id = id;
        button.setBackground(Color.red);
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public JButton getButton() {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
    // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
    // TODO Auto-generated method stub
        return button;
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//// TODO Auto-generated method stub
//        JOptionPane.showMessageDialog(null, "渲染器学期1653", "消息", JOptionPane.OK_OPTION);
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
        int flag = JOptionPane.showConfirmDialog(null, "确定删除该条记录？", "警告", JOptionPane.YES_NO_OPTION);
        if (flag == JOptionPane.YES_OPTION){


        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
// TODO Auto-generated method stub
        return button;
    }
}