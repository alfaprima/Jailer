/*
 * Copyright 2007 - 2016 the original author or authors.
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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import net.sf.jailer.datamodel.Association;
import net.sf.jailer.ui.UIUtil;

/**
 * Query Builder Path Selector Dialog.
 * 
 * @deprecated this dialog is no longer used
 * @author Ralf Wisser
 */
public class QueryBuilderPathSelector extends javax.swing.JDialog {

    private static final long serialVersionUID = 3231933816703766740L;

	/** Creates new form QueryBuilderPathSelector */
    public QueryBuilderPathSelector(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				okButton.grabFocus();
			}
		});
        pack();
		setSize(Math.max(400, getWidth()), getHeight());
		UIUtil.initPeer();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        toComboBox = new net.sf.jailer.ui.JComboBox();
        fromLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Query Builder");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText(" join tables from   ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText(" to ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jLabel2, gridBagConstraints);

        toComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        toComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                toComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(toComboBox, gridBagConstraints);

        fromLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        fromLabel.setText("TABLE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(fromLabel, gridBagConstraints);

        okButton.setText(" Ok ");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        getContentPane().add(okButton, gridBagConstraints);

        jLabel3.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(jLabel3, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        ok = true;
        setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void toComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_toComboBoxItemStateChanged
        backCount = ((ToElement) toComboBox.getSelectedItem()).backCount;
        ok = true;
        setVisible(false);
    }//GEN-LAST:event_toComboBoxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fromLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox toComboBox;
    // End of variables declaration//GEN-END:variables
    
    private static class ToElement {
    	public int backCount;
    	public String name;
    	public String toString() {
    		return name;
    	}
    }
    
    private int backCount;
    private boolean ok;
    
	/**
	 * Opens the dialog and lets select the number of tables to join.
	 */
    public int selectBackCount(List<Association> associationsOnPath) {
    	if (associationsOnPath.isEmpty()) {
    		return 0;
    	}
    	fromLabel.setText(associationsOnPath.get(0).source.getUnqualifiedName());
    	backCount = -1;
    	ok = false;
    	DefaultComboBoxModel model = new DefaultComboBoxModel();
    	int i = 0;
		ToElement element = new ToElement();
		element.backCount = i++;
		element.name = associationsOnPath.get(0).source.getUnqualifiedName();
		model.addElement(element);
    	for (Association a: associationsOnPath) {
    		element = new ToElement();
    		element.backCount = i++;
    		element.name = a.destination.getUnqualifiedName();
    		model.addElement(element);
    	}
    	toComboBox.setModel(model);
    	toComboBox.setSelectedIndex(i - 1);
		setLocation(getParent().getX() + (getParent().getWidth() - getWidth()) / 2, getParent().getY() + (getParent().getHeight() - getHeight()) / 2);
    	setVisible(true);
		return ok? backCount : -1;
	}

}
