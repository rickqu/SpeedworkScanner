package com.rfid.ui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class CommTable {

	public static void setTableStyleZL(JTable table) {
		CommTableHeadUI ui = new CommTableHeadUI();
		table.getTableHeader().setUI(ui);
		table.getTableHeader().setPreferredSize(new Dimension(450, 30));
		table.setRowHeight(25);
		table.setEnabled(false);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.setPreferredScrollableViewportSize(new Dimension(350, 190));
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(50);
		firsetColumn.setMaxWidth(50);
		firsetColumn.setMinWidth(50);
		TableColumn secondColumn = table.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(140);
		secondColumn.setMaxWidth(140);
		secondColumn.setMinWidth(140);
	}
	
	public static void setTableStyle(JTable table) {
		//BasicTableHeadUI ui = new BasicTableHeadUI();
		//tbl_showTagInfo.getTableHeader().setUI(ui);

		table.getTableHeader().setPreferredSize(new Dimension(450, 30));
		table.setRowHeight(30);
		//table.setEnabled(false);
		table.setAutoscrolls(false);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.setPreferredScrollableViewportSize(new Dimension(400, 260));
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(50);
		firsetColumn.setMaxWidth(50);
		firsetColumn.setMinWidth(50);

		TableColumn secondColumn = table.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(100);
		secondColumn.setMinWidth(100);
		
		int selectionMode = ListSelectionModel.SINGLE_SELECTION;
		table.getSelectionModel().setSelectionMode(selectionMode);
		
		table .getTableHeader().setReorderingAllowed(false);
	}
}
