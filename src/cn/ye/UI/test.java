/*
 * Created by JFormDesigner on Fri Apr 03 08:15:02 CST 2020
 */

package cn.ye.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author hho
 */
public class test extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    test frame = new test();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public test() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        tabbedPane2 = new JTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        layeredPane1 = new JLayeredPane();
        layeredPane2 = new JLayeredPane();
        desktopPane1 = new JDesktopPane();
        desktopPane2 = new JDesktopPane();
        internalFrame1 = new JInternalFrame();
        toolBar1 = new JToolBar();
        button1 = new JButton();
        splitPane1 = new JSplitPane();
        textField1 = new JTextField();
        label1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();

        //======== tabbedPane1 ========
        {

            //======== tabbedPane2 ========
            {

                //======== panel1 ========
                {

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGap(0, 334, Short.MAX_VALUE)
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGap(0, 141, Short.MAX_VALUE)
                    );
                }
                tabbedPane2.addTab("text", panel1);

                //======== panel2 ========
                {

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGap(0, 334, Short.MAX_VALUE)
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGap(0, 141, Short.MAX_VALUE)
                    );
                }
                tabbedPane2.addTab("text", panel2);

                //======== panel3 ========
                {

                    GroupLayout panel3Layout = new GroupLayout(panel3);
                    panel3.setLayout(panel3Layout);
                    panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                            .addGap(0, 334, Short.MAX_VALUE)
                    );
                    panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                            .addGap(0, 141, Short.MAX_VALUE)
                    );
                }
                tabbedPane2.addTab("text", panel3);
            }
            tabbedPane1.addTab("text", tabbedPane2);
        }

        //======== layeredPane1 ========
        {
            layeredPane1.add(layeredPane2, JLayeredPane.DEFAULT_LAYER);
            layeredPane2.setBounds(new Rectangle(new Point(30, 20), layeredPane2.getPreferredSize()));
        }

        //======== internalFrame1 ========
        {
            internalFrame1.setVisible(true);
            Container internalFrame1ContentPane = internalFrame1.getContentPane();

            GroupLayout internalFrame1ContentPaneLayout = new GroupLayout(internalFrame1ContentPane);
            internalFrame1ContentPane.setLayout(internalFrame1ContentPaneLayout);
            internalFrame1ContentPaneLayout.setHorizontalGroup(
                internalFrame1ContentPaneLayout.createParallelGroup()
                    .addGap(0, 100, Short.MAX_VALUE)
            );
            internalFrame1ContentPaneLayout.setVerticalGroup(
                internalFrame1ContentPaneLayout.createParallelGroup()
                    .addGap(0, 100, Short.MAX_VALUE)
            );
        }

        //======== toolBar1 ========
        {

            //---- button1 ----
            button1.setText("text");
            toolBar1.add(button1);
        }

        //======== splitPane1 ========
        {
            splitPane1.setLeftComponent(textField1);

            //---- label1 ----
            label1.setText("text");
            splitPane1.setRightComponent(label1);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(120, 120, 120)
                    .addComponent(toolBar1, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(96, 96, 96)
                            .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(internalFrame1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(82, 82, 82)
                    .addComponent(desktopPane1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(desktopPane2, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(splitPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(splitPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(63, 63, 63)
                                    .addComponent(layeredPane1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(desktopPane1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tabbedPane1, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(toolBar1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
                        .addComponent(internalFrame1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(102, 102, 102)
                    .addComponent(desktopPane2, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLayeredPane layeredPane1;
    private JLayeredPane layeredPane2;
    private JDesktopPane desktopPane1;
    private JDesktopPane desktopPane2;
    private JInternalFrame internalFrame1;
    private JToolBar toolBar1;
    private JButton button1;
    private JSplitPane splitPane1;
    private JTextField textField1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
