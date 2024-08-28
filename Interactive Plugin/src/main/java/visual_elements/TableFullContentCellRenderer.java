package visual_elements;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableFullContentCellRenderer extends JTextArea implements TableCellRenderer {
    public TableFullContentCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((String) value);
        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
        setBackground(Color.decode("#FFFFFF"));
        setForeground(Color.decode("#220F67"));
        if (table.getRowHeight(row) < getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }
        if (row == 0){
            setFont(getFont().deriveFont(Font.BOLD));
        }
        if (row % 2 == 0) {
            setBackground(Color.decode("#D3D3D3"));
        }
        return this;
    }
}
