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

import java.awt.Frame;
import java.awt.Window;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.sf.jailer.ui.InfoBar;
import net.sf.jailer.ui.UIUtil;

/**
 * Tips dialog.
 * 
 * @author Ralf Wisser
 */
public class TipDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = -1997318446584589123L;
	
    /** Creates new form TipDialog */
    public TipDialog(Window parent, String tip) {
        super(parent, ModalityType.DOCUMENT_MODAL);
        initComponents();
        InfoBar infoBar = new InfoBar("Did you know?", tip);
        UIUtil.replace(infoBarLabel, infoBar);
        pack();
        setLocation(getParent().getX() + (getParent().getWidth() - getWidth()) / 2, getParent().getY() + (getParent().getHeight() - getHeight()) / 2);
		UIUtil.fit(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        infoBarLabel = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tip");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        infoBarLabel.setText("infobar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        getContentPane().add(infoBarLabel, gridBagConstraints);

        jCheckBox1.setText("Don't tell me again.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 32, 0, 44);
        getContentPane().add(jCheckBox1, gridBagConstraints);

        jButton1.setText(" Ok ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        getContentPane().add(jButton1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel infoBarLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    // End of variables declaration//GEN-END:variables

    private static Set<String> toldTips = new HashSet<String>();
    
	public static void showTip(Frame parent, String tipId, String tip) {
		if (toldTips.contains(tipId)) {
			return;
		}
		toldTips.add(tipId);
		File dtma = new File(".dtma-" + tipId);
		if (!dtma.exists()) {
			TipDialog tipDialog = new TipDialog(parent, tip);
			tipDialog.setVisible(true);
			if (tipDialog.jCheckBox1.isSelected()) {
				try {
					FileWriter out = new FileWriter(dtma);
					out.write("-");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
