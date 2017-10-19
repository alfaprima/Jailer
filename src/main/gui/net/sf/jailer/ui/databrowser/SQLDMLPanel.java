/*
 * Copyright 2007 - 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.jailer.ui.databrowser;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import net.sf.jailer.ExecutionContext;
import net.sf.jailer.database.Session;
import net.sf.jailer.ui.UIUtil;
import net.sf.jailer.ui.databrowser.metadata.MetaDataSource;
import net.sf.jailer.ui.databrowser.sqlconsole.MetaDataBasedSQLCompletionProvider;
import net.sf.jailer.ui.databrowser.sqlconsole.SQLConsole;
import net.sf.jailer.ui.syntaxtextarea.RSyntaxTextAreaWithSQLSyntaxStyle;
import net.sf.jailer.util.SqlScriptExecutor;

/**
 * Editor for SQL/DML statements.
 * 
 * @author Ralf Wisser
 */
public class SQLDMLPanel extends javax.swing.JPanel {
	
	private static final long serialVersionUID = 1747749941444843829L;

	/**
	 * The DB session.
	 */
	private final Session session;
	
	/**
	 * To be done after execution of the script.
	 */
	private final Runnable afterExecution;
	private final Runnable switchToConsole;
	
	private final ExecutionContext executionContext;
	private final SQLConsole sqlConsole;
	private final JDialog dialog;
	
