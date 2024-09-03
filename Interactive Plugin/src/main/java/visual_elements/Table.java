package visual_elements;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class Table {

    JTable jt;

    public Table(String data[][]){

        String column[]=data[0];
        jt = new JTable(data,column)
        {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                //  Alternate row color
                c.setBackground(row % 2 != 0 ? Color.decode("#D3D3D3") : Color.decode("#EFF1F3"));
                c.setFont(row != 0 ? getFont() : c.getFont().deriveFont(Font.BOLD, 11f));
                return c;

            }
        };
        jt.setBackground(Color.decode("#FFFFFF"));
        jt.setForeground(Color.decode("#220F67"));
        jt.setGridColor(Color.decode("#D6D6D6"));
        jt.setAlignmentX(Component.LEFT_ALIGNMENT);

        for ( int i = 0; i < jt.getColumnCount(); i++) {
            jt.getColumnModel().getColumn(i).setCellRenderer(new TableFullContentCellRenderer());
        }
    }

    public JTable getTable(){
        return jt;
    }
}
