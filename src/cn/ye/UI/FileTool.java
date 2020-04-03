/*
 * Created by JFormDesigner on Fri Apr 03 08:28:38 CST 2020
 */

package cn.ye.UI;

import cn.ye.core.Create;
import cn.ye.core.Delete;
import cn.ye.core.Read;
import cn.ye.core.Update;
import cn.ye.model.fileModle;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author hho
 */
public class FileTool extends JFrame {

    //主线程入口
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FileTool frame = new FileTool();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //变量定义
    //读取参数，全局仅仅允许拥有一份
    ArrayList<fileModle> fileModles;
    //Read
    private String fileSrc;
    private boolean mutiDir;
    private String[] filterCondition;
    //Delete
    private boolean delDir;
    //Create
    private String fileOut1;
    private boolean isMKDir1;
    private String rule;
    private int dirNum;

    private String fileOut2;
    private boolean isMKDir2;
    private String[] strings;
    //Update
    private String fileSrc1;
    private String[] filterCondition1;
    private int dirHibFlag;

    private String fileSrc2;
    private String[] filterCondition2;
    private boolean reDir;

    //文件读取1
    private StringBuilder getStringBuilder() {
        fileModles=Read.getFileModles();
        StringBuilder builder = new StringBuilder();
        for (fileModle fileModle : fileModles) {
            builder.append(fileModle.getFileMsg() + "\n");
        }
        return builder;
    }

    //文件读取2
    private StringBuilder getFaileBuilder(ArrayList<File> failOpr) {
        StringBuilder builder = new StringBuilder();
        if (failOpr!=null)
        for (File file : failOpr) {
            builder.append(file.getAbsolutePath() + "\n");
        }
        return builder;
    }
    //进行参数读取
    private void initReadPra() {
        fileSrc=readFileSrc.getText();
        delDir = readMutiDir.isSelected();
        filterCondition=readFilterCondition.getText().split("\\n");
    }
    private void initDeletePra() {
        fileSrc=deleteFileSrc2.getText();
        mutiDir = deleteMutiDir2.isSelected();
        filterCondition=deleteFilterCondition2.getText().split("\\n");
    }
    private void initCreatePra() {
        fileOut1=createFileOut1.getText();
        rule = createRule.getText();
        isMKDir1=createIsMKDir1.isSelected();
        try {
            dirNum = Integer.parseInt(createDirNum.getText());
        } catch (Exception e) {
            dirNum=0;
        }

        fileOut2=createFileOut2.getText();
        isMKDir2=createIsMKDir2.isSelected();
        strings=createStrings2.getText().split("\\n");
    }

    private void initUpdatePra() {
        //1
        fileSrc1=updateFileSrc1.getText();
        reDir = updateReDir1.isSelected();
        rule=updateRule1.getText();
        filterCondition1=updateFilterCondition1.getText().split("\n");
        //2
        fileSrc2=updateFileSrc2.getText();
        fileOut2=updateFileOut.getText();
        if (updateRadioButton1.isSelected()){ dirHibFlag=1;}
        if (updateRadioButton2.isSelected()){ dirHibFlag=2;}
        if (updateRadioButton3.isSelected()){ dirHibFlag=3;}

        filterCondition2=updateFilterCondition2.getText().split("\n");
    }

    /**
     * 功能四：文件更新提取
     * @param e
     */
    private void updateButton1MouseClicked(MouseEvent e) {
        // TODO add your code here
        initUpdatePra();
        ArrayList<File> failOpr = Update.renameFile(fileSrc1, rule,reDir, filterCondition1);
        updateTextArea1.setText(getStringBuilder().toString());
        updateFaileTextArea1.setText(getFaileBuilder(failOpr).toString());
    }

    private void updateButton2MouseClicked(MouseEvent e) {
        // TODO add your code here
        initUpdatePra();
        ArrayList<File> failOpr =Update.removeFile(fileSrc2, fileOut2, dirHibFlag, filterCondition2);
        updateTextArea2.setText(getStringBuilder().toString());
        updateFailTextArea2.setText(getFaileBuilder(failOpr).toString());
    }

