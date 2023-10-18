package visual_elements;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableComparation {

    JTable jt;

    public TableComparation(String data[][]){

        String column[]=data[0];
        jt = new JTable(data,column)
        {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                //  Alternate row color
                c.setBackground(row % 2 != 0 ? getBackground() : Color.decode("#EFF1F3"));
                c.setFont(row != 0 ? getFont() : c.getFont().deriveFont(Font.BOLD, 13f));
                return c;

            }
        };
        jt.setBackground(Color.decode("#FFFFFF"));
        jt.setForeground(Color.decode("#220F67"));
        jt.setGridColor(Color.decode("#D6D6D6"));


        jt.setRowHeight(0, 25);

        for (int i = 0; i < jt.getColumnCount(); i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(new TableFullContentCellRenderer(){
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    return c;
                }
            });
            jt.setRowHeight(i, 20);
        }
    }

    public JTable getTable(){
        return jt;
    }
}