	/** Creates new form SQLPanel 
	 * @param sql 
	 * @param metaDataSource 
	 * @param dialog */
	public SQLDMLPanel(String sql, SQLConsole sqlConsole, Session session, MetaDataSource metaDataSource, Runnable afterExecution, Runnable switchToConsole, JDialog dialog, ExecutionContext executionContext) {
		this.executionContext = executionContext;
		this.sqlConsole = sqlConsole;
		this.switchToConsole = switchToConsole;
		this.session = session;
		this.afterExecution = afterExecution;
		this.dialog = dialog;
		initComponents();
		
		this.sqlTextArea = new RSyntaxTextAreaWithSQLSyntaxStyle(false);
		try {
			MetaDataBasedSQLCompletionProvider provider = new MetaDataBasedSQLCompletionProvider(session, metaDataSource);
			AutoCompletion ac = new AutoCompletion(provider);
			ac.install(sqlTextArea);
		} catch (SQLException e) {
		}
		
		RTextScrollPane jScrollPane1 = new RTextScrollPane();
		jScrollPane1.setViewportView(sqlTextArea);
		
		GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		jPanel1.add(jScrollPane1, gridBagConstraints);
		
		jScrollPane1.setLineNumbersEnabled(true);

		statusLabel.setText("");
		sqlTextArea.setText(sql);
		sqlTextArea.select(0, 0);
		
		mlmTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				appendMLM(mlmTextField.getText());
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				appendMLM(mlmTextField.getText());
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				appendMLM(mlmTextField.getText());
			}
		});
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				sqlConsoleButton.grabFocus();
			}
		});
	}

	private String mlm = "";
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        clipboardSingleLineButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        clipboardButton = new javax.swing.JButton();
        executeButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mlmTextField = new javax.swing.JTextField();
        singleLineCheckBox = new javax.swing.JCheckBox();
        sqlConsoleButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        clipboardSingleLineButton.setText(" Copy as Single Line ");
        clipboardSingleLineButton.setToolTipText(" Copy the SQL statement as a single line to the clipboard");
        clipboardSingleLineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipboardSingleLineButtonActionPerformed(evt);
            }
        });

        setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        saveButton.setText(" Save ");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(saveButton, gridBagConstraints);

        clipboardButton.setText(" Copy to Clipboard ");
        clipboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipboardButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(clipboardButton, gridBagConstraints);

        executeButton.setText(" Execute ");
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(executeButton, gridBagConstraints);

        statusLabel.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanel2.add(statusLabel, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("multi-line continuation  ");
        jLabel1.setToolTipText("multi-line continuation character");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 16;
        jPanel4.add(mlmTextField, gridBagConstraints);

        singleLineCheckBox.setText("single line  ");
        singleLineCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                singleLineCheckBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel4.add(singleLineCheckBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel2.add(jPanel4, gridBagConstraints);

        sqlConsoleButton.setText("SQL Console");
        sqlConsoleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sqlConsoleButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(sqlConsoleButton, gridBagConstraints);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel2.add(closeButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
		String fn = UIUtil.choseFile(null, ".", "Save SQL Query", "", this, false, false);
		if (fn != null) {
			try {
				PrintWriter out = new PrintWriter(new FileWriter(fn));
				out.print(sqlTextArea.getText());
				out.close();
			} catch (Exception e) {
				UIUtil.showException(this, "Error saving query", e, session);
			}
		}
}//GEN-LAST:event_saveButtonActionPerformed

	private void clipboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipboardButtonActionPerformed
		sqlTextArea.selectAll();
		sqlTextArea.copy();
		sqlTextArea.select(0, 0);
}//GEN-LAST:event_clipboardButtonActionPerformed

	private void clipboardSingleLineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipboardSingleLineButtonActionPerformed
		String orig = sqlTextArea.getText();
		sqlTextArea.setText(orig.replaceAll(" *(\n|\r)+ *", " "));
		sqlTextArea.selectAll();
		sqlTextArea.copy();
		sqlTextArea.setText(orig);
		sqlTextArea.select(0, 0);
	}//GEN-LAST:event_clipboardSingleLineButtonActionPerformed

	private String lastMultiLineSQL = "";
	
	private void singleLineCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_singleLineCheckBoxItemStateChanged
		String mlm = this.mlm;
		if (singleLineCheckBox.isSelected()) {
			String lf = System.getProperty("line.separator", "\n");
			appendMLM("");
			lastMultiLineSQL = sqlTextArea.getText();
			String EOL = "EOL498503458430EOL";
			sqlTextArea.setText(lastMultiLineSQL.replaceAll(";(\n|\r)", EOL).replaceAll(" *(\n|\r)+ *", " ").replaceAll(EOL + " *", ";" + lf));
			appendMLM(mlm);
		} else {
			sqlTextArea.setText(lastMultiLineSQL);
			this.mlm = "";
			appendMLM(mlm);
		}
		sqlTextArea.setCaretPosition(0);
	}//GEN-LAST:event_singleLineCheckBoxItemStateChanged

	private void appendMLM(String mlm) {
		mlm = mlm.trim();
		if (mlm.length() > 1) {
			mlm = mlm.substring(0, 1);
		}
		if (this.mlm.equals(mlm)) {
			return;
		}
		if (this.mlm.length() > 0) {
			String omlm = this.mlm;
			if ("\\|[]()^-$".indexOf(omlm) >= 0) {
				omlm = "\\" + omlm;
			}
			sqlTextArea.setText(sqlTextArea.getText().replaceAll(" " + omlm + "([\n\r])", "$1"));
			sqlTextArea.select(0, 0);
		}
		this.mlm = mlm;
		if (mlm.length() > 0) {
			if ("\\".equals(mlm) || "$".equals(mlm)) {
				mlm = "\\" + mlm;
			}
			sqlTextArea.setText(sqlTextArea.getText().replaceAll("([^\n\r;])([\n\r])", "$1 " + mlm + "$2"));
			sqlTextArea.select(0, 0);
		}
	}
	
	private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "Execute Statements?", "Execute", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
			String sqlFile;
			try {
				String sqlFileBase = "temp_" + System.currentTimeMillis();
				for (int i = 0; ; ++i) {
					sqlFile = sqlFileBase + i + ".sql";
					if (!new File(sqlFile).exists()) {
						break;
					}
				}
				PrintWriter out = new PrintWriter(new File(sqlFile));
				out.println(sqlTextArea.getText());
				out.close();
			} catch (Exception e) {
				UIUtil.showException(this, "Error", e, session);
				return;
			}
			List<String> args = new ArrayList<String>();
			args.add("import");
			args.add(sqlFile);
			args.addAll(session.getCliArguments());
			args.add("-transactional");
			if (UIUtil.runJailer(SwingUtilities.getWindowAncestor(this), args, false, true, false, true, null, session.getSchema(), session.getPassword(), null, null, false, false, true, executionContext)) {
				statusLabel.setText("Executed " + SqlScriptExecutor.getLastStatementCount().a + " statements. " +
						SqlScriptExecutor.getLastStatementCount().b + " rows affected");
				statusLabel.setForeground(new Color(0, 100, 0));
				afterExecution.run();
				// JOptionPane.showMessageDialog(this, "Successfully executed " + SqlScriptExecutor.getLastStatementCount().a + " statements.\n" + SqlScriptExecutor.getLastStatementCount().b + " rows affected.", "SQL/DML", JOptionPane.INFORMATION_MESSAGE);
		 
			} else {
				statusLabel.setText("Error, rolled back");
				statusLabel.setForeground(new Color(115, 0, 0));
			}
			new File(sqlFile).delete();
		}
	}//GEN-LAST:event_executeButtonActionPerformed

    private void sqlConsoleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sqlConsoleButtonActionPerformed
        switchToConsole.run();
    	sqlConsole.appendStatement(sqlTextArea.getText().trim().replaceAll("\\s*\\n", "\n"), false);
        dialog.dispose();
    }//GEN-LAST:event_sqlConsoleButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
    	dialog.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clipboardButton;
    private javax.swing.JButton clipboardSingleLineButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton executeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField mlmTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JCheckBox singleLineCheckBox;
    private javax.swing.JButton sqlConsoleButton;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

    private final RSyntaxTextArea sqlTextArea;
}
