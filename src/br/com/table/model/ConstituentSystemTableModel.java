package br.com.table.model;



import br.com.object.model.ConstituentSystem;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ConstituentSystemTableModel extends AbstractTableModel {

    private final List<ConstituentSystem> lineCS;
    private final int colId = 0, colName = 1, colServerAddress = 2, colAuthentication = 3, colActive = 4;

    public ConstituentSystemTableModel() {
        this.lineCS = new ArrayList();
    }

    @Override
    public int getRowCount() {
        return lineCS.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case colId:
                return "id";
            case colName:
                return "Name";
            case colServerAddress:
                return "Server Address";
            case colAuthentication:
                return "Authentication";
            case colActive:
                return "Active";
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ConstituentSystem lineConstituent = lineCS.get(rowIndex);

        switch (columnIndex) {
            case colId:
                return lineConstituent.getId();
            case colName:
                return lineConstituent.getName();
            case colServerAddress:
                return lineConstituent.getServerAddress();
            case colAuthentication:
                return lineConstituent.getAuthentication();
            case colActive:
                return lineConstituent.isActive();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    public ConstituentSystem getCS(int rowIndex) {
        return lineCS.get(rowIndex);
    }

    public void addListCS(List<ConstituentSystem> listCS) {
        int intSizeActual = getRowCount();
        lineCS.addAll(listCS);
        fireTableRowsInserted(intSizeActual, getRowCount() - 1);
    }

    public void removeCS(int rowIndex) {
        lineCS.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void removeAll() {
        lineCS.clear();
        fireTableDataChanged();
    }

}