    /**
     * 功能三：创建文件
     * @param e
     */
    private void createButton1MouseClicked(MouseEvent e) {
        // TODO add your code here
        initCreatePra();
        ArrayList<File> failOpr = Create.createDirOrFileByRule(fileOut1, rule, isMKDir1, dirNum);
        createTextArea1.setText(getStringBuilder().toString());
        createFileOut1.setText(getFaileBuilder(failOpr).toString());
    }

    private void createButton2MouseClicked(MouseEvent e) {
        // TODO add your code here
        initCreatePra();
        ArrayList<File> failOpr =Create.createDirOrFileByStrings(fileOut2, strings, isMKDir2);
        createTextArea2.setText(getStringBuilder().toString());
        createFileOut1.setText(getFaileBuilder(failOpr).toString());
    }
    /**
     * 功能二：删除功能
     * @param e
     */
    private void deleteButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        initDeletePra();
        ArrayList<File> files = Delete.deleteFileOrDir(fileSrc, delDir, filterCondition);
        StringBuilder builder = getFaileBuilder(files);
        deleteFaileTextField1.setText(builder.toString());
    }
    /**
     * 功能一：读取功能
     */
    private void readButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        initReadPra();
        Read.getFileModles(fileSrc, mutiDir, filterCondition);
        StringBuilder builder = getStringBuilder();
        readtextArea1.setText(builder.toString());
    }



    public FileTool() {
        initComponents();
    }

    private void readButtonFocusLost(FocusEvent e) {
        // TODO add your code here
    }

    private void readFileSrcFocusLost(FocusEvent e) {
        // TODO add your code here
    }








    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        tabbedPane4 = new JTabbedPane();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        readtextArea1 = new JTextArea();
        readButton = new JButton();
        scrollPane2 = new JScrollPane();
        readFilterCondition = new JTextArea();
        label1 = new JLabel();
        label2 = new JLabel();
        readMutiDir = new JRadioButton();
        readFileSrc = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        tabbedPane5 = new JTabbedPane();
        panel2 = new JPanel();
        deleteTextArea2 = new JTextArea();
        deleteFilterCondition2 = new JTextArea();
        deleteFileSrc2 = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        deleteMutiDir2 = new JRadioButton();
        label7 = new JLabel();
        deleteButton2 = new JButton();
        label8 = new JLabel();
        label17 = new JLabel();
        deleteFaileTextField1 = new JTextField();
        tabbedPane7 = new JTabbedPane();
        panel3 = new JPanel();
        readFilterCondition3 = new JTextArea();
        createFileOut1 = new JTextField();
        label9 = new JLabel();
        label10 = new JLabel();
        createIsMKDir1 = new JRadioButton();
        label11 = new JLabel();
        createButton1 = new JButton();
        createRule = new JTextField();
        label23 = new JLabel();
        label24 = new JLabel();
        createFaileTextArea1 = new JTextArea();
        createTextArea1 = new JTextArea();
        label12 = new JLabel();
        createDirNum = new JTextField();
        panel5 = new JPanel();
        createStrings2 = new JTextArea();
        createFileOut2 = new JTextField();
        label18 = new JLabel();
        label19 = new JLabel();
        createIsMKDir2 = new JRadioButton();
        label20 = new JLabel();
        createButton2 = new JButton();
        label21 = new JLabel();
        label22 = new JLabel();
        scrollPane3 = new JScrollPane();
        createTextArea2 = new JTextArea();
        scrollPane4 = new JScrollPane();
        createFaileTextArea2 = new JTextArea();
        tabbedPane = new JTabbedPane();
        panel6 = new JPanel();
        updateFilterCondition1 = new JTextArea();
        updateFileSrc1 = new JTextField();
        label28 = new JLabel();
        label29 = new JLabel();
        label30 = new JLabel();
        updateButton1 = new JButton();
        label31 = new JLabel();
        scrollPane7 = new JScrollPane();
        updateTextArea1 = new JTextArea();
        readFilterCondition4 = new JTextArea();
        updateRule1 = new JTextField();
        label32 = new JLabel();
        label34 = new JLabel();
        scrollPane8 = new JScrollPane();
        updateFaileTextArea1 = new JTextArea();
        updateReDir1 = new JRadioButton();
        panel4 = new JPanel();
        updateFilterCondition2 = new JTextArea();
        updateFileSrc2 = new JTextField();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        updateButton2 = new JButton();
        label16 = new JLabel();
        updateRadioButton1 = new JRadioButton();
        updateRadioButton2 = new JRadioButton();
        updateRadioButton3 = new JRadioButton();
        label25 = new JLabel();
        scrollPane5 = new JScrollPane();
        updateTextArea2 = new JTextArea();
        scrollPane6 = new JScrollPane();
        updateFailTextArea2 = new JTextArea();
        label26 = new JLabel();
        label27 = new JLabel();
        updateFileOut = new JTextField();

        //======== this ========
        Container contentPane = getContentPane();

        //======== tabbedPane1 ========
        {

            //======== tabbedPane4 ========
            {

                //======== panel1 ========
                {

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(readtextArea1);
                    }

                    //---- readButton ----
                    readButton.setText("\u8bfb\u53d6\u6587\u4ef6");
                    readButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            readButtonMouseClicked(e);
                        }
                    });
                    readButton.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusLost(FocusEvent e) {
                            readButtonFocusLost(e);
                        }
                    });

                    //======== scrollPane2 ========
                    {

                        //---- readFilterCondition ----
                        readFilterCondition.setText("\u8fc7\u6ee4\u6761\u4ef6\uff0c\u793a\u4f8b\u5982\u4e0b\uff1a\njava");
                        scrollPane2.setViewportView(readFilterCondition);
                    }

                    //---- label1 ----
                    label1.setText("\u6570\u636e\u8bfb\u53d6");

                    //---- label2 ----
                    label2.setText("\u4e3b\u8981\u6b65\u9aa4\uff1a\u53c2\u6570\u8bbe\u7f6e");

                    //---- readMutiDir ----
                    readMutiDir.setText("\u662f\u5426\u6df1\u5ea6\u8bfb\u53d6");
                    readMutiDir.setSelected(true);

                    //---- readFileSrc ----
                    readFileSrc.setText("D:\\helo");
                    readFileSrc.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusLost(FocusEvent e) {
                            readFileSrcFocusLost(e);
                        }
                    });

                    //---- label3 ----
                    label3.setText("\u6587\u4ef6\u8bfb\u53d6\u8def\u5f84");

                    //---- label4 ----
                    label4.setText("\u8fc7\u6ee4\u5173\u952e\u5b57");

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(readButton, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                .addGap(547, 547, 547))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label2, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(readMutiDir, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(readFileSrc, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(scrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(label4, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                                .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(readButton)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(readMutiDir, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(readFileSrc, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(scrollPane1))
                                .addGap(20, 20, 20))
                    );
                }
                tabbedPane4.addTab("\u6587\u4ef6\u6761\u4ef6\u8bfb\u53d6", panel1);
            }
            tabbedPane1.addTab("Read", tabbedPane4);

            //======== tabbedPane5 ========
            {

                //======== panel2 ========
                {

                    //---- deleteFilterCondition2 ----
                    deleteFilterCondition2.setText("\u8fc7\u6ee4\u6761\u4ef6\uff0c\u793a\u4f8b\u5982\u4e0b\uff1a\njava");

                    //---- deleteFileSrc2 ----
                    deleteFileSrc2.setText("D:\\helo");

                    //---- label5 ----
                    label5.setText("\u5220\u9664\u6587\u4ef6\u7b5b\u9009\u5173\u952e\u5b57");

                    //---- label6 ----
                    label6.setText("\u6587\u4ef6\u5220\u9664\u76ee\u6807\u8def\u5f84");

                    //---- deleteMutiDir2 ----
                    deleteMutiDir2.setText("\u662f\u5426\u5220\u9664\u76ee\u5f55");
                    deleteMutiDir2.setSelected(true);

                    //---- label7 ----
                    label7.setText("\u4e3b\u8981\u6b65\u9aa4\uff1a\u53c2\u6570\u8bbe\u7f6e");

                    //---- deleteButton2 ----
                    deleteButton2.setText("\u8fdb\u884c\u5220\u9664");
                    deleteButton2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            deleteButtonMouseClicked(e);
                        }
                    });

                    //---- label8 ----
                    label8.setText("\u6570\u636e\u8bfb\u53d6");

                    //---- label17 ----
                    label17.setText("\u5220\u9664\u5931\u8d25\u7684\u6587\u4ef6");

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup()
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(deleteMutiDir2, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                            .addComponent(label5, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                            .addComponent(label6, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                            .addComponent(deleteFileSrc2, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(deleteFilterCondition2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(619, Short.MAX_VALUE)))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(deleteButton2, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(label8, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                        .addGap(277, 277, 277)
                                        .addComponent(label17, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deleteTextArea2, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deleteFaileTextField1, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
                                .addContainerGap())
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(0, 84, Short.MAX_VALUE)
                                    .addComponent(deleteMutiDir2)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(deleteFileSrc2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                    .addGap(7, 7, 7)
                                    .addComponent(deleteFilterCondition2, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteButton2))
                                    .addComponent(label17, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel2Layout.createParallelGroup()
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 349, Short.MAX_VALUE))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(panel2Layout.createParallelGroup()
                                            .addComponent(deleteFaileTextField1, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                                            .addComponent(deleteTextArea2, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))))
                                .addContainerGap())
                    );
                }
                tabbedPane5.addTab("\u6587\u4ef6\u6761\u4ef6\u5220\u9664", panel2);
            }
            tabbedPane1.addTab("Delete", tabbedPane5);

            //======== tabbedPane7 ========
            {

                //======== panel3 ========
                {

                    //---- readFilterCondition3 ----
                    readFilterCondition3.setText("\u521b\u5efa\u89c4\u5219\u8bf4\u660e\uff0c\u793a\u4f8b\u5982\u4e0b\uff1a \n\nHello^100$NUM^yyyy MM dd HH mm ss$Date^A$WORD\n\n\u53ef\u4ee5\u4f7f\u7528\u7684\u53c2\u6570\uff1a\n\n^100$ \u5fc5\u987b\u7684\uff0c\u8868\u4ece100\u5411\u4e0a\u8ba1\u6570\u521b\u5efa\n^yyyy MM dd HH mm ss$ \u65e5\u671f\uff0c\u8868\u793a \u5e74 \u6708 \u65e5 \u65f6 \u5206 \u79d2\n^A$ \u8868\u793a\u4ece\u5b57\u6bcd\u5f00\u59cb\u6392\u5e8f\uff0c\u53ef\u4ee5\u4f7f\u7528 A-Z a-z");
                    readFilterCondition3.setEditable(false);

                    //---- createFileOut1 ----
                    createFileOut1.setText("D:\\helo");

                    //---- label9 ----
                    label9.setText("\u4f60\u7684\u521b\u5efa\u89c4\u5219");

                    //---- label10 ----
                    label10.setText("\u6587\u4ef6\u521b\u5efa\u8f93\u51fa\u8def\u5f84");

                    //---- createIsMKDir1 ----
                    createIsMKDir1.setText("\u521b\u5efa\u76ee\u5f55or\u6587\u4ef6");
                    createIsMKDir1.setSelected(true);

                    //---- label11 ----
                    label11.setText("\u4e3b\u8981\u6b65\u9aa4\uff1a\u53c2\u6570\u8bbe\u7f6e");

                    //---- createButton1 ----
                    createButton1.setText("\u5f00\u59cb\u521b\u5efa");
                    createButton1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            createButton1MouseClicked(e);
                        }
                    });

                    //---- createRule ----
                    createRule.setText("Hello^100$NUM^yyyy MM dd HH mm ss$Date^A$WORD");

                    //---- label23 ----
                    label23.setText("\u6570\u636e\u8bfb\u53d6");

                    //---- label24 ----
                    label24.setText("\u521b\u5efa\u5931\u8d25\u7684\u6587\u4ef6");

                    //---- label12 ----
                    label12.setText("\u521b\u5efa\u6587\u4ef6\u7684\u6570\u91cf");

                    //---- createDirNum ----
                    createDirNum.setText("10");

                    GroupLayout panel3Layout = new GroupLayout(panel3);
                    panel3.setLayout(panel3Layout);
                    panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addComponent(createButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(createIsMKDir1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(createFileOut1)
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(label12)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(createDirNum))
                                    .addComponent(label9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGroup(panel3Layout.createParallelGroup()
                                            .addComponent(createRule, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(readFilterCondition3, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addComponent(label23, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
                                        .addGap(90, 90, 90)
                                        .addComponent(label24, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(createTextArea1, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)
                                        .addComponent(createFaileTextArea1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                    );
                    panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(panel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label23, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(createButton1)))
                                    .addComponent(label24, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel3Layout.createParallelGroup()
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(label11, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(createIsMKDir1)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label10, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(createFileOut1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addGroup(panel3Layout.createParallelGroup()
                                            .addComponent(label12)
                                            .addComponent(createDirNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(createRule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(readFilterCondition3, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel3Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(panel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(createFaileTextArea1, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                                            .addComponent(createTextArea1, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))))
                                .addContainerGap(3, Short.MAX_VALUE))
                    );
                }
                tabbedPane7.addTab("\u6839\u636e\u7279\u6b8a\u7684\u89c4\u5219\u521b\u5efa\u6587\u4ef6", panel3);

                //======== panel5 ========
                {

                    //---- createStrings2 ----
                    createStrings2.setText("\u793a\u4f8b\uff1a\njava1 \njava2 \njava3 \njava4");

                    //---- createFileOut2 ----
                    createFileOut2.setText("D:\\helo");

                    //---- label18 ----
                    label18.setText("\u521b\u5efa\u540d\u79f0\uff0c\u4ee5\u56de\u8f66\u5206\u9694");

                    //---- label19 ----
                    label19.setText("\u6587\u4ef6\u521b\u5efa\u76ee\u6807");

                    //---- createIsMKDir2 ----
                    createIsMKDir2.setText("\u521b\u5efa\u76ee\u5f55or\u6587\u4ef6");
                    createIsMKDir2.setSelected(true);

                    //---- label20 ----
                    label20.setText("\u4e3b\u8981\u6b65\u9aa4\uff1a\u53c2\u6570\u8bbe\u7f6e");

                    //---- createButton2 ----
                    createButton2.setText("\u5f00\u59cb\u521b\u5efa");
                    createButton2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            createButton2MouseClicked(e);
                        }
                    });

                    //---- label21 ----
                    label21.setText("\u6570\u636e\u8bfb\u53d6");

                    //---- label22 ----
                    label22.setText("\u521b\u5efa\u5931\u8d25\u7684\u6587\u4ef6");

                    //======== scrollPane3 ========
                    {
                        scrollPane3.setViewportView(createTextArea2);
                    }

                    //======== scrollPane4 ========
                    {
                        scrollPane4.setViewportView(createFaileTextArea2);
                    }

                    GroupLayout panel5Layout = new GroupLayout(panel5);
                    panel5.setLayout(panel5Layout);
                    panel5Layout.setHorizontalGroup(
                        panel5Layout.createParallelGroup()
                            .addGroup(panel5Layout.createParallelGroup()
                                .addGroup(panel5Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(panel5Layout.createParallelGroup()
                                        .addGroup(panel5Layout.createSequentialGroup()
                                            .addComponent(createButton2, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(label21, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(label20, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createIsMKDir2, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 281, Short.MAX_VALUE)))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel5Layout.createParallelGroup()
                                    .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(createFileOut2, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                        .addComponent(label19, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                        .addComponent(label18, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                                    .addComponent(createStrings2, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(label22, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                    );
                    panel5Layout.setVerticalGroup(
                        panel5Layout.createParallelGroup()
                            .addGroup(panel5Layout.createParallelGroup()
                                .addGroup(panel5Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(panel5Layout.createParallelGroup()
                                        .addComponent(createButton2)
                                        .addComponent(label21, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label20, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(createIsMKDir2)
                                    .addGap(0, 336, Short.MAX_VALUE)))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGroup(panel5Layout.createParallelGroup()
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(label22, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                            .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)))
                                    .addGroup(panel5Layout.createSequentialGroup()
                                        .addGap(95, 95, 95)
                                        .addComponent(label19, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(createFileOut2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label18, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(createStrings2, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(7, Short.MAX_VALUE))
                    );
                }
                tabbedPane7.addTab("\u6839\u636e\u56fa\u5b9a\u540d\u79f0\u521b\u5efa\u6587\u4ef6", panel5);
            }
            tabbedPane1.addTab("Create", tabbedPane7);

            //======== tabbedPane ========
            {

                //======== panel6 ========
                {

                    //---- updateFilterCondition1 ----
                    updateFilterCondition1.setText("\u8fc7\u6ee4\u6761\u4ef6\uff0c\u793a\u4f8b\u5982\u4e0b\uff1a\njava");

                    //---- updateFileSrc1 ----
                    updateFileSrc1.setText("D:\\helo");

                    //---- label28 ----
                    label28.setText("\u8fc7\u6ee4\u5173\u952e\u5b57");

                    //---- label29 ----
                    label29.setText("\u6587\u4ef6\u8bfb\u53d6\u8def\u5f84");

                    //---- label30 ----
                    label30.setText("\u4e3b\u8981\u6b65\u9aa4\uff1a\u53c2\u6570\u8bbe\u7f6e");

                    //---- updateButton1 ----
                    updateButton1.setText("\u5f00\u59cb\u91cd\u547d\u540d");
                    updateButton1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            updateButton1MouseClicked(e);
                        }
                    });

                    //---- label31 ----
                    label31.setText("\u6570\u636e\u8bfb\u53d6");

                    //======== scrollPane7 ========
                    {
                        scrollPane7.setViewportView(updateTextArea1);
                    }

                    //---- readFilterCondition4 ----
                    readFilterCondition4.setText("\u521b\u5efa\u89c4\u5219\u8bf4\u660e\uff0c\u793a\u4f8b\u5982\u4e0b\uff1a \n\nHello^100$NUM^yyyy MM dd HH mm ss$Date^A$WORD\n\n\u53ef\u4ee5\u4f7f\u7528\u7684\u53c2\u6570\uff1a\n\n^100$ \u5fc5\u987b\u7684\uff0c\u8868\u4ece100\u5411\u4e0a\u8ba1\u6570\u521b\u5efa\n^yyyy MM dd HH mm ss$ \u65e5\u671f\uff0c\u8868\u793a \u5e74 \u6708 \u65e5 \u65f6 \u5206 \u79d2\n^A$ \u8868\u793a\u4ece\u5b57\u6bcd\u5f00\u59cb\u6392\u5e8f\uff0c\u53ef\u4ee5\u4f7f\u7528 A-Z a-z");
                    readFilterCondition4.setEditable(false);

                    //---- updateRule1 ----
                    updateRule1.setText("Hello^100$NUM^yyyy MM dd HH mm ss$Date^A$WORD");

                    //---- label32 ----
                    label32.setText("\u4f60\u7684\u521b\u5efa\u89c4\u5219");

                    //---- label34 ----
                    label34.setText("\u63d0\u53d6\u5931\u8d25\u7684\u6587\u4ef6");

                    //======== scrollPane8 ========
                    {
                        scrollPane8.setViewportView(updateFaileTextArea1);
                    }

                    //---- updateReDir1 ----
                    updateReDir1.setText("\u91cd\u547d\u540d\u76ee\u5f55or\u6587\u4ef6");

                    GroupLayout panel6Layout = new GroupLayout(panel6);
                    panel6.setLayout(panel6Layout);
                    panel6Layout.setHorizontalGroup(
                        panel6Layout.createParallelGroup()
                            .addGroup(panel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel6Layout.createParallelGroup()
                                    .addGroup(panel6Layout.createSequentialGroup()
                                        .addGroup(panel6Layout.createParallelGroup()
                                            .addComponent(updateRule1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                            .addGroup(GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(panel6Layout.createParallelGroup()
                                                    .addComponent(label29, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(updateReDir1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label28, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label32, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(panel6Layout.createSequentialGroup()
                                                .addGroup(panel6Layout.createParallelGroup()
                                                    .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(label30, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                                        .addComponent(updateFileSrc1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                                                    .addComponent(updateFilterCondition1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(readFilterCondition4, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane7, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane8, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panel6Layout.createSequentialGroup()
                                        .addComponent(updateButton1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(label31, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label34, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                    );
                    panel6Layout.setVerticalGroup(
                        panel6Layout.createParallelGroup()
                            .addGroup(panel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(updateButton1)
                                    .addComponent(label31, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label34))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel6Layout.createParallelGroup()
                                    .addComponent(scrollPane8)
                                    .addGroup(panel6Layout.createSequentialGroup()
                                        .addGroup(panel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addGroup(panel6Layout.createSequentialGroup()
                                                .addComponent(label30, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label29, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1)
                                                .addComponent(updateFileSrc1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(updateReDir1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label28, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(updateFilterCondition1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(label32, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(updateRule1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(readFilterCondition4, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12))
                                            .addComponent(scrollPane7, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }
                tabbedPane.addTab("\u6279\u91cf\u91cd\u547d\u540d\u76ee\u6807\u6587\u4ef6", panel6);

                //======== panel4 ========
                {

                    //---- updateFilterCondition2 ----
                    updateFilterCondition2.setText("\u8fc7\u6ee4\u6761\u4ef6\uff0c\u793a\u4f8b\u5982\u4e0b\uff1a\njava");

                    //---- updateFileSrc2 ----
                    updateFileSrc2.setText("D:\\helo");

                    //---- label13 ----
                    label13.setText("\u8fc7\u6ee4\u5173\u952e\u5b57");

                    //---- label14 ----
                    label14.setText("\u6587\u4ef6\u8bfb\u53d6\u8def\u5f84");

                    //---- label15 ----
                    label15.setText("\u4e3b\u8981\u6b65\u9aa4\uff1a\u53c2\u6570\u8bbe\u7f6e");

                    //---- updateButton2 ----
                    updateButton2.setText("\u5f00\u59cb\u63d0\u53d6");
                    updateButton2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            updateButton2MouseClicked(e);
                        }
                    });

                    //---- label16 ----
                    label16.setText("\u6570\u636e\u8bfb\u53d6");

                    //---- updateRadioButton1 ----
                    updateRadioButton1.setText("\u4e0d\u521b\u5efa\u65b0\u76ee\u5f55");
                    updateRadioButton1.setSelected(true);

                    //---- updateRadioButton2 ----
                    updateRadioButton2.setText("\u521b\u5efa\u4e00\u7ea7\u76ee\u5f55");

                    //---- updateRadioButton3 ----
                    updateRadioButton3.setText("\u4fdd\u6301\u539f\u6765\u7684\u76ee\u5f55\u6811");

                    //---- label25 ----
                    label25.setText("\u9009\u62e9\u76ee\u5f55\u7ed3\u6784");

                    //======== scrollPane5 ========
                    {
                        scrollPane5.setViewportView(updateTextArea2);
                    }

                    //======== scrollPane6 ========
                    {
                        scrollPane6.setViewportView(updateFailTextArea2);
                    }

                    //---- label26 ----
                    label26.setText("\u63d0\u53d6\u5931\u8d25\u7684\u6587\u4ef6");

                    //---- label27 ----
                    label27.setText("\u6587\u4ef6\u8f93\u51fa\u8def\u5f84");

                    //---- updateFileOut ----
                    updateFileOut.setText("D:\\helo");

                    GroupLayout panel4Layout = new GroupLayout(panel4);
                    panel4.setLayout(panel4Layout);
                    panel4Layout.setHorizontalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createParallelGroup()
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(updateButton2, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label16, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 210, Short.MAX_VALUE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel4Layout.createParallelGroup()
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(label15, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                        .addComponent(label14, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                        .addComponent(updateFileSrc2, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(updateFilterCondition2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(label13, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(updateRadioButton3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(updateRadioButton2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(updateRadioButton1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(label25, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(updateFileOut, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                        .addComponent(label27, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane5, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label26, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                    .addComponent(scrollPane6, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                                .addContainerGap())
                    );
                    panel4Layout.setVerticalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createParallelGroup()
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(panel4Layout.createParallelGroup()
                                        .addComponent(updateButton2)
                                        .addComponent(label16, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 392, Short.MAX_VALUE)))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label26, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel4Layout.createParallelGroup()
                                    .addComponent(scrollPane6, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(scrollPane5)
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addComponent(label15, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(label14, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(updateFileSrc2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(label27, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(updateFileOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(label25)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(updateRadioButton1)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(updateRadioButton2)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(updateRadioButton3)
                                            .addGap(8, 8, 8)
                                            .addComponent(label13, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(updateFilterCondition2, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, 0))
                    );
                }
                tabbedPane.addTab("\u63d0\u53d6\u6587\u4ef6", panel4);
            }
            tabbedPane1.addTab("Update", tabbedPane);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabbedPane1, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    ButtonGroup buttonGroup;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane4;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextArea readtextArea1;
    private JButton readButton;
    private JScrollPane scrollPane2;
    private JTextArea readFilterCondition;
    private JLabel label1;
    private JLabel label2;
    private JRadioButton readMutiDir;
    private JTextField readFileSrc;
    private JLabel label3;
    private JLabel label4;
    private JTabbedPane tabbedPane5;
    private JPanel panel2;
    private JTextArea deleteTextArea2;
    private JTextArea deleteFilterCondition2;
    private JTextField deleteFileSrc2;
    private JLabel label5;
    private JLabel label6;
    private JRadioButton deleteMutiDir2;
    private JLabel label7;
    private JButton deleteButton2;
    private JLabel label8;
    private JLabel label17;
    private JTextField deleteFaileTextField1;
    private JTabbedPane tabbedPane7;
    private JPanel panel3;
    private JTextArea readFilterCondition3;
    private JTextField createFileOut1;
    private JLabel label9;
    private JLabel label10;
    private JRadioButton createIsMKDir1;
    private JLabel label11;
    private JButton createButton1;
    private JTextField createRule;
    private JLabel label23;
    private JLabel label24;
    private JTextArea createFaileTextArea1;
    private JTextArea createTextArea1;
    private JLabel label12;
    private JTextField createDirNum;
    private JPanel panel5;
    private JTextArea createStrings2;
    private JTextField createFileOut2;
    private JLabel label18;
    private JLabel label19;
    private JRadioButton createIsMKDir2;
    private JLabel label20;
    private JButton createButton2;
    private JLabel label21;
    private JLabel label22;
    private JScrollPane scrollPane3;
    private JTextArea createTextArea2;
    private JScrollPane scrollPane4;
    private JTextArea createFaileTextArea2;
    private JTabbedPane tabbedPane;
    private JPanel panel6;
    private JTextArea updateFilterCondition1;
    private JTextField updateFileSrc1;
    private JLabel label28;
    private JLabel label29;
    private JLabel label30;
    private JButton updateButton1;
    private JLabel label31;
    private JScrollPane scrollPane7;
    private JTextArea updateTextArea1;
    private JTextArea readFilterCondition4;
    private JTextField updateRule1;
    private JLabel label32;
    private JLabel label34;
    private JScrollPane scrollPane8;
    private JTextArea updateFaileTextArea1;
    private JRadioButton updateReDir1;
    private JPanel panel4;
    private JTextArea updateFilterCondition2;
    private JTextField updateFileSrc2;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JButton updateButton2;
    private JLabel label16;
    private JRadioButton updateRadioButton1;
    private JRadioButton updateRadioButton2;
    private JRadioButton updateRadioButton3;
    private JLabel label25;
    private JScrollPane scrollPane5;
    private JTextArea updateTextArea2;
    private JScrollPane scrollPane6;
    private JTextArea updateFailTextArea2;
    private JLabel label26;
    private JLabel label27;
    private JTextField updateFileOut;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
